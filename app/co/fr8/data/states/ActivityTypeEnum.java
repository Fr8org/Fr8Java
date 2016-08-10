package co.fr8.data.states;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing the ActivityType
 */
public enum ActivityTypeEnum {
  STANDARD(1, "Standard"),
  LOOP(2, "Loop"),
  SOLUTION(3, "Solution");

  private final int code;
  private final String friendlyName;

  ActivityTypeEnum(int code, String friendlyName) {
    this.code = code;
    this.friendlyName = friendlyName;
  }

  @JsonValue
  public int getCode() {
    return code;
  }

  public String getFriendlyName() {
    return friendlyName;
  }

  public static ActivityTypeEnum findByFriendlyName(String name) {
    for(ActivityTypeEnum type : values()) {
      if (type.getFriendlyName().equalsIgnoreCase(name))
        return type;
    }

    return null;
  }
}
