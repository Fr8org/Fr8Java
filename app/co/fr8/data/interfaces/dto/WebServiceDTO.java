package co.fr8.data.interfaces.dto;

/**
 * TODO: Implement
 */
public class WebServiceDTO {

//  private int id;
  private final String name;
  private final String iconPath;

  public WebServiceDTO(String name, String iconPath) {
    this.name = name;
    this.iconPath = iconPath;
  }

  public String getName() {
    return name;
  }

  public String getIconPath() {
    return iconPath;
  }
}
