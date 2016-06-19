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
  private AuthenticationMode authenticationMode = AuthenticationMode.InternalMode;
  protected T activityUI;

  public AbstractTerminalActivity(ActivityTemplateDTO activityTemplate) {
    this.activityContext = new ActivityContext();
    this.activityContext.setActivityPayload(new ActivityPayload(activityTemplate.getName()));
    this.activityTemplateDTO = activityTemplate;
  }

  private void initializeInternalState(ActivityContext activityContext,
                                       ContainerExecutionContext containerExecutionContext,
                                       ActionNameEnum actionName) {
    this.activityContext = activityContext;
    this.containerExecutionContext = containerExecutionContext;

    if (containerExecutionContext != null) {
      List<Crate> crates = getActivityPayload().getCrateStorage().getCratesOfType(MT.OperationalStatus);

      if (CollectionUtils.isEmpty(crates) || operationalState == null) {
        throw new IllegalArgumentException("Operational state crate is not found");
      } else {
        operationalState = (OperationalStateCM) crates.get(0).getContent();
      }
    }

    initializeActivityState(actionName);
  }

  /**
   * add an authentication crate to the storage for the activity
   *
   * @param revoke specifies whether the authentication should be revoked
   */
  protected void addAuthenticationCrate(boolean revoke) {
    getStorage().remove(StandardAuthenticationCM.class);
    getStorage().add(Crate.fromContent("RequiresAuthentication",
        new StandardAuthenticationCM(getAuthenticationMode(), revoke)));
  }

  /**
   * Super method for the configure activity
   * Subclasses must implement initialize() and followup();
   *
   * @param activityContext
   */
  public void configure(ActivityContext activityContext) {
    initializeInternalState(activityContext, null, ActionNameEnum.CONFIGURE);
    ConfigurationRequestType conType = getConfigurationRequestType();

    Logger.debug("conType for request is: " + conType);

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

    afterConfigure(conType, null);

  }

  /**
   *
   * @param activityContext
   */
  public void activate(ActivityContext activityContext) {
    initializeInternalState(activityContext, null, ActionNameEnum.ACTIVATE);
    if (!beforeActivate()) {
      return;
    }

    activate();

    afterActivate();
  }

  /**
   *
   * @param activityContext
   */
  public void deactivate(ActivityContext activityContext) {
    initializeInternalState(activityContext, null, ActionNameEnum.DEACTIVATE);

    if (!beforeDeactivate()) {
      return;
    }

    deactivate();
    afterDeactivate();
  }

  /**
   *
   * @param activityContext
   * @param containerExecutionContext
   */
  public void run(ActivityContext activityContext, ContainerExecutionContext containerExecutionContext) {
    initializeInternalState(activityContext, containerExecutionContext, ActionNameEnum.RUN);

    run(run());
  }

  /**
   *
   * @param activityContext
   * @param containerExecutionContext
   */
  public void runChildActivities(ActivityContext activityContext, ContainerExecutionContext containerExecutionContext) {

    initializeInternalState(activityContext, containerExecutionContext,
        ActionNameEnum.EXECUTE_CHILD_ACTIVITIES);

    run(runChildActivities());
  }

  private void run(ActivityFunctionalInterface runMode) {
    if (!checkAuthentication()) {
      AuthorizationTokenDTO authToken =
          this.activityContext.getAuthorizationToken();
      if (authToken == null || StringUtils.isBlank(authToken.getToken())) {
        errorInvalidToken("No AuthToken provided.");
      } else {
        errorInvalidToken("Authorization token is invalid");
      }
      return;
    }

    operationalState.setCurrentActivityResponse(null);

    if (!beforeRun()) {
      return;
    }

    runMode.execute();
    afterRun();

    if (operationalState.getCurrentActivityErrorCode() == null) {
      success(StringUtils.EMPTY);
    } else {
      error(operationalState.getCurrentActivityErrorMessage(),
          operationalState.getCurrentActivityErrorCode());
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
  protected void requestJumpToActivity(UUID targetActivityId) {
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
  protected void requestJumpToSubplan(UUID targetSubplanId) {
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

  /**
  /// Returns authentication error to hub
  /// </summary>
  /// <returns></returns>
  */
  protected void raiseInvalidTokenError(String instructionsToUser) {
    raiseError(instructionsToUser, ErrorType.Authentication,
        ActivityErrorCode.AUTH_TOKEN_NOT_PROVIDED_OR_INVALID, StringUtils.EMPTY,
        StringUtils.EMPTY);
  }


  protected SolutionPageDTO getDocumentation(String documentationType) {
    SolutionPageDTO ret = new SolutionPageDTO();
    ret.setName("No documentation found");
    ret.setBody("Please override the getDocumentation in your subclass");

    return ret;
  }


  public SolutionPageDTO getDocumentation(ActivityContext activityContext, String documentationType) {
    initializeInternalState(activityContext, null, ActionNameEnum.DOCUMENTATION);
    return getDocumentation(documentationType);
  }

  protected AbstractCrateStorage getPayload() throws InvalidOperationException {
    checkRunTime("Payload storage is not available at the design time");
    return activityContext.getActivityPayload().getCrateStorage();
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
    Crate crate = new Crate<>(MT.StandardConfigurationControls, "Configuration_Controls", controls);

    Logger.debug("generateStandardConfigurationControlsCrate returns: " + crate);

    return crate;
  }

  protected ConfigurationRequestType getConfigurationRequestType() {

    Logger.debug("getConfigurationRequestType: storage has " + getStorage().getCount());
    return getStorage().getCount() <= 0 ?
        ConfigurationRequestType.INITIAL : ConfigurationRequestType.FOLLOWUP;
  }

  abstract protected void initializeActivityState(ActionNameEnum actionName);

  abstract protected boolean checkAuthentication();

  /**
   * Initialize abstract methods
   */
  abstract public void initialize();

  abstract protected boolean beforeConfigure(ConfigurationRequestType configurationRequestType);

  abstract public void followUp();

  abstract public ActivityFunctionalInterface runChildActivities();

  abstract public void deactivate();

  /**
   * Run abstract methods
   **/
  abstract protected boolean beforeRun();

  abstract public ActivityFunctionalInterface run();

  abstract protected void afterRun();

  /**
   * Deactivate abstract methods
   **/
  abstract protected void afterDeactivate();

  abstract protected boolean beforeDeactivate();

  /**
   * Activate abstract methods
   **/
  abstract public void activate();

  abstract protected boolean afterActivate();

  abstract protected boolean beforeActivate();

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
