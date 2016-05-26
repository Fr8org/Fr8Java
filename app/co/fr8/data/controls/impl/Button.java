package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO Implementation for a Button UI element
 */
public class Button extends ControlDefinitionDTO {

  @JsonProperty("class")
  private String cssClass;
  private boolean clicked = false;

  public Button() {
    super(ControlTypeEnum.BUTTON);
  }

  public boolean isClicked() {
    return clicked;
  }

  public void setClicked(boolean clicked) {
    this.clicked = clicked;
  }

  public String getCssClass() {
    return cssClass;
  }

  public void setCssClass(String cssClass) {
    this.cssClass = cssClass;
  }
}
