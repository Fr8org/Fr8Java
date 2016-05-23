package co.fr8.terminal.base;

import co.fr8.data.constants.MT;
import co.fr8.data.crates.CrateManifestType;
import co.fr8.data.crates.ICrateStorage;
import co.fr8.data.interfaces.dto.ActivityDTO;
import co.fr8.data.interfaces.dto.ActivityTemplateDTO;
import co.fr8.data.interfaces.dto.AuthorizationTokenDTO;
import co.fr8.data.interfaces.dto.PayloadDTO;
import co.fr8.hub.managers.CrateManager;
import co.fr8.hub.managers.ICrateManager;
import co.fr8.hub.managers.IUpdatableCrateStorage;
import co.fr8.terminal.exception.TerminalConfigurationException;
import co.fr8.terminal.infrastructure.DefaultHubCommunicator;
import co.fr8.terminal.infrastructure.IHubCommunicator;
import co.fr8.terminal.infrastructure.ValidationManager;
import co.fr8.terminal.infrastructure.states.ConfigurationRequestType;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO: Implement
 */
abstract public class TerminalActivity {

  private final boolean authRequired;
  private final ICrateManager crateManager; // { get; private set; }
  private static String RUNTIME_AVAILABLE_CRATES_LABEL = "Runtime Available Crates";
  protected static final String CONFIGURATION_CONTROLS_LABEL = "Configuration_Controls";
  private String currentFr8UserId; // { get; set; }
  private String currentFr8UserEmail; // { get; set; }
  private final String activityName; // { get; set; }

  private List<ActivityTemplateDTO> activityTemplateCache = null;

  private final IHubCommunicator hubCommunicator;// { get; set; }

  public static final Set<MT> excludedManifestTypes =
      Stream.of(MT.StandardConfigurationControls, MT.StandardEventSubscription).collect(Collectors.toCollection(HashSet::new));


  public TerminalActivity(String activityName, boolean authRequired) {

    // TODO: Review IoC needs
    crateManager = new CrateManager();
    hubCommunicator = new DefaultHubCommunicator();

    this.authRequired = authRequired;
    this.activityName = activityName;
    registerConfigurationControls();
  }

  public void setCurrentUser(String userId, String userEmail) {
    currentFr8UserId = userId;
    currentFr8UserEmail = userEmail;
  }

  public IHubCommunicator getHubCommunicator() {
    return hubCommunicator;
  }

  protected abstract void initialize(CrateSignaller crateSignaller);
  protected abstract void configure(CrateSignaller crateSignaller, ValidationManager validationManager);
  protected abstract ConfigurationRequestType configurationEvaluationResult(ActivityDTO activityDTO);
  protected abstract void registerConfigurationControls();
  protected abstract void runChildActivities();
  protected abstract void runCurrentActivity();
  protected abstract void activate();
  protected abstract void deactivate();

  public String getActivityName() {
    return activityName;
  }

