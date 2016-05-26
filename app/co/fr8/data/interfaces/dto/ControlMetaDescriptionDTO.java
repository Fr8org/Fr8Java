package co.fr8.data.interfaces.dto;

import java.util.List;

/**
 * @deprecated not necessary for Java implementation
 * Defines a more complex control DTO which contains a list of ControlDefinitionDTO
 * objects
 */
abstract public class ControlMetaDescriptionDTO {

  private List<ControlDefinitionDTO> controls;
  private final String description;
  private final String type;

  public ControlMetaDescriptionDTO(String type, String description) {
    this.description = description;
    this.type = type;
  }

  abstract public ControlDefinitionDTO createControl();

  public List<ControlDefinitionDTO> getControls() {
    return controls;
  }

  public void setControls(List<ControlDefinitionDTO> controls) {
    this.controls = controls;
  }

  public String getDescription() {
    return description;
  }

  public String getType() {
    return type;
  }
}
