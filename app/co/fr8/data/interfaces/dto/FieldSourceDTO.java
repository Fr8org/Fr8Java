package co.fr8.data.interfaces.dto;

import co.fr8.data.states.AvailabilityTypeEnum;

/**
 * TODO: Implement
 */
public class FieldSourceDTO {

  private String manifestType;
  private String label;
  private String filterByTag;
  private boolean requestUpstream = false;
  private AvailabilityTypeEnum availabilityType;

  public FieldSourceDTO() {
  }

  public FieldSourceDTO(String label, String manifestType) {
    this.manifestType = manifestType;
    this.label = label;
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

  public String getFilterByTag() {
    return filterByTag;
  }

  public void setFilterByTag(String filterByTag) {
    this.filterByTag = filterByTag;
  }

  public boolean isRequestUpstream() {
    return requestUpstream;
  }

  public void setRequestUpstream(boolean requestUpstream) {
    this.requestUpstream = requestUpstream;
  }

  public AvailabilityTypeEnum getAvailabilityType() {
    return availabilityType;
  }

  public void setAvailabilityType(AvailabilityTypeEnum availabilityType) {
    this.availabilityType = availabilityType;
  }
}
