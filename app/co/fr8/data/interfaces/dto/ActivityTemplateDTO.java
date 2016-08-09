package co.fr8.data.interfaces.dto;

import co.fr8.data.states.ActivityCategoryEnum;
import co.fr8.data.states.ActivityTypeEnum;
import co.fr8.util.CollectionUtils;

import java.util.List;

/**
 * TODO: Implement
 */
public class ActivityTemplateDTO {

  //private final UUID id = UUID.randomUUID();
  private String id;
  private String name;
  private String label;
  private String version;
  private TerminalDTO terminal;
  private String tags;
  private List<CategoriesDTO> categories;
  private ActivityCategoryEnum category;
  private ActivityTypeEnum type;
  private int minPaneWidth;
  private boolean needsAuthentication;
  private WebServiceDTO webService;

  public ActivityTemplateDTO() {
  }

  public ActivityTemplateDTO(String id, String name, String label, String version, WebServiceDTO webService,
                             TerminalDTO terminal, String tags, List<CategoriesDTO> categories,
                             ActivityCategoryEnum category, boolean needsAuthentication, int minPaneWidth) {
    this.id = id;
    this.name = name;
    this.label = label;
    this.version = version;
    this.webService = webService;
    this.terminal = terminal;
    this.tags = tags;
    this.categories = categories;
    this.category = category;
    this.needsAuthentication = needsAuthentication;
    this.minPaneWidth = minPaneWidth;
    this.type = ActivityTypeEnum.STANDARD;
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
        ", categories=" + CollectionUtils.toString(categories) + '\'' +
        ", category=" + category.getCode() + '\'' +
        ", type=" + type +
        ", minPaneWidth=" + minPaneWidth +
        ", needsAuthentication=" + needsAuthentication +
        '}';
  }

  public List<CategoriesDTO> getCategories() {
    return categories;
  }

  public void setCategories(List<CategoriesDTO> categories) {
    this.categories = categories;
  }

  public ActivityCategoryEnum getCategory() {
    return category;
  }

  public void setCategory(ActivityCategoryEnum category) {
    this.category = category;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
