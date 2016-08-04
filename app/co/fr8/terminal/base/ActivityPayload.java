package co.fr8.terminal.base;

import co.fr8.data.crates.AbstractCrateStorage;
import co.fr8.data.crates.Crate;
import co.fr8.data.crates.CrateStorage;
import co.fr8.data.interfaces.dto.ActivityDTO;
import co.fr8.data.interfaces.dto.ActivityTemplateSummaryDTO;
import co.fr8.data.interfaces.dto.CrateDTO;
import co.fr8.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Transitional object used for converting Fr8Data into ActivityDTO
 */
public class ActivityPayload {

  private UUID id;
  private String label;
  private String name;
  private List<ActivityPayload> childrenActivities;
  private ActivityTemplateSummaryDTO activityTemplate;
  private AbstractCrateStorage crateStorage;
  private UUID planId;
  private UUID parentPlanNodeId;
  private int ordering;

  public ActivityPayload(String name) {
    this.name = name;
  }

  public ActivityPayload(ActivityDTO activityDTO) {
    this.id = activityDTO.getId();
    this.label = activityDTO.getLabel();
//    this.name = activityDTO.getName();

    if (CollectionUtils.isNotEmpty(activityDTO.getChildrenActivities())) {
      this.childrenActivities = new ArrayList<>(activityDTO.getChildrenActivities().length);

      for (ActivityDTO childActivities : activityDTO.getChildrenActivities()) {
        this.childrenActivities.add(new ActivityPayload(childActivities));
      }
    }

    this.activityTemplate = activityDTO.getActivityTemplate();

    if (activityDTO.getCrateStorage() != null &&
        CollectionUtils.isNotEmpty(activityDTO.getCrateStorage().getCrates())) {

      this.crateStorage = new CrateStorage();

      for (CrateDTO crateDTO : activityDTO.getCrateStorage().getCrates()) {
        this.crateStorage.add(new Crate(crateDTO));
      }
    }

    this.planId = activityDTO.getPlanId();
    this.parentPlanNodeId = activityDTO.getParentPlanNodeId();
    this.ordering = activityDTO.getOrdering();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<ActivityPayload> getChildrenActivities() {
    return childrenActivities;
  }

  public void setChildrenActivities(List<ActivityPayload> childrenActivities) {
    this.childrenActivities = childrenActivities;
  }

  public ActivityTemplateSummaryDTO getActivityTemplate() {
    return activityTemplate;
  }

  public void setActivityTemplate(ActivityTemplateSummaryDTO activityTemplate) {
    this.activityTemplate = activityTemplate;
  }

  public AbstractCrateStorage getCrateStorage() {
    // #HACKFIX
    if (crateStorage == null)
      crateStorage = new CrateStorage();

    return crateStorage;
  }

  public void setCrateStorage(AbstractCrateStorage crateStorage) {
    this.crateStorage = crateStorage;
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

  @Override
  public String toString() {
    return "ActivityPayload{" +
        "id=" + id +
        ", label='" + label + '\'' +
        ", name='" + name + '\'' +
        ", childrenActivities=" + childrenActivities +
        ", activityTemplate=" + activityTemplate +
        ", crateStorage=" + crateStorage +
        ", planId=" + planId +
        ", parentPlanNodeId=" + parentPlanNodeId +
        ", ordering=" + ordering +
        '}';
  }
}