  /*

    /// <summary>
    /// Creates a suspend request for hub execution
    /// </summary>
    /// <param name="payload"></param>
    /// <returns></returns>
    protected PayloadDTO suspendHubExecution(PayloadDTO payload) {
      crateManager.getUpdatableStorage();
      using (var crateStorage = CrateManager.GetUpdatableStorage(payload)) {
        var operationalState = getOperationalState(crateStorage);
        operationalState.setCurrentActivityResponse(ActivityResponseDTO.create(ActivityResponse.RequestSuspend));
      }

      return payload;
    }

    /// <summary>
    /// Creates a terminate request for hub execution
    /// TODO: we could include a reason message with this request
    /// after that we could stop throwing exceptions on actions
    /// </summary>
    /// <param name="payload"></param>
    /// <returns></returns>
    protected PayloadDTO TerminateHubExecution(PayloadDTO payload, String message = null)
    {
      using (var crateStorage = CrateManager.GetUpdatableStorage(payload))
      {
        var operationalState = GetOperationalStateCrate(crateStorage);
        operationalState.CurrentActivityResponse = ActivityResponseDTO.Create(ActivityResponse.RequestTerminate);
        operationalState.CurrentActivityResponse.AddResponseMessageDTO(new ResponseMessageDTO { Message = message });
      }

      return payload;
    }

    protected PayloadDTO LaunchPlan(PayloadDTO payload, Guid targetPlanId)
    {
      using (var crateStorage = CrateManager.GetUpdatableStorage(payload))
      {
        var operationalState = GetOperationalStateCrate(crateStorage);
        operationalState.CurrentActivityResponse = ActivityResponseDTO.Create(ActivityResponse.LaunchAdditionalPlan);
        operationalState.CurrentActivityResponse.AddResponseMessageDTO(new ResponseMessageDTO { Details = targetPlanId });
      }

      return payload;
    }

    protected PayloadDTO JumpToSubplan(PayloadDTO payload, Guid targetSubplanId)
    {
      using (var crateStorage = CrateManager.GetUpdatableStorage(payload))
      {
        var operationalState = GetOperationalStateCrate(crateStorage);
        operationalState.CurrentActivityResponse = ActivityResponseDTO.Create(ActivityResponse.JumpToSubplan);
        operationalState.CurrentActivityResponse.AddResponseMessageDTO(new ResponseMessageDTO { Details = targetSubplanId });
      }

      return payload;
    }

    /// <summary>
    /// Jumps to an activity that resides in same subplan as current activity
    /// </summary>
    /// <param name="payload"></param>
    /// <returns></returns>
    protected PayloadDTO JumpToActivity(PayloadDTO payload, Guid targetActivityId)
    {
      using (var crateStorage = CrateManager.GetUpdatableStorage(payload))
      {
        var operationalState = GetOperationalStateCrate(crateStorage);
        operationalState.CurrentActivityResponse = ActivityResponseDTO.Create(ActivityResponse.JumpToActivity);
        operationalState.CurrentActivityResponse.AddResponseMessageDTO(new ResponseMessageDTO { Details = targetActivityId });
      }

      return payload;
    }

    /// <summary>
    /// Jumps to an activity that resides in same subplan as current activity
    /// </summary>
    /// <param name="payload"></param>
    /// <returns></returns>
    protected PayloadDTO LaunchAdditionalPlan(PayloadDTO payload, Guid targetSubplanId)
    {
      using (var crateStorage = CrateManager.GetUpdatableStorage(payload))
      {
        var operationalState = GetOperationalStateCrate(crateStorage);
        operationalState.CurrentActivityResponse = ActivityResponseDTO.Create(ActivityResponse.LaunchAdditionalPlan);
        operationalState.CurrentActivityResponse.AddResponseMessageDTO(new ResponseMessageDTO { Details = targetSubplanId });
      }

      return payload;
    }
  */
  /// <summary>
  /// returns success to hub
  /// </summary>
  /// <param name="payload"></param>
  /// <returns></returns>
  protected PayloadDTO success(PayloadDTO payload, String message) {
//    using (var crateStorage = CrateManager.GetUpdatableStorage(payload))
//    {
//      var operationalState = GetOperationalStateCrate(crateStorage);
//      operationalState.CurrentActivityResponse = ActivityResponseDTO.Create(ActivityResponse.Success);
//      operationalState.CurrentActivityResponse.AddResponseMessageDTO(new ResponseMessageDTO { Message = message });
//    }

    return payload;
  }
/*
  protected void Success(IUpdatableCrateStorage crateStorage, String message = "")
  {
    var operationalState = GetOperationalStateCrate(crateStorage);
    operationalState.CurrentActivityResponse = ActivityResponseDTO.Create(ActivityResponse.Success);
    operationalState.CurrentActivityResponse.AddResponseMessageDTO(new ResponseMessageDTO { Message = message });
  }

  protected PayloadDTO ExecuteClientActivity(PayloadDTO payload, String clientActionName)
  {
    using (var crateStorage = CrateManager.GetUpdatableStorage(payload))
    {
      var operationalState = GetOperationalStateCrate(crateStorage);
      operationalState.CurrentActivityResponse = ActivityResponseDTO.Create(ActivityResponse.ExecuteClientActivity);
      operationalState.CurrentClientActivityName = clientActionName;
    }

    return payload;
  }

  /// <summary>
  /// skips children of this action
  /// </summary>
  /// <param name="payload"></param>
  /// <returns></returns>
  protected void SkipChildren(IUpdatableCrateStorage crateStorage)
  {
    var operationalState = GetOperationalStateCrate(crateStorage);
    operationalState.CurrentActivityResponse = ActivityResponseDTO.Create(ActivityResponse.SkipChildren);
  }

  /// <summary>
  /// returns error to hub
  /// </summary>
  /// <param name="payload"></param>
  /// <param name="errorMessage"></param>
  /// <param name="errorCode"></param>
  /// <param name="currentActivity">Activity where the error occured</param>
  /// <param name="currentTerminal">Terminal where the error occured</param>
  /// <returns></returns>
  protected PayloadDTO Error(PayloadDTO payload, String errorMessage = null, ActivityErrorCode? errorCode = null, String currentActivity = null, String currentTerminal = null)
  {
    return Error(payload, errorMessage, ErrorType.Generic, errorCode, currentActivity, currentTerminal);
  }

  /// <summary>
  /// returns error to hub
  /// </summary>
  /// <param name="currentActivity">Activity where the error occured</param>
  /// <param name="currentTerminal">Terminal where the error occured</param>
  /// <returns></returns>
  protected PayloadDTO Error(PayloadDTO payload, String errorMessage, ErrorType errorType, ActivityErrorCode? errorCode = null, String currentActivity = null, String currentTerminal = null)
  {
    using (var crateStorage = CrateManager.GetUpdatableStorage(payload))
    {
      var operationalState = GetOperationalStateCrate(crateStorage);
      operationalState.CurrentActivityErrorCode = errorCode;
      operationalState.CurrentActivityResponse = ActivityResponseDTO.Create(ActivityResponse.Error);
      operationalState.CurrentActivityResponse.AddErrorDTO(ErrorDTO.Create(errorMessage, errorType, errorCode.ToString(), null, currentActivity, currentTerminal));
    }

    return payload;
  }

  /// <summary>
  /// returns error to hub
  /// </summary>
  /// <param name="payload"></param>
  /// <param name="errorMessage"></param>
  /// <param name="errorCode"></param>
  /// <param name="currentActivity">Activity where the error occured</param>
  /// <param name="currentTerminal">Terminal where the error occured</param>
  /// <returns></returns>
  protected void Error(IUpdatableCrateStorage crateStorage, String errorMessage = null, ActivityErrorCode? errorCode = null, String currentActivity = null, String currentTerminal = null)
  {
    var operationalState = GetOperationalStateCrate(crateStorage);
    operationalState.CurrentActivityErrorCode = errorCode;
    operationalState.CurrentActivityResponse = ActivityResponseDTO.Create(ActivityResponse.Error);
    operationalState.CurrentActivityResponse.AddErrorDTO(ErrorDTO.Create(errorMessage, ErrorType.Generic, errorCode.ToString(), null, currentActivity, currentTerminal));
  }

  /// <summary>
  /// Returns Needs authentication error to hub
  /// </summary>
  /// <param name="payload"></param>
  /// <returns></returns>
  protected PayloadDTO NeedsAuthenticationError(PayloadDTO payload)
  {
    return Error(payload, "No AuthToken provided.", ErrorType.Authentication, ActivityErrorCode.AUTH_TOKEN_NOT_PROVIDED_OR_INVALID);
  }

  /// <summary>
  /// Returns authentication error to hub
  /// </summary>
  /// <param name="payload"></param>
  /// <returns></returns>
  protected PayloadDTO InvalidTokenError(PayloadDTO payload, String instructionsToUser = null)
  {
    return Error(payload, instructionsToUser, ErrorType.Authentication, ActivityErrorCode.AUTH_TOKEN_NOT_PROVIDED_OR_INVALID);
  }

  protected async Task PushUserNotification(TerminalNotificationDTO notificationMessage)
  {
    await HubCommunicator.NotifyUser(notificationMessage, CurrentFr8UserId);
  }
*/
  public PayloadDTO executeChildActivities(ActivityDTO curActivityDTO, UUID containerId, AuthorizationTokenDTO authTokenDTO) {
    return success(getPayload(curActivityDTO, containerId), StringUtils.EMPTY);
//        Success(await GetPayload(curActivityDO, containerId));
  }
  public PayloadDTO run(ActivityDTO curActivityDTO, UUID containerId, AuthorizationTokenDTO authTokenDTO) {
    return success(getPayload(curActivityDTO, containerId), StringUtils.EMPTY);
  }
/*
  protected bool CheckAuthentication(ActivityDO activity, AuthorizationTokenDO authTokenDO)
  {
    if (NeedsAuthentication(authTokenDO))
    {
      AddAuthenticationCrate(activity, false);
      return true;
    }

    return false;
  }

  protected void AddAuthenticationCrate(ActivityDO activityDO, bool revocation)
  {
    using (var crateStorage = CrateManager.UpdateStorage(() => activityDO.CrateStorage))
    {
      var terminalAuthType = activityDO.ActivityTemplate.Terminal.AuthenticationType;

      AuthenticationMode mode;
      switch (terminalAuthType)
      {
        case AuthenticationType.Internal:
          mode = AuthenticationMode.InternalMode;
          break;
        case AuthenticationType.External:
          mode = AuthenticationMode.ExternalMode;
          break;
        case AuthenticationType.InternalWithDomain:
          mode = AuthenticationMode.InternalModeWithDomain;
          break;
        case AuthenticationType.None:
        default:
          mode = AuthenticationMode.ExternalMode;
          break;
      }

      crateStorage.Add(
          CrateManager.CreateAuthenticationCrate("RequiresAuthentication", mode, revocation)
      );
    }
  }

  public virtual bool NeedsAuthentication(AuthorizationTokenDO authTokenDO)
  {
    return String.IsNullOrEmpty(authTokenDO?.Token);
  }
*/
  protected PayloadDTO getPayload(ActivityDTO activityDTO, UUID containerId) {
    return hubCommunicator.getPayload(activityDTO, containerId, currentFr8UserId);
  }
/*
  protected async Task<UserDTO> GetCurrentUserData(ActivityDO activityDO, Guid containerId)
  {
    return await HubCommunicator.GetCurrentUser(activityDO, containerId, CurrentFr8UserId);
  }

  protected async Task<PlanDTO> GetPlansByActivity(String activityId)
  {
    return await HubCommunicator.GetPlansByActivity(activityId, CurrentFr8UserId);
  }

  protected async Task<PlanDTO> UpdatePlan(PlanEmptyDTO plan)
  {
    return await HubCommunicator.UpdatePlan(plan, CurrentFr8UserId);
  }

  protected async Task<Crate> ValidateFields(List<FieldValidationDTO> requiredFieldList)
  {
    var result = await HubCommunicator.ValidateFields(requiredFieldList, CurrentFr8UserId);

    var validationErrorList = new List<FieldDTO>();
    //lets create necessary validationError crates
    for (var i = 0; i < result.Count; i++)
    {
      var fieldCheckResult = result[i];
      if (fieldCheckResult == FieldValidationResult.NotExists)
      {
        validationErrorList.Add(new FieldDTO() { Key = requiredFieldList[i].FieldName, Value = "Required" });
      }
    }

    if (validationErrorList.Any())
    {
      return CrateManager.CreateDesignTimeFieldsCrate("Validation Errors", validationErrorList.ToArray());
    }

    return null;
  }

  protected async Task<CrateDTO> ValidateByStandartDesignTimeFields(ActivityDO curActivityDO, FieldDescriptionsCM designTimeFields)
  {
    var fields = designTimeFields.Fields;
    var validationList = fields.Select(f => new FieldValidationDTO(curActivityDO.Id, f.Key)).ToList();
    return CrateManager.ToDto(await ValidateFields(validationList));
  }
*/
  //if the Action doesn't provide a specific method to override this, we just return null = no validation errors
  protected ICrateStorage validateActivity(ActivityDTO curActivityDO) {
    return null;
//    return await Task.FromResult<ICrateStorage>(null);
  }

