package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.controls.FilterPaneField;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

import java.util.List;

/**
 * DTO definition for the FilterPane UI control
 */
public class FilterPane extends ControlDefinitionDTO {

  public List<FilterPaneField> fields;

  public FilterPane() {
    super(ControlTypeEnum.FILTER_PANE);
  }

  public List<FilterPaneField> getFields() {
    return fields;
  }

  public void setFields(List<FilterPaneField> fields) {
    this.fields = fields;
  }
}
