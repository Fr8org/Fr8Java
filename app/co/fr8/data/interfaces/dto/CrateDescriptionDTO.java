package co.fr8.data.interfaces.dto;

import co.fr8.data.states.AvailabilityTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO implementation for the CrateDescription object
 */
public class CrateDescriptionDTO {

  private int manifestId;
  private String manifestType;
  private String label;
  private String producedBy;
  private boolean selected = false;
  private AvailabilityTypeEnum availabilityType = AvailabilityTypeEnum.RunTime;
  private List<FieldDTO> fields = new ArrayList<>();

  public CrateDescriptionDTO() {
  }

  public int getManifestId() {
    return manifestId;
  }

  public void setManifestId(int manifestId) {
    this.manifestId = manifestId;
  }

  public String getManifestType() {
    return manifestType;
  }

  public void setManifestType(String manifestType) {
    this.manifestType = manifestType;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getProducedBy() {
    return producedBy;
  }

  public void setProducedBy(String producedBy) {
    this.producedBy = producedBy;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public AvailabilityTypeEnum getAvailabilityType() {
    return availabilityType;
  }

  public void setAvailabilityType(AvailabilityTypeEnum availabilityType) {
    this.availabilityType = availabilityType;
  }

  public List<FieldDTO> getFields() {
    return fields;
  }

  public void setFields(List<FieldDTO> fields) {
    this.fields = fields;
  }
}
