package co.fr8.terminal.base;

/**
 * TODO: DOCUMENT
 */
public enum ActivityResponse {

  NULL("Null", 0),
  SUCCESS("Success", 1),
  ERROR("Error", 2),
  REQUEST_TERMINATE("RequestTerminate", 3),
  REQUEST_SUSPEND("RequestSuspend", 4),
  SKIP_CHILDREN("SkipChildren", 5),
  REPROCESS_CHILDREN("ReprocessChildren", 6),
  EXECUTE_CLIENT_ACTIVITY("ExecuteClientActivity", 7),
  SHOW_DOCUMENTATION("ShowDocumentation", 8),
  JUMP_TO_ACTIVITY("JumpToActivity", 9),
  JUMP_TO_SUBPLAN("JumpToSubplan", 10),
  LAUNCH_ADDITIONAL_PLAN("LaunchAdditionalPlan", 11),
  CALL_AND_RETURN("CallAndReturn", 12),
  BREAK("Break", 13);

  private final String friendlyName;
  private final int code;

  ActivityResponse(String friendlyName, int code) {
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