  protected ActivityDTO processConfigurationRequest(ActivityDTO curActivityDTO,
                                                    AuthorizationTokenDTO authToken)
  throws TerminalConfigurationException {

    ConfigurationRequestType configRequestType = configurationEvaluationResult(curActivityDTO);
    if (configRequestType == ConfigurationRequestType.INITIAL) {

      return initialConfigurationResponse(curActivityDTO, authToken);

    } else if (configRequestType == ConfigurationRequestType.FOLLOWUP) {
      ICrateStorage validationErrors = validateActivity(curActivityDTO);
      if (validationErrors != null) {
//        IUpdatableCrateStorage crateStorage = CrateManager.getUpdatableStorage(curActivityDTO);
//          crateStorage.AddRange(validationErrors);

        return curActivityDTO;
      }

      //clean any existing crates with "Validation Errors" that can be present from previous
//      using (var crateStorage = CrateManager.GetUpdatableStorage(curActivityDTO))
//      {
//        crateStorage.RemoveByLabel("Validation Errors");
//      }

      ActivityDTO result = followupConfigurationResponse(curActivityDTO, authToken);
      updateRuntimeAvailableCrates(result);
      return result;
    }

    throw new TerminalConfigurationException("Activity's Configuration Store does not contain valid ConfigurationRequestType field for " + configRequestType);
  }


  /**
   * TODO: Pending implementation of lambda method in ICrateManager
   * @param curActivityDTO the ActivityDTO to use to update the crates
   */
  private void updateRuntimeAvailableCrates(ActivityDTO curActivityDTO) {
/*    using (var activityStorage = CrateManager.getUpdatableStorage(curActivityDTO)) {
      activityStorage.RemoveByLabel(RuntimeAvailableCratesLabel);
      var crateDescriptions = GetRuntimeAvailableCrateDescriptions(curActivityDO)?.ToArray();
      if (crateDescriptions?.Length > 0)
      {
        activityStorage.Add(Crate.FromContent(RuntimeAvailableCratesLabel, new CrateDescriptionCM(crateDescriptions), AvailabilityType.RunTime));
      }
    }*/
  }
  /*
  /// <summary>
  /// Returns the list of items that describe crates available at runtime for downstream activities
  /// NOTE: do not create <see cref="Hub.Managers.IUpdatableCrateStorage" /> for passed activity inside this method
  /// as it may lead to unpredictable consequences
  /// </summary>
  protected virtual IEnumerable<CrateDescriptionDTO> GetRuntimeAvailableCrateDescriptions(ActivityDO curActivityDO)
  {
    yield break;
  }

  /// <summary>
  /// Configure infrastructure.
  /// </summary>
  */
  public ActivityDTO configure(ActivityDTO activityDO, AuthorizationTokenDTO authTokenDO)
  throws TerminalConfigurationException {
    return processConfigurationRequest(activityDO, /* ConfigurationEvaluator, */authTokenDO);
  }
/*
  /// <summary>
  /// This method "evaluates" as to what configuration should be called. 
  /// Every terminal action will have its own decision making; hence this method must be implemented in the relevant child class.
  /// </summary>
  /// <param name="curActivityDO"></param>
  /// <returns></returns>
  public virtual ConfigurationRequestType ConfigurationEvaluator(ActivityDO curActivityDO)
  {
    throw new NotImplementedException("ConfigurationEvaluator method not implemented in child class.");
  }
*/
  //if the Action doesn't provide a specific method to override this, we just return the existing CrateStorage, unchanged
  protected ActivityDTO initialConfigurationResponse(ActivityDTO curActivityDO, AuthorizationTokenDTO authTokenDO) {
    //Returns Task<ActivityDTO> using FromResult as the return type is known
    return curActivityDO;
//    return await Task.FromResult<ActivityDO>(curActivityDO);
  }

