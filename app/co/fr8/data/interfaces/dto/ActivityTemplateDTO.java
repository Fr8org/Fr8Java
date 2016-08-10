package co.fr8.data.interfaces.dto;

import co.fr8.data.states.ActivityTypeEnum;
import co.fr8.util.CollectionUtils;

import java.util.List;

/**
 * TODO: Implement
 */
public class ActivityTemplateDTO {

  //private final UUID id = UUID.randomUUID();
  private String internalId;
  private String name;
  private String label;
  private String version;
  private String tags;
  private List<CategoriesDTO> categories;
  private ActivityTypeEnum type;
  private int minPaneWidth;
  private boolean needsAuthentication;
  private String showDocumentation;
  private String description;

  public ActivityTemplateDTO() {
  }

  public ActivityTemplateDTO(String name, String label, String version,
                             String tags, List<CategoriesDTO> categories,
                              boolean needsAuthentication, int minPaneWidth) {
    this.internalId = null;
    this.name = name;
    this.label = label;
    this.version = version;
    this.tags = tags;
    this.categories = categories;
    this.needsAuthentication = needsAuthentication;
    this.minPaneWidth = minPaneWidth;
    this.type = ActivityTypeEnum.STANDARD;
    this.showDocumentation = null;
    this.description = null;
  }

//  public UUID getId() {
//    return id;
//  }

  public String getName() {
    return name;
  }

  public String getLabel() {
    return label;
  }

  public String getVersion() {
    return version;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public ActivityTypeEnum getType() {
    return type;
  }

  public int getMinPaneWidth() {
    return minPaneWidth;
  }

  public void setMinPaneWidth(int minPaneWidth) {
    this.minPaneWidth = minPaneWidth;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public void setType(String type) {
    this.type = ActivityTypeEnum.findByFriendlyName(type);
  }

  public void setNeedsAuthentication(boolean needsAuthentication) {
    this.needsAuthentication = needsAuthentication;
  }

  public boolean isNeedsAuthentication() {
    return needsAuthentication;
  }

  public List<CategoriesDTO> getCategories() {
    return categories;
  }

  public void setCategories(List<CategoriesDTO> categories) {
    this.categories = categories;
  }

  public String getShowDocumentation() {
    return showDocumentation;
  }

  public void setShowDocumentation(String showDocumentation) {
    this.showDocumentation = showDocumentation;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "ActivityTemplateDTO{" +
        "internalId='" + internalId + '\'' +
        ", name='" + name + '\'' +
        ", label='" + label + '\'' +
        ", version='" + version + '\'' +
        ", tags='" + tags + '\'' +
        ", categories=" + CollectionUtils.toString(categories) + '\'' +
        ", type=" + type +
        ", minPaneWidth=" + minPaneWidth +
        ", needsAuthentication=" + needsAuthentication +
        ", showDocumentation='" + showDocumentation + '\'' +
        ", description='" + description + '\'' +
        '}';
  }

  public String getInternalId() {
    return internalId;
  }

  public void setInternalId(String internalId) {
    this.internalId = internalId;
  }
}
