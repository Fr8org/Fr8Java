package co.fr8.data.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoriesDTO {

  private final String name;
  private final String iconPath;

  @JsonCreator
  public CategoriesDTO(@JsonProperty("name") String name,
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

  @Override
  public String toString() {
    return "CategoriesDTO{" +
        "name='" + name + '\'' +
        ", iconPath='" + iconPath + '\'' +
        '}';
  }
}
