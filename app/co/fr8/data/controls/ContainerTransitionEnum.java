package co.fr8.data.controls;

/**
 * Enumeration class for ContainerTransition states
 */
public enum ContainerTransitionEnum {

  JumpToActivity(0),
  LaunchAdditionalPlan(1),
  JumpToSubplan(2),
  StopProcessing(3),
  SuspendProcessing(4),
  ProceedToNextActivity(5);

  private final int code;

  ContainerTransitionEnum(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
