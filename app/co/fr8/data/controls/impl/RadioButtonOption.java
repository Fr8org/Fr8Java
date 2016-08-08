package co.fr8.data.controls.impl;

import co.fr8.data.controls.AbstractControlDefinition;
import co.fr8.data.controls.interfaces.IContainerControl;
import co.fr8.data.controls.interfaces.ISupportsNestedControls;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Implement
 */
public class RadioButtonOption extends AbstractControlDefinition implements ISupportsNestedControls, IContainerControl<ControlDefinitionDTO>{

  private List<ControlDefinitionDTO> controls = new ArrayList<>();
  private boolean selected = false;
  private String name;
  private String value;

  public RadioButtonOption() {
  }

  public RadioButtonOption(String value, String name) {
    this.value = value;
    this.name = name;
  }

  public RadioButtonOption(String value, String name, List<ControlDefinitionDTO> controls) {
    this.value = value;
    this.name = name;
    this.controls = controls;
  }

  public Iterable<ControlDefinitionDTO> enumerateChildren() {
    return controls;
  }

  @Override
  public List<ControlDefinitionDTO> getControls() {
    return controls;
  }

  public void setControls(List<ControlDefinitionDTO> controls) {
    this.controls = controls;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
