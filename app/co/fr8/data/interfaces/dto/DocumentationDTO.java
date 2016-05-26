package co.fr8.data.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

/**
 * DTO Implementation for the documentation object
 */
public class DocumentationDTO {

  private String displayMechanism;
  private String contentPath;

  public DocumentationDTO(String displayMechanism, String contentPath) {
    this.displayMechanism = displayMechanism;
    this.contentPath = contentPath;
  }

  public String getDisplayMechanism() {
    return displayMechanism;
  }

  public void setDisplayMechanism(String displayMechanism) {
    this.displayMechanism = displayMechanism;
  }

  public String getContentPath() {
    return contentPath;
  }

  public void setContentPath(String contentPath) {
    this.contentPath = contentPath;
  }

  @JsonProperty("url")
  public String getUrl() {

    if (StringUtils.isBlank(this.contentPath)) {
      return "/activities/documentation";
    }

    return "/activities/documentation/" + this.contentPath;
  }

//  public void setUrl(String url) {
//    this.url = url;
//  }
}
