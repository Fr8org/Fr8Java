package co.fr8.data.interfaces.dto;

import co.fr8.data.crates.CrateManifestType;
import co.fr8.data.states.AvailabilityTypeEnum;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

/**
 * TODO: Implement
 */
public class FieldDTO {

  private String key;
  private String value;
  private String label;
  private String fieldType;
  private boolean required = false;
  private String tags;
  private AvailabilityTypeEnum availability;
  private CrateManifestType sourceCrateManifest;
  private String sourceCrateLabel;
  private String sourceActivityId;
  private Map<String, JsonNode> data;

  public FieldDTO() {
  }

  public FieldDTO(String key, String value, AvailabilityTypeEnum availability) {
    this.key = key;
    this.value = value;
    this.availability = availability;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
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

  public String getFieldType() {
    return fieldType;
  }

  public void setFieldType(String fieldType) {
    this.fieldType = fieldType;
  }

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    required = required;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public AvailabilityTypeEnum getAvailability() {
    return availability;
  }

  public void setAvailability(AvailabilityTypeEnum availability) {
    this.availability = availability;
  }

  public CrateManifestType getSourceCrateManifest() {
    return sourceCrateManifest;
  }

  public void setSourceCrateManifest(CrateManifestType sourceCrateManifest) {
    this.sourceCrateManifest = sourceCrateManifest;
  }

  public String getSourceCrateLabel() {
    return sourceCrateLabel;
  }

  public void setSourceCrateLabel(String sourceCrateLabel) {
    this.sourceCrateLabel = sourceCrateLabel;
  }

  public String getSourceActivityId() {
    return sourceActivityId;
  }

  public void setSourceActivityId(String sourceActivityId) {
    this.sourceActivityId = sourceActivityId;
  }

  public Map<String, JsonNode> getData() {
    return data;
  }

  public void setData(Map<String, JsonNode> data) {
    this.data = data;
  }
}
