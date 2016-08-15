package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

/**
 * Object implementation of the CheckBox control which provides serialization
 * to JSON
 */
public class CheckBox extends ControlDefinitionDTO {

  /**
   * Constructor for the CheckBox class
   * Calls the ActivityTemplateSubplanDTO constructor passing the
   * ControlTypeEnum.CHECKBOX enum value to set the type property
   */
  public CheckBox(String name, String label) {
    super(ControlTypeEnum.CHECKBOX);
    super.setName(name);
    super.setLabel(label);
  }


}
