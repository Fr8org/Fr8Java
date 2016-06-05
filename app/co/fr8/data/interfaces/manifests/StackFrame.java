package co.fr8.data.interfaces.manifests;

import java.util.UUID;

/**
 * TODO: DOCUMENT
 */
public class StackFrame {

  private UUID nodeId;
  private String nodeName;
  private ActivityExecutionPhase currentActivityExecutionPhase;
  private UUID currentChildId;
  private StackLocalData localData;

  public UUID getNodeId() {
    return nodeId;
  }

  public void setNodeId(UUID nodeId) {
    this.nodeId = nodeId;
  }

  public String getNodeName() {
    return nodeName;
  }

  public void setNodeName(String nodeName) {
    this.nodeName = nodeName;
  }

  public ActivityExecutionPhase getCurrentActivityExecutionPhase() {
    return currentActivityExecutionPhase;
  }

  public void setCurrentActivityExecutionPhase(ActivityExecutionPhase currentActivityExecutionPhase) {
    this.currentActivityExecutionPhase = currentActivityExecutionPhase;
  }

  public UUID getCurrentChildId() {
    return currentChildId;
  }

  public void setCurrentChildId(UUID currentChildId) {
    this.currentChildId = currentChildId;
  }

  public StackLocalData getLocalData() {
    return localData;
  }

  public void setLocalData(StackLocalData localData) {
    this.localData = localData;
  }
}
