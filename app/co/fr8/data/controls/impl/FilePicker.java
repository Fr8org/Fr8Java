package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

/**
 * DTO implementation for the FilePicker UI control
 */
public class FilePicker extends ControlDefinitionDTO {

  public FilePicker() {
    super(ControlTypeEnum.FILE_PICKER);
  }
}
