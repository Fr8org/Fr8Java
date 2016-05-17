package co.fr8.data.states;


/**
 * Enum representation of the ActivityCategory
 */
public enum ActivityCategoryEnum {
  MONITORS(1, "Monitors"),
  RECEIVERS(2, "Receivers"),
  PROCESSORS(3, "Processors"),
  FORWARDERS(4, "Forwarders"),
  SOLUTION(5, "Solution");

  private final int code;
  private final String friendlyName;

  ActivityCategoryEnum(int code, String friendlyName) {
    this.friendlyName = friendlyName;
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public String getFriendlyName() { return friendlyName; }

  public static ActivityCategoryEnum findByFriendlyName(String name) {
    for(ActivityCategoryEnum category : values()) {
      if (category.getFriendlyName().equalsIgnoreCase(name))
        return category;
    }

    return null;
  }
}
