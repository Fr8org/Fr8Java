package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

/**
 * DTO for a TextArea UI control with the option for read only access
 */
public class TextArea extends ControlDefinitionDTO {

  private boolean isReadOnly = false;

  public TextArea(ControlTypeEnum type) {
    super(type);
  }

  public TextArea() {
    super(ControlTypeEnum.TEXT_AREA);
  }

  public boolean isReadOnly() {
    return isReadOnly;
  }

  public void setReadOnly(boolean readOnly) {
    isReadOnly = readOnly;
  }
}
