package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.controls.interfaces.IContainerControl;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the ControlDefinitionDTO for the RadioButtonGroup
 * ControlType
 *
 * This class implements IContainerControl in order to contain multiple RadioButton
 * control items
 */
public class RadioButtonGroup extends ControlDefinitionDTO implements IContainerControl<RadioButtonOption> {

  private String groupName;
  private List<RadioButtonOption> radios = new ArrayList<>();

  public RadioButtonGroup() {
    super(ControlTypeEnum.RADIO_BUTTON_GROUP);
  }

  @Override
  public Iterable<RadioButtonOption> enumerateChildren() {
    return radios;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public List<RadioButtonOption> getRadios() {
    return radios;
  }

  public void setRadios(List<RadioButtonOption> radios) {
    this.radios = radios;
  }
}
