package co.fr8.terminal.base;

import co.fr8.data.constants.MT;
import co.fr8.data.crates.AbstractCrateStorage;
import co.fr8.data.crates.Crate;
import co.fr8.data.interfaces.dto.*;
import co.fr8.data.interfaces.manifests.AuthenticationMode;
import co.fr8.data.interfaces.manifests.OperationalStateCM;
import co.fr8.data.interfaces.manifests.StandardAuthenticationCM;
import co.fr8.data.interfaces.manifests.StandardConfigurationControlsCM;
import co.fr8.hub.managers.ICrateStorage;
import co.fr8.terminal.base.exception.AuthorizationTokenExpiredOrInvalidException;
import co.fr8.terminal.base.exception.InvalidOperationException;
import co.fr8.terminal.base.ui.AbstractActivityUI;
import co.fr8.terminal.infrastructure.IHubCommunicator;
import co.fr8.terminal.infrastructure.states.ConfigurationRequestType;
import co.fr8.util.CollectionUtils;
import co.fr8.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.UUID;

/**
 * TODO: Implement
 */
abstract public class AbstractTerminalActivity<T extends AbstractActivityUI>
    implements IActivity {

  private final ActivityTemplateDTO activityTemplateDTO;
  private OperationalStateCM operationalState;
  private ActivityContext activityContext;
  private ContainerExecutionContext containerExecutionContext;
  private AbstractCrateStorage payload;
  private AuthenticationMode authenticationMode = AuthenticationMode.InternalMode;
  protected AbstractCrateStorage currentActivityStorage;
  protected ActivityDTO currentActivity;
  protected AuthorizationToken authorizationToken;
//  protected T configurationControls;
  protected T activityUI;
  protected UpstreamQueryManager upstreamQueryManager;
  protected UIBuilder uiBuilder;
  protected int loopIndex;
  protected boolean disableValidationOnFollowup;

  public AbstractTerminalActivity(ActivityTemplateDTO activityTemplate) {
    this.activityContext = new ActivityContext();
    this.activityContext.setActivityPayload(new ActivityPayload(activityTemplate.getName()));
    this.activityTemplateDTO = activityTemplate;
  }

  private void initializeInternalState(ActivityContext activityContext,
                                       ContainerExecutionContext containerExecutionContext) {
    this.activityContext = activityContext;
    this.containerExecutionContext = containerExecutionContext;

    if (containerExecutionContext != null) {
      List<Crate> crates = payload.getCratesOfType(MT.OperationalStatus);

      if (CollectionUtils.isEmpty(crates) || operationalState == null) {
        throw new IllegalArgumentException("Operational state crate is not found");
      } else {
        operationalState = (OperationalStateCM) crates.get(0).getContent();
      }
    }

    initializeInternalState();
  }

  /**
   * add an authentication crate to the storage for the activity
   * @param revoke
   */
  protected void addAuthenticationCrate(boolean revoke) {
    getStorage().remove(StandardAuthenticationCM.class);
    getStorage().add(Crate.fromContent("RequiresAuthentication",
        new StandardAuthenticationCM(getAuthenticationMode(), revoke)));

  }

  public void configure(ActivityContext activityContext) {
    initializeInternalState(activityContext, null);
    ConfigurationRequestType conType =
        getConfigurationRequestType();

    Logger.debug("conType for request is: " + conType);

    boolean afterConfigureFails = false;

    try {
      Logger.debug("checking authentication: " + checkAuthentication());
      if (!checkAuthentication()) {
        addAuthenticationCrate(false);
        return;
      }

      Logger.debug("before configure: " + beforeConfigure(conType));
      if (!beforeConfigure(conType)) {
        return;
      }

      switch (conType) {
        case INITIAL: {
          Logger.debug("initial contype");
          initialize();
          break;
        }
        case FOLLOWUP: {
          followUp();
          break;
        }
        default:
          throw new IllegalArgumentException("Unsupported configuration type " + conType);
      }

      try {
        afterConfigure(conType, null);
      } catch (Exception e) {
        Logger.error("Exception during post configuration", e);
        afterConfigureFails = true;
        throw (e);
      }
    } catch (Exception e) {
      if (isInvalidTokenException(e) || e instanceof AuthorizationTokenExpiredOrInvalidException) {
        addAuthenticationCrate(true);
      } else if (!afterConfigureFails) {
        afterConfigure(conType, e);
      }
    }
  }

  public void activate(ActivityContext activityContext) {
    initializeInternalState(activityContext, null);
    if (!beforeActivate()) {
      return;
    }

    boolean afterActivateFails = false;

    try {
      activate();
      try {
        afterActivate(null);
      } catch (Exception e) {
        afterActivateFails = true;
        throw(e);
      }
    } catch (Exception e) {
      if (!afterActivateFails) {
        afterActivate(e);
      }
    }
  }

  public void deactivate(ActivityContext activityContext) {
    initializeInternalState(activityContext, null);

    if (!beforeDeactivate()) {
      return;
    }

    boolean afterDeactivateFails = false;

    try {
      deactivate();
      try {
        afterDeactivate(null);
      } catch (Exception e) {
        afterDeactivateFails = true;
        throw (e);
      }
    } catch (Exception e) {
      if (!afterDeactivateFails)
        afterDeactivate(e);
    }
  }

  public void run(ActivityContext activityContext, ContainerExecutionContext containerExecutionContext) {
    initializeInternalState(activityContext, containerExecutionContext);
    run();
  }

  public void runChildActivities(ActivityContext activityContext, ContainerExecutionContext containerExecutionContext) {
    initializeInternalState(activityContext, containerExecutionContext);
    run();
  }

  private void run(ActivityFunctionalInterface runMode) {
    if (!checkAuthentication()) {
      AuthorizationTokenDTO authToken = this.activityContext.getAuthorizationToken();
      if (authToken == null || StringUtils.isBlank(authToken.getToken())) {
        errorInvalidToken("No AuthToken provided.");
      } else {
        errorInvalidToken("Authorization token is invalid");
      }
      return;
    }

    boolean afterRunFails = false;

    try {
      operationalState.setCurrentActivityResponse(null);

      if (beforeRun()) {
        runMode.execute();
        try {
          afterRun(null);
        } catch (Exception e) {
          afterRunFails = true;
          throw(e);
        }
      }

      if (operationalState.getCurrentActivityErrorCode() == null) {
        success(StringUtils.EMPTY);
      }
    } catch (Exception /* ActivityErrorException */ e) {
      if (!afterRunFails)
        afterRun(e);

      if (isInvalidTokenException(e)) {
        errorInvalidToken(e.getMessage());
      } else {
        // TODO: this method expects an error code type
        error(e.getMessage(), null);
      }
    }
  }

  protected void suspendHubExecution(String message) {
    setResponse(ActivityResponse.REQUEST_SUSPEND, message, null);
  }

  protected void terminateHubExecution(String message) {
    setResponse(ActivityResponse.REQUEST_TERMINATE, message, null);
  }

  protected void requestHubExecutionTermination(String message) {
    terminateHubExecution(message);
  }

  protected void success(String message) {
    setResponse(ActivityResponse.SUCCESS, message, null);
  }

  protected void executeClientActivity(String clientActionName) {
    setResponse(ActivityResponse.EXECUTE_CLIENT_ACTIVITY, StringUtils.EMPTY, null);
    operationalState.setCurrentClientActivityName(clientActionName);
  }

  protected void requestSkipChildren() {
    setResponse(ActivityResponse.SKIP_CHILDREN, StringUtils.EMPTY, null);
  }

  protected void requestCall(UUID targetNodeId) {
    setResponse(ActivityResponse.CALL_AND_RETURN, StringUtils.EMPTY, null);
    ResponseMessageDTO responseMessage = new ResponseMessageDTO();
    responseMessage.setDetails(targetNodeId);
    ActivityResponseHelper.addResponseMessageDTO(operationalState.getCurrentActivityResponse(),
        responseMessage);
  }

  /**
   private void SyncConfControlsBack()
   {
   Storage.Remove<StandardConfigurationControlsCM>();
   // we create new StandardConfigurationControlsCM with controls from ActivityUi.
   // We do this because ActivityUi can has properties to access specific controls. We don't want those propeties exist in serialized crate.

   var configurationControlsToAdd = new StandardConfigurationControlsCM(ActivityUI.Controls);
   Storage.Add(Crate.FromContent(ConfigurationControlsLabel, configurationControlsToAdd, AvailabilityType.Configuration));
   ActivityUI.SaveDynamicControlsTo(configurationControlsToAdd);
   }
   */

  /**********************************************************************************/
  /// <summary>
  /// Jumps to an activity that resides in same subplan as current activity
  /// </summary>
  /// <returns></returns>
  protected void requestJumpToActivity(UUID targetActivityId)
  {
    setResponse(ActivityResponse.JUMP_TO_ACTIVITY, StringUtils.EMPTY, null);
    ResponseMessageDTO responseMessage = new ResponseMessageDTO();
    responseMessage.setDetails(targetActivityId);
    ActivityResponseHelper.addResponseMessageDTO(operationalState.getCurrentActivityResponse(),
        responseMessage);
  }

  /**********************************************************************************/
  /// <summary>
  /// Jumps to an activity that resides in same subplan as current activity
  /// </summary>
  /// <returns></returns>
  protected void requestJumpToSubplan(UUID targetSubplanId)
  {
    setResponse(ActivityResponse.JUMP_TO_SUBPLAN, StringUtils.EMPTY, null);
    ResponseMessageDTO responseMessage = new ResponseMessageDTO();
    responseMessage.setDetails(targetSubplanId);
    ActivityResponseHelper.addResponseMessageDTO(operationalState.getCurrentActivityResponse(),
        responseMessage);
  }

  /**********************************************************************************/
  /// <summary>
  /// Jumps to another plan
  /// </summary>
  /// <returns></returns>
  protected void launchPlan(UUID targetPlanId) {
    setResponse(ActivityResponse.LAUNCH_ADDITIONAL_PLAN, StringUtils.EMPTY, null);
    ResponseMessageDTO responseDTO = new ResponseMessageDTO();
    responseDTO.setDetails(targetPlanId);

    ActivityResponseHelper.addResponseMessageDTO(operationalState.getCurrentActivityResponse(),
        responseDTO);
  }

  /**********************************************************************************/

  protected void setResponse(ActivityResponse response, String message, Object details) {
    operationalState.setCurrentActivityResponse(new ActivityResponseDTO(response.getFriendlyName()));

    if (StringUtils.isNotBlank(message) || details != null) {
      ActivityResponseHelper.addResponseMessageDTO(operationalState.getCurrentActivityResponse(),
          new ResponseMessageDTO(message, StringUtils.EMPTY, StringUtils.EMPTY, details));
    }
  }

  /**********************************************************************************/
  /// <summary>
  /// returns error to hub
  /// </summary>
  protected void error(String errorMessage, ActivityErrorCode errorCode) {
    setResponse(ActivityResponse.ERROR, StringUtils.EMPTY, StringUtils.EMPTY);
    operationalState.setCurrentActivityErrorCode(errorCode);

    ActivityResponseHelper.addErrorDTO(operationalState.getCurrentActivityResponse(),
        new ErrorDTO(errorMessage, ErrorType.Generic.name(),
            ActivityErrorCode.AUTH_TOKEN_NOT_PROVIDED_OR_INVALID.name(), StringUtils.EMPTY,
            StringUtils.EMPTY, StringUtils.EMPTY));

  }

  /**********************************************************************************/
  /// <summary>
  /// returns error to hub
  /// </summary>
  protected void errorInvalidToken(String instructionsToUser) {
    setResponse(ActivityResponse.ERROR, StringUtils.EMPTY, StringUtils.EMPTY);
    operationalState.setCurrentActivityErrorCode(ActivityErrorCode.AUTH_TOKEN_NOT_PROVIDED_OR_INVALID);

    ActivityResponseHelper.addErrorDTO(operationalState.getCurrentActivityResponse(),
        new ErrorDTO(instructionsToUser, ErrorType.Authentication.name(),
            ActivityErrorCode.AUTH_TOKEN_NOT_PROVIDED_OR_INVALID.name(), StringUtils.EMPTY,
            StringUtils.EMPTY, StringUtils.EMPTY));

  }

  /**********************************************************************************/
  /// <summary>
  /// returns error to hub
  /// </summary>
  /// <param name="errorMessage"></param>
  /// <param name="errorCode"></param>
  /// <param name="currentActivity">Activity where the error occured</param>
  /// <param name="currentTerminal">Terminal where the error occured</param>
  /// <returns></returns>
  protected void raiseError(String errorMessage, ActivityErrorCode errorCode,
                            String currentActivity, String currentTerminal) {
    raiseError(errorMessage, ErrorType.Generic, errorCode, currentActivity, currentTerminal);
  }

  /**********************************************************************************/
  /// <summary>
  /// returns error to hub
  /// </summary>
  /// <param name="errorCode"></param>
  /// <param name="currentActivity">Activity where the error occured</param>
  /// <param name="currentTerminal">Terminal where the error occured</param>
  /// <param name="errorMessage"></param>
  /// <param name="errorType"></param>
  /// <returns></returns>
  protected void raiseError(String errorMessage, ErrorType errorType, ActivityErrorCode errorCode,
                            String currentActivity, String currentTerminal) {
    operationalState.setCurrentActivityErrorCode(errorCode);
    operationalState.setCurrentActivityResponse(new ActivityResponseDTO(ActivityResponse.ERROR.getFriendlyName()));
    ActivityResponseHelper.addErrorDTO(operationalState.getCurrentActivityResponse(),
        new ErrorDTO(errorMessage, errorCode.toString(), errorType.name(), null, currentActivity, currentTerminal));
  }

  /**********************************************************************************/
  /// <summary>
  /// Returns Needs authentication error to hub
  /// </summary>
  /// <returns></returns>
  protected void raiseNeedsAuthenticationError() {
    raiseError("No AuthToken provided.", ErrorType.Authentication,
        ActivityErrorCode.AUTH_TOKEN_NOT_PROVIDED_OR_INVALID, StringUtils.EMPTY, StringUtils.EMPTY);
  }

  /**********************************************************************************/
  /// <summary>
  /// Returns authentication error to hub
  /// </summary>
  /// <returns></returns>
  protected void raiseInvalidTokenError(String instructionsToUser) {
    raiseError(instructionsToUser, ErrorType.Authentication,
        ActivityErrorCode.AUTH_TOKEN_NOT_PROVIDED_OR_INVALID, StringUtils.EMPTY,
        StringUtils.EMPTY);
  }

  private ErrorDTO createErrorDTO() {
    return new ErrorDTO();
  }

  /**********************************************************************************/

  abstract protected SolutionPageDTO getDocumentation(String documentationType);

  /**********************************************************************************/

  public SolutionPageDTO getDocumentation(ActivityContext activityContext, String documentationType) {
    initializeInternalState(activityContext, null);
    return getDocumentation(documentationType);
  }

  protected AbstractCrateStorage getPayload() throws InvalidOperationException {
    checkRunTime("Payload storage is not available at the design time");
    return payload;
  }

  protected ContainerExecutionContext getContainerExecutionContext() throws InvalidOperationException {
    checkRunTime("Execution context is not available at the design time");
    return containerExecutionContext;
  }

  protected OperationalStateCM getOperationalState() throws InvalidOperationException {
    checkRunTime("Operations state is not available at the design time");
    return operationalState;
  }

  protected ICrateStorage getStorage() {

    /*
    TODO: Figure out if the activitycontext is necessary
    The following null check is a hackfix
     */

    if (activityContext.getActivityPayload() == null)
      activityContext.setActivityPayload(new ActivityPayload("Default Payload"));

    return activityContext.getActivityPayload().getCrateStorage();
  }

  protected ActivityPayload getActivityPayload() {
    return activityContext.getActivityPayload();
  }

  protected IHubCommunicator getHubCommunicator() {
    return activityContext.getHubCommunicator();
  }

  protected ActivityContext getActivityContext() {
    return activityContext;
  }

  protected boolean isRuntime() {
    return containerExecutionContext != null;
  }

  protected AuthenticationMode getAuthenticationMode() {
    return authenticationMode;
  }

  public void setAuthenticationMode(AuthenticationMode authenticationMode) {
    this.authenticationMode = authenticationMode;
  }

  private void checkRunTime(String message) throws InvalidOperationException {
    if (!isRuntime()) {
      throw new InvalidOperationException(
          (StringUtils.isBlank(message) ? "Not available at design time" : message));
    }
  }

  protected void afterConfigure(ConfigurationRequestType configurationRequestType, Exception e) {
    Logger.debug("In after configure with configurationRequestType : " + configurationRequestType);

    getStorage().add(generateStandardConfigurationControlsCrate());

    Logger.debug("There are " + getStorage().getCount() + " items in storage " + getStorage());
  }

  private Crate generateStandardConfigurationControlsCrate() {
    StandardConfigurationControlsCM controls =
        new StandardConfigurationControlsCM(activityUI.getControls());
    Crate crate = new Crate<>(MT.StandardConfigurationControls, controls);

    Logger.debug("generateStandardConfigurationControlsCrate returns: " + crate);

    return crate;
  }

  abstract protected boolean checkAuthentication();
  abstract protected ConfigurationRequestType getConfigurationRequestType();
  abstract public void initialize();
  abstract public void followUp();
  abstract public void run();
  abstract public void runChildActivities();
  abstract public void activate();
  abstract public void deactivate();
  abstract protected void afterRun(Exception e);
  abstract protected boolean beforeRun();
  abstract protected void afterDeactivate(Exception e);
  abstract protected boolean beforeDeactivate();
  abstract protected boolean afterActivate(Exception e);
  abstract protected boolean beforeActivate();

  abstract protected boolean beforeConfigure(ConfigurationRequestType configurationRequestType);
  abstract protected boolean isInvalidTokenException(Exception e);
  abstract protected void initializeInternalState();

  public interface ActivityFunctionalInterface {
    public void execute();
  }

  public ActivityTemplateDTO getActivityTemplateDTO() {
    return activityTemplateDTO;
  }

  public T getActivityUI() {
    return activityUI;
  }
}
