package co.fr8.data.states;

/**
 * Enum representation of the ActivityCategory
 */
public enum ActivityCategoryEnum {
  MONITORS(1),
  RECEIVERS(2),
  PROCESSORS(3),
  FORWARDERS(4),
  SOLUTION(5);

  private final int code;

  ActivityCategoryEnum(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
