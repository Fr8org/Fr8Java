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

  public String getCssClass() {
    return cssClass;
  }

  public void setCssClass(String cssClass) {
    this.cssClass = cssClass;
  }
}
