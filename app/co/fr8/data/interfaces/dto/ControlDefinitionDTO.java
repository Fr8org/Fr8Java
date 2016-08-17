package co.fr8.data.interfaces.dto;

import co.fr8.data.controls.AbstractControlDefinition;
import co.fr8.data.controls.ControlEvent;
import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.util.CollectionUtils;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the abstract definition of a control in order to make it
 * easily extensible and serializable to JSON
 */
abstract public class ControlDefinitionDTO extends AbstractControlDefinition {

  protected static final String EMPTY_BRACKETS = "[]";
  private List<ControlEvent> events = new ArrayList<>();
  private boolean required = false;
  private String value;
  private String label;
  private boolean selected;
  private FieldSourceDTO source;
  private ActivityResponseDTO showDocumentation;

  @JsonProperty("isHidden")
  private boolean hidden = false;

  @JsonProperty("isCollapsed")
  private boolean collapsed = false;
  private ControlTypeEnum type;
  private String errorMessage;

  public ControlDefinitionDTO(){
    super();
  }

  public ControlDefinitionDTO(ControlTypeEnum type) {
    this.type = type;
  }

  public void reset(List<String> fieldNames) {
    //This is here to prevent development bugs
    if (CollectionUtils.isEmpty(fieldNames)) {
      Logger.warn("Attempt to set ControlDefinitionDTO using empty field names");
//      throw new NotSupportedException();
    }
    value = StringUtils.EMPTY;
  }

  protected void resetToEmpty() {
    setValue(EMPTY_BRACKETS);
  }

  public List<ControlEvent> getEvents() {
    return events;
  }

  public void setEvents(List<ControlEvent> events) {
    this.events = events;
  }

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public FieldSourceDTO getSource() {
    return source;
  }

  public void setSource(FieldSourceDTO source) {
    this.source = source;
  }

  public ActivityResponseDTO getShowDocumentation() {
    return showDocumentation;
  }

  public void setShowDocumentation(ActivityResponseDTO showDocumentation) {
    this.showDocumentation = showDocumentation;
  }

  public boolean isHidden() {
    return hidden;
  }

  public void setHidden(boolean hidden) {
    this.hidden = hidden;
  }

  public boolean isCollapsed() {
    return collapsed;
  }

  public void setCollapsed(boolean collapsed) {
    this.collapsed = collapsed;
  }

  public String getType() {
    return type.getFriendlyName();
  }

  public void setType(ControlTypeEnum controlTypeEnum) {
    this.type = controlTypeEnum;
  }

  public void setType(String controlTypeString) {
    this.type = ControlTypeEnum.findByFriendlyName(controlTypeString);
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
