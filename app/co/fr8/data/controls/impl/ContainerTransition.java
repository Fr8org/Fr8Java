package co.fr8.data.controls.impl;

import co.fr8.data.controls.ContainerTransitionField;
import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO definition for a ContainerTransition UI control
 */
public class ContainerTransition extends ControlDefinitionDTO {

  private List<ContainerTransitionField> transitions = new ArrayList<>();

  public ContainerTransition() {
    super(ControlTypeEnum.CONTAINER_TRANSITION);
  }

  public List<ContainerTransitionField> getTransitions() {
    return transitions;
  }

  public void setTransitions(List<ContainerTransitionField> transitions) {
    this.transitions = transitions;
  }
}
