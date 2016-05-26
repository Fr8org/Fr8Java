package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

/**
 * DTO for a Generic UI control which is a TextBox type
 */
public class Generic extends ControlDefinitionDTO {

  public Generic() {
    super(ControlTypeEnum.TEXTBOX);
  }
}
