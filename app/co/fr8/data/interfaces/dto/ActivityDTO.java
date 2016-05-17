package co.fr8.data.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * TODO: Implement
 */
public class ActivityDTO {

  @JsonProperty("Label")
  private String label;

  private ActivityTemplateDTO activityTemplate;

  @JsonProperty("RootPlanNodeId")
  private UUID rootPlanNodeId;

  @JsonProperty("ParentPlanNodeId")
  private UUID parentPlanNodeId;

  @JsonProperty("CurrentView")
  private String currentView;

  @JsonProperty("Ordering")
  private int ordering;

  @JsonProperty("Id")
  private UUID id;

  @JsonProperty("CrateStorage")
  private CrateStorageDTO crateStorage;

  @JsonProperty("ChildrenActivities")
  private ActivityDTO[] childActivities;

  @JsonProperty("AuthToken")
  private AuthorizationTokenDTO authToken;

  @JsonProperty("Fr8AccountId")
  private String fr8AccountId;

  private String documentation;

  @JsonProperty("Name")
  private String name;

  public ActivityDTO() {
  }

  public ActivityDTO(String label, ActivityTemplateDTO activityTemplate, UUID rootPlanNodeId,
                     UUID parentPlanNodeId, String currentView, int ordering, UUID id,
                     CrateStorageDTO crateStorage, ActivityDTO[] childActivities,
                     AuthorizationTokenDTO authToken, String fr8AccountId, String documentation) {
    this.label = label;
    this.activityTemplate = activityTemplate;
    this.rootPlanNodeId = rootPlanNodeId;
    this.parentPlanNodeId = parentPlanNodeId;
    this.currentView = currentView;
    this.ordering = ordering;
    this.id = id;
    this.crateStorage = crateStorage;
    this.childActivities = childActivities;
    this.authToken = authToken;
    this.fr8AccountId = fr8AccountId;
    this.documentation = documentation;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public ActivityTemplateDTO getActivityTemplate() {
    return activityTemplate;
  }

  public void setActivityTemplate(ActivityTemplateDTO activityTemplate) {
    this.activityTemplate = activityTemplate;
  }

  public UUID getRootPlanNodeId() {
    return rootPlanNodeId;
  }

  public void setRootPlanNodeId(UUID rootPlanNodeId) {
    this.rootPlanNodeId = rootPlanNodeId;
  }

  public UUID getParentPlanNodeId() {
    return parentPlanNodeId;
  }

  public void setParentPlanNodeId(UUID parentPlanNodeId) {
    this.parentPlanNodeId = parentPlanNodeId;
  }

  public String getCurrentView() {
    return currentView;
  }

  public void setCurrentView(String currentView) {
    this.currentView = currentView;
  }

  public int getOrdering() {
    return ordering;
  }

  public void setOrdering(int ordering) {
    this.ordering = ordering;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public CrateStorageDTO getCrateStorage() {
    return crateStorage;
  }

  public void setCrateStorage(CrateStorageDTO crateStorage) {
    this.crateStorage = crateStorage;
  }

  public ActivityDTO[] getChildActivities() {
    return childActivities;
  }

  public void setChildActivities(ActivityDTO[] childActivities) {
    this.childActivities = childActivities;
  }

  public AuthorizationTokenDTO getAuthToken() {
    return authToken;
  }

  public void setAuthToken(AuthorizationTokenDTO authToken) {
    this.authToken = authToken;
  }

  public String getFr8AccountId() {
    return fr8AccountId;
  }

  public void setFr8AccountId(String fr8AccountId) {
    this.fr8AccountId = fr8AccountId;
  }

  public String getDocumentation() {
    return documentation;
  }

  public void setDocumentation(String documentation) {
    this.documentation = documentation;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /*
  public string Label { get; set; }

  [JsonProperty("activityTemplate")]
      [JsonConverter(typeof(ActivityTemplateActivityConverter))]
  public ActivityTemplateDTO ActivityTemplate { get; set; }

  public Guid? RootPlanNodeId { get; set; }

  public Guid? ParentPlanNodeId { get; set; }

  public string CurrentView { get; set; }

  public int Ordering { get; set; }

  public Guid Id { get; set; }

  public CrateStorageDTO CrateStorage { get; set; }

  public ActivityDTO[] ChildrenActivities { get; set; }

  public AuthorizationTokenDTO AuthToken { get; set; }


  [JsonIgnore]
  public string Fr8AccountId { get; set; }

  [JsonProperty("documentation")]
  public string Documentation { get; set; }
  */
}
