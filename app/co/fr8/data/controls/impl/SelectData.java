package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ActivityTemplateSubplanDTO;

/**
 * Object implementation of the SelectData control which provides serialization
 * to JSON
 */
public class SelectData extends ActivityTemplateSubplanDTO {

  private String activityTemplateName;

  /**
   * Constructor for the SelectData class
   * Calls the ActivityTemplateSubplanDTO constructor passing the
   * ControlTypeEnum.SELECT_DATA enum value to set the type property
   */
  public SelectData() {
    super(ControlTypeEnum.SELECT_DATA);
  }

  /**
   * Returns the value of the activityTemplateName property
   * @return String value of the activityTemplateName
   */
  public String getActivityTemplateName() {
    return activityTemplateName;
  }

  /**
   * Setter for the activityTemplateName property
   * @param activityTemplateName String value to use when setting the
   *                             activityTemplateName property
   */
  public void setActivityTemplateName(String activityTemplateName) {
    this.activityTemplateName = activityTemplateName;
  }
}
