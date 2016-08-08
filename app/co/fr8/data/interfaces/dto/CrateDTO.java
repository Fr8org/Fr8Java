package co.fr8.data.interfaces.dto;

import co.fr8.data.crates.Crate;
import co.fr8.data.states.AvailabilityTypeEnum;
import co.fr8.util.json.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Calendar;
import java.util.Date;

/**
 * TODO: Implement
 */
public class CrateDTO {

  private static final String FR8_REGISTRAR = "www.fr8.co/registry";

  private String manifestType;
  private int manifestId;
  private ManufacturerDTO manufacturer;
  private String manifestRegistrar;
  private String id;
  private String label;
  private JsonNode contents;
  private String parentCrateId;
  private Date createTime = Calendar.getInstance().getTime();
  private int availability;
  private String sourceActivityId;

  public CrateDTO() {
  }

  public CrateDTO(Crate crate) {
//    this.manifestId = crate.getId();
    this.manifestType = crate.getCrateManifestType().getFriendlyName();
    this.label = crate.getLabel();
    this.id = crate.getId();

    if (crate.getKnownContent() != null) {
      this.contents = JsonUtils.writeObjectToJsonNode(crate.getKnownContent());
    } else if (crate.getContent() != null) {
      this.contents = JsonUtils.writeObjectToJsonNode(crate.getContent());
    } else {
      this.contents = crate.getRawContent();
    }
    // HERE IS YOUR PROBLEM
//    this.contents = crate.getRawContent();
    this.availability =
        (crate.getAvailability() == null) ? AvailabilityTypeEnum.NotSet.getCode() : crate.getAvailability().getCode();
    this.manifestId = crate.getCrateManifestType().getId();

  }

  public String getManifestType() {
    return manifestType;
  }

  public void setManifestType(String manifestType) {
    this.manifestType = manifestType;
  }

  public int getManifestId() {
    return manifestId;
  }

  public void setManifestId(int manifestId) {
    this.manifestId = manifestId;
  }

  public ManufacturerDTO getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(ManufacturerDTO manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getManifestRegistrar() {
    return FR8_REGISTRAR;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public JsonNode getContents() {
    return contents;
  }

  public void setContents(JsonNode contents) {
    this.contents = contents;
  }

  public String getParentCrateId() {
    return parentCrateId;
  }

  public void setParentCrateId(String parentCrateId) {
    this.parentCrateId = parentCrateId;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public int getAvailability() {
    return availability;
  }

  public void setAvailability(AvailabilityTypeEnum availability) {
    this.availability =
        (availability == null) ? AvailabilityTypeEnum.NotSet.getCode() : availability.getCode();
  }

  public String getSourceActivityId() {
    return sourceActivityId;
  }

  public void setSourceActivityId(String sourceActivityId) {
    this.sourceActivityId = sourceActivityId;
  }

  @Override
  public String toString() {
    return "CrateDTO{" +
        "manifestType='" + manifestType + '\'' +
        ", manifestId=" + manifestId +
        ", manufacturer=" + manufacturer +
        ", manifestRegistrar='" + manifestRegistrar + '\'' +
        ", id='" + id + '\'' +
        ", label='" + label + '\'' +
        ", contents=" + contents +
        ", parentCrateId='" + parentCrateId + '\'' +
        ", createTime=" + createTime +
        ", availability=" + availability +
        ", sourceActivityId='" + sourceActivityId + '\'' +
        '}';
  }
}
