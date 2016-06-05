package co.fr8.data.interfaces.manifests;

/**
 * TODO: DOCUMENT
 */
public enum ActivityExecutionPhase {

  WAS_NOT_EXECUTED("WasNotExecuted", 0),
  PROCESSING_CHILDREN("ProcessingChildren", 1);

  private final String friendlyName;
  private final int code;

  ActivityExecutionPhase(String friendlyName, int code) {
    this.friendlyName = friendlyName;
    this.code = code;
  }

  public String getFriendlyName() {
    return friendlyName;
  }

  public int getCode() {
    return code;
  }
}
