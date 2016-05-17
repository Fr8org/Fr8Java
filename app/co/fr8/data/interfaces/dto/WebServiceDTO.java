package co.fr8.data.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TODO: Implement
 */
public class WebServiceDTO {

  private int id;
  private final String name;
  private final String iconPath;


  @JsonCreator
  public WebServiceDTO(@JsonProperty("name") String name,
                       @JsonProperty("iconPath") String iconPath) {
    this.name = name;
    this.iconPath = iconPath;
  }

  public String getName() {
    return name;
  }

  public String getIconPath() {
    return iconPath;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}
