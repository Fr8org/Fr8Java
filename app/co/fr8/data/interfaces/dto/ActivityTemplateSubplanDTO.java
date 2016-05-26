package co.fr8.data.interfaces.dto;

import co.fr8.data.controls.ControlTypeEnum;

/**
 * TODO: Implement
 */
abstract public class ActivityTemplateSubplanDTO extends ControlDefinitionDTO {

  private String activityTemplateId;
  private String subPlanId;
  private String externalObjectName;

  public ActivityTemplateSubplanDTO(ControlTypeEnum type) {
    super(type);
  }

  public String getActivityTemplateId() {
    return activityTemplateId;
  }

  public void setActivityTemplateId(String activityTemplateId) {
    this.activityTemplateId = activityTemplateId;
  }

  public String getSubPlanId() {
    return subPlanId;
  }

  public void setSubPlanId(String subPlanId) {
    this.subPlanId = subPlanId;
  }

  public String getExternalObjectName() {
    return externalObjectName;
  }

  public void setExternalObjectName(String externalObjectName) {
    this.externalObjectName = externalObjectName;
  }
}
