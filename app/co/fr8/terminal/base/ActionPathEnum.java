package co.fr8.terminal.base;

import org.apache.commons.lang3.StringUtils;

/**
 * Enum representation for the
 */
public enum ActionPathEnum {
  CONFIGURE("configure"),
  RUN("run"),
  EXECUTE_CHILD_ACTIVITIES("executechildactivities"),
  INITIAL_CONFIGURATION_RESPONSE("initialconfigurationresponse"),
  FOLLOWUP_CONFIGURATION_RESPONSE("followupconfigurationresponse"),
  ACTIVATE("activate"),
  DEACTIVATE("deactivate"),
  DOCUMENTATION("documentation"),
  UNKNOWN(StringUtils.EMPTY);

  private String lowerValue;

  ActionPathEnum(String lowerValue) {
    this.lowerValue = lowerValue;
  }

  /**
   * Get the enum value by its lower case value
   * This code is probably overengineered
   *
   * @param lowerValue the String associated with the enum
   * @return an enum if one exists for the String, null otherwise
   */
  public static ActionPathEnum getByLowerValue(String lowerValue) {
    for(ActionPathEnum value : values()) {
      if (value.lowerValue.equals(lowerValue)) {
        return value;
      }
    }
    return UNKNOWN;
  }
}