  public ActivityDTO activate(ActivityDTO curActivityDTO, AuthorizationTokenDTO authTokenDTO)
  {
    //Returns Task<ActivityDTO> using FromResult as the return type is known
    ICrateStorage validationErrors = validateActivity(curActivityDTO);
    if (validationErrors != null) {
//      using (var crateStorage = CrateManager.GetUpdatableStorage(curActivityDO)) {
//        crateStorage.AddRange(validationErrors);
//      }
      return curActivityDTO;
    }
    return curActivityDTO;
  }
/*
  public virtual async Task<ActivityDO> Deactivate(ActivityDO curActivityDO)
  {
    //Returns Task<ActivityDTO> using FromResult as the return type is known
    return await Task.FromResult<ActivityDO>(curActivityDO);
  }
*/
  //if the Action doesn't provide a specific method to override this, we just return the existing CrateStorage, unchanged
  protected ActivityDTO followupConfigurationResponse(ActivityDTO curActivityDO,
                                                      AuthorizationTokenDTO authTokenDO) {
    //Returns Task<ActivityDTO> using FromResult as the return type is known
    return curActivityDO;
//    return await Task.FromResult<ActivityDO>(curActivityDO);
  }
/*


  protected void UpdateDesignTimeCrateValue(ICrateStorage storage, String label, params FieldDTO[] fields)
  {
    var crate = storage.CratesOfType<FieldDescriptionsCM>().FirstOrDefault(x => x.Label == label);

    if (crate == null)
    {
      crate = CrateManager.CreateDesignTimeFieldsCrate(label, fields);

      storage.Add(crate);
    }
    else
    {
      crate.Content.Fields.Clear();
      crate.Content.Fields.AddRange(fields);
    }
  }

  protected async Task<ActivityTemplateDTO> GetActivityTemplate(Guid activityTemplateId)
  {
    var allActivityTemplates = _activityTemplateCache ?? (_activityTemplateCache = await HubCommunicator.GetActivityTemplates(CurrentFr8UserId));

    var foundActivity = allActivityTemplates.FirstOrDefault(a => a.Id == activityTemplateId);


    if (foundActivity == null)
    {
      throw new Exception($"ActivityTemplate was not found. Id: {activityTemplateId}");
    }

    return foundActivity;
  }

  protected async Task<ActivityTemplateDTO> GetActivityTemplate(String terminalName, String activityTemplateName, String activityTemplateVersion = "1", String terminalVersion = "1")
  {
    var allActivityTemplates = _activityTemplateCache ?? (_activityTemplateCache = await HubCommunicator.GetActivityTemplates(CurrentFr8UserId));

    var foundActivity =
        allActivityTemplates.FirstOrDefault(
            a =>
            a.Terminal.Name == terminalName && a.Terminal.Version == terminalVersion &&
            a.Name == activityTemplateName && a.Version == activityTemplateVersion);


    if (foundActivity == null)
    {
      throw new Exception($"ActivityTemplate was not found. TerminalName: {terminalName}\nTerminalVersion: {terminalVersion}\nActivitiyTemplateName: {activityTemplateName}\nActivityTemplateVersion: {activityTemplateVersion}");
    }

    return foundActivity;
  }

  /// <summary>
  /// DON'T USE THIS FUNCTION THIS IS JUST FOR BACKWARD COMPABILITY !!
  /// </summary>
  /// <param name="terminalName"></param>
  /// <param name="activityTemplateName"></param>
  /// <param name="activityTemplateVersion"></param>
  /// <param name="terminalVersion"></param>
  /// <returns></returns>
  [Obsolete("This function is for backward comatibility only. Please use Task<ActivityTemplateDTO> GetActivityTemplate(String, String, String, String)")]
  protected async Task<ActivityTemplateDTO> GetActivityTemplateByName(String activityTemplateName)
  {
    var allActivityTemplates = _activityTemplateCache ?? (_activityTemplateCache = await HubCommunicator.GetActivityTemplates(CurrentFr8UserId));
    var foundActivity = allActivityTemplates.FirstOrDefault(a => a.Name == activityTemplateName);

    if (foundActivity == null)
    {
      throw new Exception($"ActivityTemplate was not found. ActivitiyTemplateName: {activityTemplateName}");
    }

    return foundActivity;
  }

  protected async Task<ActivityDO> AddAndConfigureChildActivity(Guid parentActivityId, ActivityTemplateDTO activityTemplate, String name = null, String label = null, int? order = null)
  {

    //assign missing properties
    label = String.IsNullOrEmpty(label) ? activityTemplate.Label : label;
    name = String.IsNullOrEmpty(name) ? activityTemplate.Label : label;


    //parent must be a SubPlan
    //If Plan is specified as a parent, then a new subPlan will be created
    //Guid parentId = (parent.ChildNodes.Count > 0) ? parent.ChildNodes[0].ParentPlanNodeId.Value : parent.RootPlanNodeId.Value;

    var result = await HubCommunicator.CreateAndConfigureActivity(activityTemplate.Id, CurrentFr8UserId, label, order, parentActivityId);
    var resultDO = Mapper.Map<ActivityDO>(result);
    return resultDO;
  }

  protected async Task<ActivityDO> AddAndConfigureChildActivity(ActivityDO parent, ActivityTemplateDTO activityTemplate, String name = null, String label = null, int? order = null)
  {

    var resultDO = await AddAndConfigureChildActivity(parent.Id, activityTemplate, name, label, order);

    if (resultDO != null)
    {
      parent.ChildNodes.Add(resultDO);
      return resultDO;
    }

    return null;
  }


  protected async Task<ActivityDO> ConfigureChildActivity(ActivityDO parent, ActivityDO child)
  {
    var result = await HubCommunicator.ConfigureActivity(child, CurrentFr8UserId);
    parent.ChildNodes.Remove(child);
    parent.ChildNodes.Add(result);
    return result;
  }


  /// <summary>
  /// Update Plan name if the current Plan name is the same as the passed parameter OriginalPlanName to avoid overwriting the changes made by the user
  /// </summary>
  /// <param name="activityDO"></param>
  /// <param name="OriginalPlanName"></param>
  /// <returns></returns>
  public async Task<PlanFullDTO> UpdatePlanName(Guid activityId, String OriginalPlanName, String NewPlanName)
  {
    try
    {
      PlanDTO plan = await GetPlansByActivity(activityId.ToString());
      if (plan != null && plan.Plan.Name.Equals(OriginalPlanName, StringComparison.OrdinalIgnoreCase))
      {
        plan.Plan.Name = NewPlanName;

        var emptyPlanDTO = Mapper.Map<PlanEmptyDTO>(plan.Plan);
        plan = await UpdatePlan(emptyPlanDTO);
      }

      return plan.Plan;

    }
    catch (Exception ex)
    {
    }
    return null;
  }

  public async Task<PlanFullDTO> UpdatePlanCategory(Guid activityId, String category)
  {
    PlanDTO plan = await GetPlansByActivity(activityId.ToString());
    if (plan != null && plan.Plan != null)
    {
      plan.Plan.Category = category;

      var emptyPlanDTO = Mapper.Map<PlanEmptyDTO>(plan.Plan);
      plan = await UpdatePlan(emptyPlanDTO);
    }

    return plan.Plan;
  }


  public ActivityResponseDTO GenerateDocumentationResponse(String documentation)
  {
    return new ActivityResponseDTO
    {
      Body = documentation,
          Type = ActivityResponse.ShowDocumentation.ToString()
    };
  }
  public ActivityResponseDTO GenerateErrorResponse(String errorMessage)
  {
    return new ActivityResponseDTO
    {
      Body = errorMessage,
          Type = ActivityResponse.ShowDocumentation.ToString()
    };
  }

  public SolutionPageDTO GetDefaultDocumentation(String solutionName, double solutionVersion, String terminalName, String body)
  {
    var curSolutionPage = new SolutionPageDTO
    {
      Name = solutionName,
          Version = solutionVersion,
          Terminal = terminalName,
          Body = body
    };

    return curSolutionPage;
  }


  // Working with payload values


  /// <summary>
  /// Extracts crate with specified label and ManifestType = Standard Design Time,
  /// then extracts field with specified fieldKey.
  /// </summary>
  protected String ExtractPayloadFieldValue(ICrateStorage crateStorage, String fieldKey, ActivityDO curActivity)
  {
    var fieldValues = crateStorage.CratesOfType<StandardPayloadDataCM>().SelectMany(x => x.Content.GetValues(fieldKey))
    .Where(s => !String.IsNullOrEmpty(s))
    .ToArray();

    if (fieldValues.Length > 0)
      return fieldValues[0];

    var baseEvent = new BaseTerminalEvent();
    var exceptionMessage = String.Format("No field found with specified key: {0}.", fieldKey);
    //This is required for proper logging of the Incidents
    var fr8UserId = curActivity.AuthorizationToken.UserID;
    baseEvent.SendTerminalErrorIncident(ActivityName, exceptionMessage, ActivityName, fr8UserId);

    throw new ApplicationException(exceptionMessage);
  }


  // Working with upstream


  //wrapper for support test method
  public virtual async Task<List<Crate<TManifest>>> GetCratesByDirection<TManifest>(ActivityDO activityDO, CrateDirection direction)
  {
    return await HubCommunicator.GetCratesByDirection<TManifest>(activityDO, direction, CurrentFr8UserId);
    // return await Activity.GetCratesByDirection<TManifest>(activityId, direction);
  }

  public Task<IncomingCratesDTO> GetAvailableData(ActivityDO activity, CrateDirection direction = CrateDirection.Upstream, AvailabilityType availability = AvailabilityType.RunTime)
  {
    return HubCommunicator.GetAvailableData(activity, direction, availability, CurrentFr8UserId);
  }

  //wrapper for support test method
  public virtual async Task<List<Crate>> GetCratesByDirection(ActivityDO activityDO, CrateDirection direction)
  {
    return await HubCommunicator.GetCratesByDirection(activityDO, direction, CurrentFr8UserId);
  }

  public virtual async Task<FieldDescriptionsCM> GetDesignTimeFields(ActivityDO activityDO, CrateDirection direction, AvailabilityType availability = AvailabilityType.NotSet)
  {
    var mergedFields = await HubCommunicator.GetDesignTimeFieldsByDirection(activityDO, direction, availability, CurrentFr8UserId);
    return mergedFields;
  }

  public virtual IEnumerable<FieldDTO> GetRequiredFields(ActivityDO curActivityDO, String crateLabel)
  {
    using (var crateStorage = CrateManager.GetUpdatableStorage(curActivityDO))
    {
      var requiredFields = crateStorage
          .CrateContentsOfType<FieldDescriptionsCM>(c => c.Label.Equals(crateLabel))
      .SelectMany(f => f.Fields.Where(s => s.IsRequired));

      return requiredFields;
    }
  }

  public virtual async Task<List<CrateManifestType>> BuildUpstreamManifestList(ActivityDO activityDO)
  {
    var upstreamCrates = await this.GetCratesByDirection<Data.Interfaces.Manifests.Manifest>(activityDO, CrateDirection.Upstream);
    return upstreamCrates.Where(x => !ExcludedManifestTypes.Contains(x.ManifestType)).Select(f => f.ManifestType).Distinct().ToList();
  }

  public virtual async Task<List<String>> BuildUpstreamCrateLabelList(ActivityDO activityDO)
  {
    var curCrates = await this.GetCratesByDirection<Data.Interfaces.Manifests.Manifest>(activityDO, CrateDirection.Upstream);
    return curCrates.Where(x => !ExcludedManifestTypes.Contains(x.ManifestType)).Select(f => f.Label).Distinct().ToList();
  }

  public virtual async Task<Crate<FieldDescriptionsCM>> GetUpstreamManifestListCrate(ActivityDO activityDO)
  {
    var manifestList = (await BuildUpstreamManifestList(activityDO));
    var fields = manifestList.Select(f => new FieldDTO(f.Type, f.Id.ToString())).ToArray();

    return CrateManager.CreateDesignTimeFieldsCrate("AvailableUpstreamManifests", fields);
  }

  public virtual async Task<Crate<FieldDescriptionsCM>> GetUpstreamCrateLabelListCrate(ActivityDO activityDO)
  {
    var labelList = (await BuildUpstreamCrateLabelList(activityDO));
    var fields = labelList.Select(f => new FieldDTO(f, f)).ToArray();

    return CrateManager.CreateDesignTimeFieldsCrate("AvailableUpstreamLabels", fields);
  }


  protected virtual async Task<List<Crate<StandardFileDescriptionCM>>> GetUpstreamFileHandleCrates(ActivityDO activityDO)
  {
    return await HubCommunicator.GetCratesByDirection<StandardFileDescriptionCM>(activityDO, CrateDirection.Upstream, CurrentFr8UserId);
  }

  protected async Task<Crate<FieldDescriptionsCM>> MergeUpstreamFields(ActivityDO activityDO, String label)
  {
    var curUpstreamFields = (await GetDesignTimeFields(activityDO, CrateDirection.Upstream)).Fields.ToArray();
    var upstreamFieldsCrate = CrateManager.CreateDesignTimeFieldsCrate(label, curUpstreamFields);

    return upstreamFieldsCrate;
  }

  /// <summary>
  /// Creates a crate with available design-time fields.
  /// </summary>
  /// <param name="activityDO">ActionDO.</param>
  /// <returns></returns>
  protected async Task<Crate> CreateAvailableFieldsCrate(ActivityDO activityDO,
                                                         String crateLabel = "Upstream Terminal-Provided Fields",
                                                         AvailabilityType availabilityTypeUpstream = AvailabilityType.RunTime,
                                                         AvailabilityType availabilityTypeFieldsCrate = AvailabilityType.Configuration)
  {
    var curUpstreamFields = await HubCommunicator.GetDesignTimeFieldsByDirection(activityDO, CrateDirection.Upstream, availabilityTypeUpstream, CurrentFr8UserId) ?? new FieldDescriptionsCM();

    var availableFieldsCrate = CrateManager.CreateDesignTimeFieldsCrate(
        crateLabel,
        curUpstreamFields.Fields,
        availabilityTypeFieldsCrate);

    return availableFieldsCrate;
  }

  // create crate with list of fields available upstream
  protected async Task<Crate<FieldDescriptionsCM>> CreateDesignTimeFieldsCrate(ActivityDO curActivityDO, String label)
  {
    List<Crate<FieldDescriptionsCM>> crates = null;

    try
    {
      //throws exception from test classes when it cannot call webservice
      crates = await GetCratesByDirection<FieldDescriptionsCM>(curActivityDO, CrateDirection.Upstream);
    }
    catch { }

    if (crates != null)
    {
      var upstreamFields = crates.SelectMany(x => x.Content.Fields).Where(a => a.Availability != AvailabilityType.Configuration).ToArray();

      return CrateManager.CreateDesignTimeFieldsCrate(label, upstreamFields);
    }

    return null;
  }

  /// <summary>
  /// Extract upstream data based on Upstream Crate Chooser Control's selected manifest and label 
  /// </summary>
  /// <param name="upstreamCrateChooserName">Upstream Crate Chooser Control's name</param>
  /// <param name="curActivityDO"></param>
  /// <returns>StandardTableDataCM. StandardTableDataCM.Table.Count = 0 if its empty</returns>
  protected async Task<StandardTableDataCM> ExtractDataFromUpstreamCrates(String upstreamCrateChooserName, ActivityDO curActivityDO)
  {
    var controls = GetConfigurationControls(curActivityDO);
    var crateChooser = controls.FindByName<UpstreamCrateChooser>(upstreamCrateChooserName);

    //Get selected manifest and label from crate chooser
    var manifestType = crateChooser.SelectedCrates.Where(w => !String.IsNullOrEmpty(w.ManifestType.Value)).Select(c => c.ManifestType.Value);
    var labelControl = crateChooser.SelectedCrates.Where(w => !String.IsNullOrEmpty(w.Label.Value)).Select(c => c.Label.Value);

    //filter by manifest or label based on selected crate chooser control
    Func<Crate, bool> whereClause = null;
    if (manifestType.Any() && labelControl.Any())
      whereClause = (crate => manifestType.Contains(crate.ManifestType.Id.ToString()) && labelControl.Contains(crate.Label));
    else if (manifestType.Any())
    whereClause = (crate => manifestType.Contains(crate.ManifestType.Id.ToString()));
    else if (labelControl.Any())
    whereClause = (crate => labelControl.Contains(crate.Label));


    //get upstream controls to extract the data based on the selected manifest or label
    var getUpstreamCrates = (await GetCratesByDirection(curActivityDO, CrateDirection.Upstream)).ToArray();
    var selectedUpcrateControls = getUpstreamCrates.Where(whereClause).Select(s => s);//filter upstream controls

    StandardTableDataCM resultTable = new StandardTableDataCM();
    foreach (var selectedUpcrateControl in selectedUpcrateControls)
    {
      if (selectedUpcrateControl != null)
      {
        //for standard table data CM return directly the result - the rest of CM will be converted to TableDataCM
        if (selectedUpcrateControl.IsOfType<StandardTableDataCM>())
        {
          var contentTableCM = selectedUpcrateControl.Get<StandardTableDataCM>();
          resultTable.Table.AddRange(contentTableCM.Table);//add rows into results StandardTableDataCM
        }
        else
          throw new NotSupportedException("Manifest type is not yet implemented in extracting the data.");
      }
    }

    return resultTable;
  }


  // Working with controls
  /*******************************************************************************************

  /// <summary>
  /// This is a generic function for creating a CrateChooser which is suitable for most use-cases
  /// </summary>
  /// <param name="curActivityDO"></param>
  /// <param name="label"></param>
  /// <param name="name"></param>
  /// <param name="singleManifest"></param>
  /// <returns></returns>
  protected async Task<CrateChooser> GenerateCrateChooser(
      ActivityDO curActivityDO,
      String name,
      String label,
      bool singleManifest,
      bool requestUpstream = false,
      bool requestConfig = false)
  {
    var crateDescriptions = await GetCratesByDirection<CrateDescriptionCM>(curActivityDO, CrateDirection.Upstream);
    var runTimeCrateDescriptions = crateDescriptions.Where(c => c.Availability == AvailabilityType.RunTime || c.Availability == AvailabilityType.Always).SelectMany(c => c.Content.CrateDescriptions);
    var control = new CrateChooser
    {
      Label = label,
          Name = name,
          CrateDescriptions = runTimeCrateDescriptions.ToList(),
          SingleManifestOnly = singleManifest,
          RequestUpstream = requestUpstream
    };

    if (requestConfig)
    {
      control.Events.Add(new ControlEvent("onChange", "requestConfig"));
    }

    return control;
  }

  /// <summary>
  /// Creates StandardConfigurationControlsCM with TextSource control
  /// </summary>
  /// <param name="storage">Crate Storage</param>
  /// <param name="label">Initial Label for the text source control</param>
  /// <param name="controlName">Name of the text source control</param>
  /// <param name="upstreamSourceLabel">Label for the upstream source</param>
  /// <param name="filterByTag">Filter for upstream source, Empty by default</param>
  /// <param name="addRequestConfigEvent">True if onChange event needs to be configured, False otherwise. True by default</param>
  /// <param name="required">True if the control is required, False otherwise. False by default</param>
  protected void AddTextSourceControl(
      ICrateStorage storage,
      String label,
      String controlName,
      String upstreamSourceLabel,
      String filterByTag = "",
      bool addRequestConfigEvent = false,
      bool required = false,
      bool requestUpstream = false)
  {
    var textSourceControl = CreateSpecificOrUpstreamValueChooser(
        label,
        controlName,
        upstreamSourceLabel,
        filterByTag,
        addRequestConfigEvent,
        requestUpstream
    );
    textSourceControl.Required = required;

    AddControl(storage, textSourceControl);
  }

  /// <summary>
  /// Creates RadioButtonGroup to enter specific value or choose value from upstream crate.
  /// </summary>
  protected TextSource CreateSpecificOrUpstreamValueChooser(
      String label,
      String controlName,
      String upstreamSourceLabel = "",
      String filterByTag = "",
      bool addRequestConfigEvent = false,
      bool requestUpstream = false)
  {
    var control = new TextSource(label, upstreamSourceLabel, controlName)
    {
      Source = new FieldSourceDTO
      {
        Label = upstreamSourceLabel,
            ManifestType = CrateManifestTypes.StandardDesignTimeFields,
            FilterByTag = filterByTag,
            RequestUpstream = requestUpstream
      }
    };
    if (addRequestConfigEvent)
    {
      control.Events.Add(new ControlEvent("onChange", "requestConfig"));
    }

    return control;
  }

  protected UpstreamCrateChooser CreateUpstreamCrateChooser(String name, String label, bool isMultiSelection = true)
  {

    var manifestDdlb = new DropDownList { Name = name + "_mnfst_dropdown_0", Source = new FieldSourceDTO(CrateManifestTypes.StandardDesignTimeFields, "AvailableUpstreamManifests") };
    var labelDdlb = new DropDownList { Name = name + "_lbl_dropdown_0", Source = new FieldSourceDTO(CrateManifestTypes.StandardDesignTimeFields, "AvailableUpstreamLabels") };

    var ctrl = new UpstreamCrateChooser
    {
      Name = name,
          Label = label,
          SelectedCrates = new List<CrateDetails> { new CrateDetails { Label = labelDdlb, ManifestType = manifestDdlb } },
      MultiSelection = isMultiSelection
    };

    return ctrl;
  }


  /// <summary>
  /// Creates TextBlock control and fills it with label, value and CssClass
  /// </summary>
  /// <param name="curLabel">Label</param>
  /// <param name="curValue">Value</param>
  /// <param name="curCssClass">Css Class</param>
  /// <param name="curName">Name</param>
  /// <returns>TextBlock control</returns>
  protected TextBlock GenerateTextBlock(String curLabel, String curValue, String curCssClass, String curName = "unnamed")
  {
    return new TextBlock
    {
      Name = curName,
          Label = curLabel,
          Value = curValue,
          CssClass = curCssClass
    };
  }

  /*******************************************************************************************
  // Deprecated methods
  // Can be refactored if necessary
  /*******************************************************************************************

  //looks for the Configuration Controls Crate and Extracts the ManifestSchema
  protected StandardConfigurationControlsCM GetControlsManifest(ActivityDO curActivity)
  {
    var control = CrateManager.GetStorage(curActivity.CrateStorage).CrateContentsOfType<StandardConfigurationControlsCM>().FirstOrDefault();

    if (control == null)
    {
      throw new ApplicationException($"No crate found with Label == \"Configuration_Controls\" and ManifestType == \"{CrateManifestTypes.StandardConfigurationControls}\"");
    }

    return control;
  }

  protected ControlDefinitionDTO GetControl(StandardConfigurationControlsCM controls, String name, String controlType = null)
  {
    Func<ControlDefinitionDTO, bool> predicate = x => x.Name == name;
    if (controlType != null)
    {
      predicate = x => x.Type == controlType && x.Name == name;
    }

    return controls.Controls.FirstOrDefault(predicate);
  }

  protected T GetControl<T>(StandardConfigurationControlsCM controls, String name, String controlType = null) where T : ControlDefinitionDTO
  {
    return (T)GetControl(controls, name, controlType);
  }

  protected ControlDefinitionDTO GetControl(ActivityDO curActivityDO, String name, String controlType = null)
  {
    var controls = GetConfigurationControls(curActivityDO);
    return GetControl(controls, name, controlType);
  }

  // do not use after EnhancedTerminalActivity is introduced
  protected ICrateStorage AssembleCrateStorage(params Crate[] curCrates)
  {
    return new CrateStorage(curCrates);
  }

  // do not use after EnhancedTerminalActivity is introduced
  protected Crate PackControls(StandardConfigurationControlsCM page)
  {
    return PackControlsCrate(page.Controls.ToArray());
  }

  // do not use after EnhancedTerminalActivity is introduced
  protected Crate<StandardConfigurationControlsCM> PackControlsCrate(params ControlDefinitionDTO[] controlsList)
  {
    return Crate<StandardConfigurationControlsCM>.FromContent(ConfigurationControlsLabel, new StandardConfigurationControlsCM(controlsList), AvailabilityType.Configuration);
  }

  // do not use after EnhancedTerminalActivity is introduced
  protected String ExtractControlFieldValue(ActivityDO curActivityDO, String fieldName)
  {
    var storage = CrateManager.GetStorage(curActivityDO);
    var controlsCrateMS = storage.CrateContentsOfType<StandardConfigurationControlsCM>().FirstOrDefault();
    var field = controlsCrateMS?.Controls.FirstOrDefault(x => x.Name == fieldName);

    return field?.Value;
  }


  protected void AddLabelControl(ICrateStorage storage, String name, String label, String text)
  {
    AddControl(
        storage,
        GenerateTextBlock(label, text, "well well-lg", name)
    );
  }

  protected void AddControl(ICrateStorage storage, ControlDefinitionDTO control)
  {
    var controlsCrate = EnsureControlsCrate(storage);

    if (controlsCrate.Content == null) { return; }

    controlsCrate.Content.Controls.Add(control);
  }

  protected void InsertControlAfter(ICrateStorage storage, ControlDefinitionDTO control, String afterControlName)
  {
    var controlsCrate = EnsureControlsCrate(storage);

    if (controlsCrate.Content == null) { return; }

    for (var i = 0; i < controlsCrate.Content.Controls.Count; ++i)
    {
      if (controlsCrate.Content.Controls[i].Name == afterControlName)
      {
        if (i == controlsCrate.Content.Controls.Count - 1)
        {
          controlsCrate.Content.Controls.Add(control);
        }
        else
        {
          controlsCrate.Content.Controls.Insert(i + 1, control);
        }

        break;
      }
    }
  }

  protected ControlDefinitionDTO FindControl(ICrateStorage storage, String name)
  {
    var controlsCrate = storage.CrateContentsOfType<StandardConfigurationControlsCM>().FirstOrDefault();

    if (controlsCrate == null) { return null; }

    var control = controlsCrate.Controls
        .FirstOrDefault(x => x.Name == name);

    return control;
  }


  /// <summary>
  /// specify selected FieldDO for DropDownList
  /// specify Value (TimeSpan) for Duration
  /// </summary>
  protected void SetControlValue(ActivityDO activity, String controlFullName, object value)
  {
    using (var crateStorage = CrateManager.GetUpdatableStorage(activity))
    {
      var controls = crateStorage
          .CrateContentsOfType<StandardConfigurationControlsCM>()
          .FirstOrDefault();

      if (controls != null)
      {
        var control = TraverseNestedControls(controls.Controls, controlFullName);

        if (control != null)
        {
          switch (control.Type)
          {
            case "TextBlock":
            case "TextBox":
            case "BuildMessageAppender":
            case ControlTypes.TextArea:
              control.Value = (String)value;
              break;

            case "CheckBox":
              control.Selected = true;
              break;

            case "DropDownList":
              var ddlb = control as DropDownList;
              var val = value as ListItem;
              ddlb.selectedKey = val.Key;
              ddlb.Value = val.Value;
              //ddlb.ListItems are not loaded yet
              break;

            case "Duration":
              var duration = control as Duration;
              var timespan = (TimeSpan)value;
              duration.Days = timespan.Days;
              duration.Hours = timespan.Hours;
              duration.Minutes = timespan.Minutes;
              break;

            default:
              throw new NotSupportedException($"Unsupported control type {control.Type}");
          }
        }
      }
    }
  }

  private ControlDefinitionDTO TraverseNestedControls(List<ControlDefinitionDTO> controls, String childControl)
  {
    ControlDefinitionDTO controlDefinitionDTO = null;
    var controlNames = childControl.Split(new char[] { '.' }, StringSplitOptions.RemoveEmptyEntries);
    if (controlNames.Length > 0 && controls.Count > 0)
    {
      var control = controls.FirstOrDefault(a => a.Name == controlNames[0]);

      if (control != null)
      {
        if (controlNames.Count() == 1)
        {
          controlDefinitionDTO = control;
        }
        else
        {
          List<ControlDefinitionDTO> nestedControls = null;

          if (control.Type == "RadioButtonGroup")
          {
            var radio = (control as RadioButtonGroup).Radios.FirstOrDefault(a => a.Name == controlNames[1]);
            if (radio != null)
            {
              radio.Selected = true;
              nestedControls = radio.Controls.ToList();

              controlDefinitionDTO = TraverseNestedControls(nestedControls, String.Join(".", controlNames.Skip(2)));
            }
          }
          //TODO: Add support for future controls with nested child controls
          else
            throw new NotImplementedException("Can't search for controls inside of " + control.Type);
        }
      }
    }

    return controlDefinitionDTO;
  }

  protected void RemoveControl(ICrateStorage storage, String name)
  {
    var controlsCrate = storage.CrateContentsOfType<StandardConfigurationControlsCM>().FirstOrDefault();

    if (controlsCrate == null) { return; }


    var control = controlsCrate.Controls.FirstOrDefault(x => x.Name == name);

    if (control != null)
    {
      controlsCrate.Controls.Remove(control);
    }
  }

  protected Crate<StandardConfigurationControlsCM> EnsureControlsCrate(ICrateStorage storage)
  {
    var controlsCrate = storage.CratesOfType<StandardConfigurationControlsCM>().FirstOrDefault();

    if (controlsCrate == null)
    {
      controlsCrate = CrateManager.CreateStandardConfigurationControlsCrate(ConfigurationControlsLabel);
      storage.Add(controlsCrate);
    }

    return controlsCrate;
  }

  public String ParseConditionToText(List<FilterConditionDTO> filterData)
  {
    var parsedConditions = new List<String>();

    filterData.ForEach(condition =>
        {
            String parsedCondition = condition.Field;

    switch (condition.Operator)
    {
      case "eq":
        parsedCondition += " = ";
        break;
      case "neq":
        parsedCondition += " != ";
        break;
      case "gt":
        parsedCondition += " > ";
        break;
      case "gte":
        parsedCondition += " >= ";
        break;
      case "lt":
        parsedCondition += " < ";
        break;
      case "lte":
        parsedCondition += " <= ";
        break;
      default:
        throw new NotSupportedException(String.Format("Not supported operator: {0}", condition.Operator));
    }

    parsedCondition += String.Format("'{0}'", condition.Value);
    parsedConditions.Add(parsedCondition);
    });

    var finalCondition = String.Join(" AND ", parsedConditions);

    return finalCondition;
  }

  // do not use after EnhancedTerminalActivity is introduced
  protected StandardConfigurationControlsCM GetConfigurationControls(ActivityDO curActivityDO)
  {
    var storage = CrateManager.GetStorage(curActivityDO);
    return GetConfigurationControls(storage);
  }

  // do not use after EnhancedTerminalActivity is introduced
  protected StandardConfigurationControlsCM GetConfigurationControls(ICrateStorage storage)
  {
    var controlsCrate = storage.CrateContentsOfType<StandardConfigurationControlsCM>(c => c.Label == ConfigurationControlsLabel).FirstOrDefault();
    return controlsCrate;
  }

  // do not use after EnhancedTerminalActivity is introduced
  /// <summary>
  /// Method to be used with Loop Action
  /// Is a helper method to decouple some of the GetCurrentElement Functionality
  /// </summary>
  /// <param name="operationalCrate">Crate of the OperationalStateCM</param>
  /// <returns>Index or pointer of the current IEnumerable Object</returns>
  protected int GetLoopIndex(OperationalStateCM operationalState)
  {
    var loopState = operationalState.CallStack.FirstOrDefault(x => x.LocalData?.Type == "Loop");

    if (loopState != null) //this is a loop related data request
    {
      return loopState.LocalData.ReadAs<OperationalStateCM.LoopStatus>().Index;
    }

    throw new NullReferenceException("No Loop was found inside the provided OperationalStateCM crate");
  }

  // do not use after EnhancedTerminalActivity is introduced
  /// <summary>
  /// Trivial method to return element at specified index of the IEnumerable object.
  /// To be used with Loop Action.
  /// IMPORTANT: 
  /// 1) Index update is performed by Loop Action
  /// 2) Loop brake is preformed by Loop Action
  /// </summary>
  /// <param name="enumerableObject">Object of type IEnumerable</param>
  /// <param name="objectIndex">Integer that points to the element</param>
  /// <returns>Object of any type</returns>
  protected object GetCurrentElement(IEnumerable<object> enumerableObject, int objectIndex)
  {
    var curElement = enumerableObject.ElementAt(objectIndex);
    return curElement;
  }

  protected OperationalStateCM GetOperationalStateCrate(ICrateStorage storage)
  {
    var operationalState = storage.CrateContentsOfType<OperationalStateCM>().SingleOrDefault();
    if (operationalState == null)
      throw new Exception("No Operational State Crate found.");
    return operationalState;
  }
  */
}
