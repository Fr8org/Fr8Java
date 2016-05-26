package co.fr8.data.interfaces.dto;

/**
 * Data Transfer Object for a FilterCondition object
 */
public class FilterConditionDTO {

  private String field;
  private String operator;
  private String value;

  public FilterConditionDTO() {
  }

  public FilterConditionDTO(String field, String operator, String value) {
    this.field = field;
    this.operator = operator;
    this.value = value;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
