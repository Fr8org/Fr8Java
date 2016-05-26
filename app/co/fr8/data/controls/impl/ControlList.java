package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.controls.ListTemplate;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO Implementation for a ControlList UI control
 */
public class ControlList extends ControlDefinitionDTO {

  private List<List<ControlDefinitionDTO>> controlGroups = new ArrayList<>();
  private ListTemplate templateContainer;
  private String addControlGroupButtonText;
  private String noDataMessage;

  public ControlList() {
    super(ControlTypeEnum.CONTROL_LIST);
  }

  public ControlList(ListTemplate templateContainer) {
    this();
    this.templateContainer = templateContainer;
  }

  public List<List<ControlDefinitionDTO>> getControlGroups() {
    return controlGroups;
  }

  public void setControlGroups(List<List<ControlDefinitionDTO>> controlGroups) {
    this.controlGroups = controlGroups;
  }

  public ListTemplate getTemplateContainer() {
    return templateContainer;
  }

  public void setTemplateContainer(ListTemplate templateContainer) {
    this.templateContainer = templateContainer;
  }

  public String getAddControlGroupButtonText() {
    return addControlGroupButtonText;
  }

  public void setAddControlGroupButtonText(String addControlGroupButtonText) {
    this.addControlGroupButtonText = addControlGroupButtonText;
  }

  public String getNoDataMessage() {
    return noDataMessage;
  }

  public void setNoDataMessage(String noDataMessage) {
    this.noDataMessage = noDataMessage;
  }
}
