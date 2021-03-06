package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

/**
 * DTO definition for a TextBox control
 */
public class TextBox extends ControlDefinitionDTO {

  public TextBox() {
    super(ControlTypeEnum.TEXTBOX);
  }

  public TextBox(String name, String label) {
    super(ControlTypeEnum.TEXTBOX);
    super.setName(name);
    super.setLabel(label);
  }

  public TextBox(ControlTypeEnum type, String name, String label) {
    super(type);
    super.setName(name);
    super.setLabel(label);
  }

}
