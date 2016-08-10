package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for the TextBlock UI control
 */
public class TextBlock extends ControlDefinitionDTO {

  @JsonProperty("class")
  private String cssClass;

  public TextBlock() {
    super(ControlTypeEnum.TEXT_BLOCK);
  }

  public TextBlock(String cssClass, String name, String value) {
    this();
    this.setCssClass(cssClass);
    this.setName(name);
    this.setValue(value);
  }

  public String getCssClass() {
    return cssClass;
  }

  public void setCssClass(String cssClass) {
    this.cssClass = cssClass;
  }
}
