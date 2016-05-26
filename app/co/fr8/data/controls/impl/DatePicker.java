package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

/**
 * DTO definition for a DatePicker control
 */
public class DatePicker extends ControlDefinitionDTO {

  public DatePicker() {
    super(ControlTypeEnum.DATE_PICKER);
  }
}
