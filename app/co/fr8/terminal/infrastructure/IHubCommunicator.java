package co.fr8.terminal.infrastructure;

import co.fr8.data.interfaces.dto.PayloadDTO;

import java.util.UUID;

/**
 * TODO: Implement
 */
public interface IHubCommunicator {
  /*Task*/ void configure(String terminalName);


  PayloadDTO getPayload(UUID containerIdd);
/*
  Task<UserDTO> GetCurrentUser(ActivityDO activityDO, Guid containerId, string userId);
  Task<FieldDescriptionsCM> GetDesignTimeFieldsByDirection(ActivityDO activityDO, CrateDirection direction, AvailabilityType availability, string userId);
  Task<IncomingCratesDTO> GetAvailableData(ActivityDO activityDO, CrateDirection direction, AvailabilityType availability, string userId);
  Task<List<Crate<TManifest>>> GetCratesByDirection<TManifest>(ActivityDO activityDO, CrateDirection direction, string userId);
  Task<List<Crate>> GetCratesByDirection(ActivityDO activityDO, CrateDirection direction, string userId);

  Task CreateAlarm(AlarmDTO alarmDTO, string userId);

  Task<List<ActivityTemplateDTO>> GetActivityTemplates(string userId);

  Task<List<ActivityTemplateDTO>> GetActivityTemplates(ActivityCategory category, string userId);

  Task<List<ActivityTemplateDTO>> GetActivityTemplates(string tag, string userId);
  Task<List<FieldValidationResult>> ValidateFields(List<FieldValidationDTO> fields, string userId);
  Task<AuthorizationToken> GetAuthToken(string authTokenId, string curFr8UserId);
  Task<ActivityPayload> ConfigureActivity(ActivityPayload activityDTO, string userId);
  Task<ActivityDO> SaveActivity(ActivityDO activityDO, string userId);
  Task<ActivityDO> ConfigureActivity(ActivityDO activityDO, string userId);
  Task<ActivityPayload> CreateAndConfigureActivity(Guid templateId, string userId, string label = null, int? order = null, Guid? parentNodeId = null, bool createPlan = false, Guid? authorizationTokenId = null);
  Task<PlanDTO> CreatePlan(PlanEmptyDTO planDTO, string userId);
  Task RunPlan(Guid planId, List<CrateDTO> payload, string userId);
  Task<PlanDO> ActivatePlan(PlanDO planDO, string userId);
  Task<List<CrateDTO>> GetStoredManifests(string currentFr8UserId, List<CrateDTO> cratesForMTRequest);
  Task<IEnumerable<PlanDTO>> GetPlansByName(string name, string userId, PlanVisibility visibility = PlanVisibility.Standard);
  Task<FileDO> SaveFile(string name, Stream stream, string userId);
  Task<Stream> DownloadFile(int fileId, string userId);
  Task<IEnumerable<FileDTO>> GetFiles(string userId);
  Task ApplyNewToken(Guid activityId, Guid authTokenId, string userId);

  bool IsConfigured { get; set; }
  Task DeletePlan(Guid planId, string userId);
  Task DeleteActivity(Guid curActivityId, string userId);
  Task DeleteExistingChildNodesFromActivity(Guid curActivityId, string userId);
  Task<PlanDTO> GetPlansByActivity(string activityId, string userId);
  Task<PlanDTO> UpdatePlan(PlanEmptyDTO plan, string userId);
  Task NotifyUser(TerminalNotificationDTO notificationMessage, string userId);
*/

}
