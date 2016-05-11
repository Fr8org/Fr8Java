package co.fr8.data.states;

/**
 * Enum representing the ActivityType
 */
public enum ActivityTypeEnum {
  STANDARD(1),
  LOOP(2),
  SOLUTION(3);

  private final int code;

  ActivityTypeEnum(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
