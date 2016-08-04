package co.fr8.data.interfaces.dto;

import co.fr8.terminal.base.ActivityPayload;
import co.fr8.util.CollectionUtils;

import java.util.Arrays;
import java.util.UUID;

/**
 * ActivityDTOs are used everywhere in Fr8, from FE to terminals.
 * They are transmitted with every request.
 */
public class ActivityDTO {

  private String label;
  private ActivityTemplateSummaryDTO activityTemplate;
  private UUID planId;
  private UUID parentPlanNodeId;
  private int ordering;
  private UUID id;
  private CrateStorageDTO crateStorage;
  private ActivityDTO[] childrenActivities;
  private UUID authTokenId;
  private AuthorizationTokenDTO authToken;
  private String documentation;

  public ActivityDTO() {
  }

  public ActivityDTO(String label, ActivityTemplateSummaryDTO activityTemplate, UUID planId,
                     UUID parentPlanNodeId, int ordering, UUID id, CrateStorageDTO crateStorage,
                     ActivityDTO[] childrenActivities, UUID authTokenId, AuthorizationTokenDTO authToken,
                     String documentation) {
    this.label = label;
    this.activityTemplate = activityTemplate;
    this.planId = planId;
    this.parentPlanNodeId = parentPlanNodeId;
    this.ordering = ordering;
    this.id = id;
    this.crateStorage = crateStorage;
    this.childrenActivities = childrenActivities;
    this.authTokenId = authTokenId;
    this.authToken = authToken;
    this.documentation = documentation;
  }

  /*
   * Controllers create new ActivityDTOs with this method.
   */
  public ActivityDTO(ActivityPayload activityPayload) {
    this.id = activityPayload.getId();
    this.label = activityPayload.getLabel();

    if (CollectionUtils.isNotEmpty(activityPayload.getChildrenActivities())) {
      childrenActivities = new ActivityDTO[activityPayload.getChildrenActivities().size()];
      for (int i = 0; i < childrenActivities.length; i++) {
        this.childrenActivities[i] = new ActivityDTO(activityPayload.getChildrenActivities().get(i));
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

    this.planId = activityPayload.getPlanId();
    this.parentPlanNodeId = activityPayload.getParentPlanNodeId();
    this.ordering = activityPayload.getOrdering();

  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public ActivityTemplateSummaryDTO getActivityTemplate() {
    return activityTemplate;
  }

  public void setActivityTemplate(ActivityTemplateSummaryDTO activityTemplate) {
    this.activityTemplate = activityTemplate;
  }

  public UUID getPlanId() {
    return planId;
  }

  public void setPlanId(UUID planId) {
    this.planId = planId;
  }

  public UUID getParentPlanNodeId() {
    return parentPlanNodeId;
  }

  public void setParentPlanNodeId(UUID parentPlanNodeId) {
    this.parentPlanNodeId = parentPlanNodeId;
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

  public ActivityDTO[] getChildrenActivities() {
    return childrenActivities;
  }

  public void setChildrenActivities(ActivityDTO[] childrenActivities) {
    this.childrenActivities = childrenActivities;
  }

  public UUID getAuthTokenId() {
    return authTokenId;
  }

  public void setAuthTokenId(UUID authTokenId) {
    this.authTokenId = authTokenId;
  }

  public AuthorizationTokenDTO getAuthToken() {
    return authToken;
  }

  public void setAuthToken(AuthorizationTokenDTO authToken) {
    this.authToken = authToken;
  }

  public String getDocumentation() {
    return documentation;
  }

  public void setDocumentation(String documentation) {
    this.documentation = documentation;
  }

  @Override
  public String toString() {
    return "ActivityDTO{" +
        "label='" + label + "\'\n" +
        ", activityTemplate=" + activityTemplate + "\'\n" +
        ", planId=" + planId + "\'\n" +
        ", parentPlanNodeId=" + parentPlanNodeId + "\'\n" +
        ", ordering=" + ordering + "\'\n" +
        ", id=" + id + "\'\n" +
        ", crateStorage=" + crateStorage + "\'\n" +
        ", childActivities=" + Arrays.toString(childrenActivities) + "\'\n" +
        ", authTokenId=" + authTokenId + "\'\n" +
        ", authToken=" + authToken + "\'\n" +
        ", documentation='" + documentation + "\'\n" +
        '}';
  }
}
