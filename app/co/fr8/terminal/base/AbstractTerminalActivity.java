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
 * This is the main base class which must be extended in order to create a new
 * Fr8 activity.
 *
 * Subclasses will implement the following methods:
 * <ul>
 * <li>initializeActivityState(ActionNameEnum actionName)</li>
 * <li>checkAuthentication</li>
 * <li>initialize</li>
 * <li>beforeConfigure(ConfigurationRequestType configurationRequestType)</li>
 * <li>followUp</li>
 * <li>ActivityFunctionalInterface runChildActivities</li>
 * <li>deactivate</li>
 * <li>beforeRun</li>
 * <li>ActivityFunctionalInterface run</li>
 * <li>afterRun</li>
 * <li>afterDeactivate</li>
 * <li>beforeDeactivate</li>
 * <li>activate</li>
 * <li>afterActivate</li>
 * <li>#beforeActivate</li>
 * </ul>
 *
 */
abstract public class AbstractTerminalActivity<T extends AbstractActivityUI>
    implements IActivity {

  private final ActivityTemplateDTO activityTemplateDTO;
  private OperationalStateCM operationalState;
  private ActivityContext activityContext;
  private ContainerExecutionContext containerExecutionContext;
  private AuthenticationMode authenticationMode = AuthenticationMode.InternalMode;
  protected T activityUI;

  /**
   * Base constructor for an AbstractTerminalActivity. Any subclasses will
   * call this method in their constructors
   *
   * @param activityTemplate an {@link ActivityTemplateDTO} object to use to set
   *                         the {@link ActivityContext#activityPayload} property
   */
  public AbstractTerminalActivity(ActivityTemplateDTO activityTemplate) {
    this.activityContext = new ActivityContext();
    this.activityContext.setActivityPayload(new ActivityPayload(activityTemplate.getName()));
    this.activityTemplateDTO = activityTemplate;
  }

  /**
   * Private method which ensures that the activity is properly configured to
   * process the activity request
   *
   * @param activityContext the {@link ActivityContext} associated with the
   *                        activity
   * @param containerExecutionContext the {@link ContainerExecutionContext} associated
   *                                  with the activity
   * @param actionName the {@link ActionNameEnum} associated with the request
   */
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
   * @param activityContext the {@link ActivityContext}
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
   * Main work method which processes the /activate request
   * @param activityContext the {@link ActivityContext} associated with the activity
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
   * Main work method which processes /deactivate request
   * @param activityContext the ActivityContext associated with the activity
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
   * Main work method which processes the activity /run request the implemented
   * {@link #run()} method
   * @param activityContext the ActivityContext for the activity
   * @param containerExecutionContext the ContainerExecutionContext for the activity
   */
  public void run(ActivityContext activityContext, ContainerExecutionContext containerExecutionContext) {
    initializeInternalState(activityContext, containerExecutionContext, ActionNameEnum.RUN);

    run(run());
  }

  /**
   * Main work method which processes the activity /run request the implemented
   * {@link #runChildActivities()} method
   * @param activityContext the ActivityContext associated with this activity
   * @param containerExecutionContext the ContainerExecutionContext associated
   *                                  with this activity
   */
  public void runChildActivities(ActivityContext activityContext, ContainerExecutionContext containerExecutionContext) {

    initializeInternalState(activityContext, containerExecutionContext,
        ActionNameEnum.EXECUTE_CHILD_ACTIVITIES);

    run(runChildActivities());
  }

  /**
   * The run method is called when a request is made to /activities/run path of
   * the terminal.
   *
   * The logic path is to check the authorization state of the request,
   * call {@link #beforeRun()}, which may or may not be implemented by a subclass.
   *
   * If {@link #beforeRun()} returns true, then the code moves on the call the execute()
   * method of the ActivityFunctionalInterface method provided in the runMode
   * parameter.
   *
   * After runMode.execute() is called, the {@link #afterRun()} method is called to finalize
   * any information that must be returned in the response.
   *
   * Finally, the logic checks errors in the operational state for the activity and
   * updates the response state accordingly by calling {@link #success(String)} or
   * {@link #error(String, ActivityErrorCode)}
   * @param runMode an implementation of ActivityFunctionalInterface to run
   */
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

  /**
   * Sets the response type to ActivityResponse.REQUEST_SUSPEND
   * @param message the message associated with the suspend request
   */
  protected void suspendHubExecution(String message) {
    setResponse(ActivityResponse.REQUEST_SUSPEND, message, null);
  }

  /**
   * Sets the response type to ActivityResponse.REQUEST_TERMINATE
   * @param message the message associated with the termination request
   */
  protected void terminateHubExecution(String message) {
    setResponse(ActivityResponse.REQUEST_TERMINATE, message, null);
  }

  /**
   * Set the response type to ActivityResponse.SUCCESS
   * @param message
   */
  protected void success(String message) {
    setResponse(ActivityResponse.SUCCESS, message, null);
  }

  /**
   * Setst the response type to ActivityResponse.EXECUTE_CLIENT_ACTIVITY
   * @param clientActivityName the name of the activity
   */
  protected void executeClientActivity(String clientActivityName) {
    setResponse(ActivityResponse.EXECUTE_CLIENT_ACTIVITY, StringUtils.EMPTY, null);
    operationalState.setCurrentClientActivityName(clientActivityName);
  }

  /**
   * Sets the response type to ActivityResponse.SKIP_CHILDREN
   */
  protected void requestSkipChildren() {
    setResponse(ActivityResponse.SKIP_CHILDREN, StringUtils.EMPTY, null);
  }

  /**
   * Sets the response to the ActivityResponse.CALL_AND_RETURN type
   * @param targetNodeId the ID of the plan
   */
  protected void requestCall(UUID targetNodeId) {
    setResponse(ActivityResponse.CALL_AND_RETURN, StringUtils.EMPTY, null);
    ResponseMessageDTO responseMessage = new ResponseMessageDTO();
    responseMessage.setDetails(targetNodeId);
    ActivityResponseHelper.addResponseMessageDTO(operationalState.getCurrentActivityResponse(),
        responseMessage);
  }

  /**
   * Jumps to an activity that resides in the same subplan as the current activity
   * @param targetActivityId the ID of the activity to jump to
   */
  protected void requestJumpToActivity(UUID targetActivityId) {
    setResponse(ActivityResponse.JUMP_TO_ACTIVITY, StringUtils.EMPTY, null);
    ResponseMessageDTO responseMessage = new ResponseMessageDTO();
    responseMessage.setDetails(targetActivityId);
    ActivityResponseHelper.addResponseMessageDTO(operationalState.getCurrentActivityResponse(),
        responseMessage);
  }

  /**
   * Jumps to an subplan that resides in the same plan as the current activity
   * @param targetSubplanId the ID of the subplan
   */
  protected void requestJumpToSubplan(UUID targetSubplanId) {
    setResponse(ActivityResponse.JUMP_TO_SUBPLAN, StringUtils.EMPTY, null);
    ResponseMessageDTO responseMessage = new ResponseMessageDTO();
    responseMessage.setDetails(targetSubplanId);
    ActivityResponseHelper.addResponseMessageDTO(operationalState.getCurrentActivityResponse(),
        responseMessage);
  }

  /**
   * Jumps to another plan
   * @param targetPlanId the ID of the plan to jump to
   */
  protected void launchPlan(UUID targetPlanId) {
    setResponse(ActivityResponse.LAUNCH_ADDITIONAL_PLAN, StringUtils.EMPTY, null);
    ResponseMessageDTO responseDTO = new ResponseMessageDTO();
    responseDTO.setDetails(targetPlanId);

    ActivityResponseHelper.addResponseMessageDTO(operationalState.getCurrentActivityResponse(),
        responseDTO);
  }

  /**
   * Sets the response with the specified activity type
   * @param response The ActivityResponse to set
   * @param message The message String
   * @param details an object to set the set the ResponseMessageDTO.details property.
   */
  protected void setResponse(ActivityResponse response, String message, Object details) {
    operationalState.setCurrentActivityResponse(new ActivityResponseDTO(response.getFriendlyName()));

    if (StringUtils.isNotBlank(message) || details != null) {
      ActivityResponseHelper.addResponseMessageDTO(operationalState.getCurrentActivityResponse(),
          new ResponseMessageDTO(message, StringUtils.EMPTY, StringUtils.EMPTY, details));
    }
  }

  /**
   * Sets the error response with a generic error
   * @param errorMessage the error message
   * @param errorCode the ActivityErrorCoded
   */
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


  /**
   * Return an error to the hub by adding it to the activity response
   * @param errorMessage a String representing the specific error message
   * @param errorType the ErrorType enum of the error
   * @param errorCode the ActivityErrorCode associated with the error
   * @param currentActivity the name of the activity for which the error occurred
   * @param currentTerminal the name of the terminal which contains the activity
   */
  protected void raiseError(String errorMessage, ErrorType errorType, ActivityErrorCode errorCode,
                            String currentActivity, String currentTerminal) {
    operationalState.setCurrentActivityErrorCode(errorCode);
    operationalState.setCurrentActivityResponse(new ActivityResponseDTO(ActivityResponse.ERROR.getFriendlyName()));
    ActivityResponseHelper.addErrorDTO(operationalState.getCurrentActivityResponse(),
        new ErrorDTO(errorMessage, errorCode.toString(), errorType.name(), null, currentActivity, currentTerminal));
  }

  /**
   * Returns authentication error to hub indicating that the AuthorizationToken
   * is missing
   */
  protected void raiseAuthenticationError() {
    raiseError("No AuthToken provided.", ErrorType.Authentication,
        ActivityErrorCode.AUTH_TOKEN_NOT_PROVIDED_OR_INVALID, StringUtils.EMPTY, StringUtils.EMPTY);
  }

  /**
   * Base implementation of the getDocumentation() request. This should be overridden
   * by a subclass
   * @param documentationType the type of the documentation to retrieve
   * @return a SolutionPageDTO object with default values
   */
  protected SolutionPageDTO getDocumentation(String documentationType) {
    SolutionPageDTO ret = new SolutionPageDTO();
    ret.setName("No documentation found");
    ret.setBody("No documentation is provided for: " + documentationType);

    return ret;
  }


  /**
   * Called during a request to /activity/documentation
   * @param activityContext the ActivityContext associated with the request
   * @param documentationType the type of documentation to retrieve
   * @return a SolutionPageDTO object to be added to the response crate
   */
  public SolutionPageDTO getDocumentation(ActivityContext activityContext, String documentationType) {
    initializeInternalState(activityContext, null, ActionNameEnum.DOCUMENTATION);
    return getDocumentation(documentationType);
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
