package co.fr8.data.interfaces.dto;

import co.fr8.data.states.ActivityCategoryEnum;
import co.fr8.data.states.ActivityTypeEnum;

import java.util.UUID;

/**
 * TODO: Implement
 */
public class ActivityTemplateDTO {

  private UUID id;
  private String name;
  private String label;
  private String version;
  private WebServiceDTO webService;
  private TerminalDTO terminal;
  private String tags;
  private ActivityCategoryEnum category;
  private ActivityTypeEnum type;
  private int minPaneWidth;
  private boolean needsAuthentication;

  public ActivityTemplateDTO() {
  }

  public ActivityTemplateDTO(String name, String label, String version, WebServiceDTO webService,
                             TerminalDTO terminal, ActivityCategoryEnum category,
                             boolean needsAuthentication, int minPaneWidth) {
    this.name = name;
    this.label = label;
    this.version = version;
    this.webService = webService;
    this.terminal = terminal;
    this.category = category;
    this.needsAuthentication = needsAuthentication;
    this.minPaneWidth = minPaneWidth;
    type = ActivityTypeEnum.STANDARD;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public String getLabel() {
    return label;
  }

  public String getVersion() {
    return version;
  }

  public WebServiceDTO getWebService() {
    return webService;
  }

  public TerminalDTO getTerminal() {
    return terminal;
  }

  public void setTerminal(TerminalDTO terminal) {
    this.terminal = terminal;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public ActivityCategoryEnum getCategory() {
    return category;
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

  public void setWebService(WebServiceDTO webService) {
    this.webService = webService;
  }

  public void setCategory(String category) {
    this.category = ActivityCategoryEnum.findByFriendlyName(category);
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

  @Override
  public String toString() {
    return "ActivityTemplateDTO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", label='" + label + '\'' +
        ", version='" + version + '\'' +
        ", webService=" + webService +
        ", terminal=" + terminal +
        ", tags='" + tags + '\'' +
        ", category=" + category +
        ", type=" + type +
        ", minPaneWidth=" + minPaneWidth +
        ", needsAuthentication=" + needsAuthentication +
        '}';
  }
}
