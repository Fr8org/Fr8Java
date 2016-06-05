package co.fr8.data.interfaces.dto;

import co.fr8.data.crates.Crate;
import co.fr8.data.crates.CrateStorage;
import co.fr8.terminal.base.ActivityPayload;
import co.fr8.util.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

/**
 * TODO: Implement
 */
public class ActivityDTO {

  @JsonProperty("Label")
  protected String label;

  protected ActivityTemplateDTO activityTemplate;

  @JsonProperty("RootPlanNodeId")
  protected UUID rootPlanNodeId;

  @JsonProperty("ParentPlanNodeId")
  protected UUID parentPlanNodeId;

  @JsonProperty("CurrentView")
  protected String currentView;

  @JsonProperty("Ordering")
  protected int ordering;

  @JsonProperty("Id")
  protected UUID id;

  @JsonProperty("CrateStorage")
  protected CrateStorageDTO crateStorage;

  @JsonProperty("ChildrenActivities")
  protected ActivityDTO[] childActivities;

  @JsonProperty("AuthTokenId")
  protected UUID authTokenId;

  @JsonProperty("AuthToken")
  protected AuthorizationTokenDTO authToken;

  @JsonProperty("Fr8AccountId")
  protected String fr8AccountId;

  protected String documentation;

  @JsonProperty("Name")
  protected String name;

  public ActivityDTO() {
  }

  public ActivityDTO(String label, ActivityTemplateDTO activityTemplate, UUID rootPlanNodeId,
                     UUID parentPlanNodeId, String currentView, int ordering, UUID id,
                     CrateStorageDTO crateStorage, ActivityDTO[] childActivities,
                     AuthorizationTokenDTO authToken, String fr8AccountId, String documentation,
                     UUID authTokenId) {
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
    this.authTokenId = authTokenId;
  }

  public ActivityDTO(ActivityPayload activityPayload) {
    this.id = activityPayload.getId();
    this.label = activityPayload.getLabel();
    this.name = activityPayload.getName();

    if (CollectionUtils.isNotEmpty(activityPayload.getChildrenActivities())) {
//      this.childActivities = new ArrayList<>(activityPayload.getChildrenActivities().length);
      childActivities = new ActivityDTO[activityPayload.getChildrenActivities().size()];
      for (int i = 0; i < childActivities.length; i++) {
        this.childActivities[i] = new ActivityDTO(activityPayload.getChildrenActivities().get(i));
      }
    }

    this.activityTemplate = activityPayload.getActivityTemplate();

    if (activityPayload.getCrateStorage() != null && activityPayload.getCrateStorage().getCount() > 0) {

      this.crateStorage = new CrateStorageDTO();

      CrateDTO[] crateDTOArray =
          new CrateDTO[activityPayload.getCrateStorage().getCount()];

      for (int i = 0; i < crateDTOArray.length; i++){
        crateDTOArray[i] =
            new CrateDTO(activityPayload.getCrateStorage().getCratesAsList().get(i));
      }

      this.crateStorage.setCrates(crateDTOArray);
    }

    this.rootPlanNodeId = activityPayload.getRootPlanNodeId();
    this.parentPlanNodeId = activityPayload.getParentPlanNodeId();
    this.ordering = activityPayload.getOrdering();

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

  public UUID getAuthTokenId() {
    return authTokenId;
  }

  public void setAuthTokenId(UUID authTokenId) {
    this.authTokenId = authTokenId;
  }

}
