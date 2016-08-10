package co.fr8.terminal.base;

import org.apache.commons.lang3.StringUtils;

/**
 * Enum representation for actions
 */
public enum ActionNameEnum {
  CONFIGURE("configure"),
  RUN("run"),
  EXECUTE_CHILD_ACTIVITIES("executechildactivities"),
  INITIAL_CONFIGURATION_RESPONSE("initialconfigurationresponse"),
  FOLLOWUP_CONFIGURATION_RESPONSE("followupconfigurationresponse"),
  ACTIVATE("activate"),
  DEACTIVATE("deactivate"),
  DOCUMENTATION("documentation"),
  UNKNOWN(StringUtils.EMPTY);

  private final String lowerValue;

  ActionNameEnum(String lowerValue) {
    this.lowerValue = lowerValue;
  }

  public String getLowerValue() {
    return lowerValue;
  }

  /**
   * Get the enum value by its lower case value
   * This code is probably over-engineered
   *
   * @param lowerValue the String associated with the enum
   * @return an enum if one exists for the String, null otherwise
   */
  public static ActionNameEnum getByLowerValue(String lowerValue) {
    for(ActionNameEnum value : values()) {
      if (value.lowerValue.equals(lowerValue)) {
        return value;
      }
    }
    return UNKNOWN;
  }

  public String getPrettyActionName() {
    return this.getLowerValue().substring(0, 1).toUpperCase() +
        this.getLowerValue().substring(1, this.getLowerValue().length());
  }
}
