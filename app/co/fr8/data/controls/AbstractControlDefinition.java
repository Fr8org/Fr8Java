package co.fr8.data.controls;

/**
 * The most basic of control definitions which contains getter and setter methods
 * for the name property
 */
abstract public class AbstractControlDefinition {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
