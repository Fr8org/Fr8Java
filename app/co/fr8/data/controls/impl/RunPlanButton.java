package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

/**
 * @deprecated no longer used by plans
 * Object implementation of the RunPlanButton control which provides serialization
 * to JSON
 */
public class RunPlanButton extends ControlDefinitionDTO {

  /**
   * Constructor for the RunPlanButton class
   * Calls the ActivityTemplateSubplanDTO constructor passing the
   * ControlTypeEnum.RUN_PLAN_BUTTON enum value to set the type property
   */
  public RunPlanButton() {
    super(ControlTypeEnum.RUN_PLAN_BUTTON);
  }

}
