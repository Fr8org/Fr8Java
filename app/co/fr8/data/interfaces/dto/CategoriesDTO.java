package co.fr8.data.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoriesDTO {

  private final String id;
  private final String name;
  private final String iconPath;

  @JsonCreator
  public CategoriesDTO(@JsonProperty("id") String id,
                       @JsonProperty("name") String name,
                       @JsonProperty("iconPath") String iconPath) {
    this.id = id;
    this.name = name;
    this.iconPath = iconPath;
  }

  public String getId() {
    return id;
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
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", iconPath='" + iconPath + '\'' +
        '}';
  }
}
