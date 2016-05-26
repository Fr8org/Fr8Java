package co.fr8.data.controls;

import co.fr8.data.interfaces.dto.FilterConditionDTO;

import java.util.List;
import java.util.UUID;

/**
 * Class definition of a field that will be used by the ContainerTransition
 * control
 */
public class ContainerTransitionField {

  private List<FilterConditionDTO> conditions;
  private ContainerTransitionEnum transition;
  private UUID targetNodeId;

  public ContainerTransitionField() {
  }

  public ContainerTransitionField(List<FilterConditionDTO> conditions,
                                  ContainerTransitionEnum transition, UUID targetNodeId) {
    this.conditions = conditions;
    this.transition = transition;
    this.targetNodeId = targetNodeId;
  }

  public List<FilterConditionDTO> getConditions() {
    return conditions;
  }

  public void setConditions(List<FilterConditionDTO> conditions) {
    this.conditions = conditions;
  }

  public ContainerTransitionEnum getTransition() {
    return transition;
  }

  public void setTransition(ContainerTransitionEnum transition) {
    this.transition = transition;
  }

  public UUID getTargetNodeId() {
    return targetNodeId;
  }

  public void setTargetNodeId(UUID targetNodeId) {
    this.targetNodeId = targetNodeId;
  }
}
