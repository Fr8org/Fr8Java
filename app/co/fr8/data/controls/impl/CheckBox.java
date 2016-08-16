package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlEvent;
import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ActivityResponseDTO;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;
import co.fr8.data.interfaces.dto.FieldSourceDTO;

import java.util.List;

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

  public CheckBox(ControlTypeEnum type) {
    super(type);
  }

  public CheckBox(List<ControlEvent> events, boolean required, String value, String label, boolean selected,
                  FieldSourceDTO source, ActivityResponseDTO showDocumentation, boolean hidden, boolean collapsed,
                  ControlTypeEnum type, String errorMessage) {
    super(events, required, value, label, selected, source, showDocumentation, hidden, collapsed, type, errorMessage);
  }
}
