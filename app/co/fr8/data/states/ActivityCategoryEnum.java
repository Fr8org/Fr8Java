package co.fr8.data.states;


import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representation of the ActivityCategory
 */

public enum ActivityCategoryEnum {
  MONITORS(1, "Monitor"),
  RECEIVERS(2, "Receiver"),
  PROCESSORS(3, "Process"),
  SENDERS(4, "Sender");

  private final int code;
  private final String friendlyName;

  ActivityCategoryEnum(int code, String friendlyName) {
    this.friendlyName = friendlyName;
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  @JsonValue
  public String getFriendlyName() { return friendlyName; }

  public static ActivityCategoryEnum findByFriendlyName(String name) {
    for(ActivityCategoryEnum category : values()) {
      if (category.getFriendlyName().equalsIgnoreCase(name))
        return category;
    }

    return null;
  }
}
