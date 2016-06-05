package co.fr8.data.interfaces.dto;

import co.fr8.data.crates.Crate;
import co.fr8.data.states.AvailabilityTypeEnum;
import co.fr8.util.json.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

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
  private Date createTime;
  private AvailabilityTypeEnum availability;

  public CrateDTO(Crate crate) {
//    this.manifestId = crate.getId();
    this.manifestType = crate.getCrateManifestType().getFriendlyName();
    this.label = crate.getLabel();
    this.id = crate.getId();
    this.contents = JsonUtils.writeObjectToJsonNode(crate.getContent());
    // HERE IS YOUR PROBLEM
//    this.contents = crate.getRawContent();
    this.availability = crate.getAvailability();

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

  public AvailabilityTypeEnum getAvailability() {
    return availability;
  }

  public void setAvailability(AvailabilityTypeEnum availability) {
    this.availability = availability;
  }

  /*

  [JsonProperty("manifestType")]
  public string ManifestType { get; set; }

  [JsonProperty("manifestId")]
  public int ManifestId { get; set; }

  [JsonProperty("manufacturer")]
  public ManufacturerDTO Manufacturer { get; set; }

  [JsonProperty("manifestRegistrar")]
  public string ManifestRegistrar
  {
    get { return "www.fr8.co/registry"; }
  }

  [JsonProperty("id")]
  public string Id { get; set; }

  [JsonProperty("label")]
  public string Label { get; set; }

  [JsonProperty("contents")]
  public JToken Contents { get; set; }

  [JsonProperty("parentCrateId")]
  public string ParentCrateId { get; set; }

  [JsonProperty("createTime")]
      [JsonConverter(typeof(CreateTimeConverter))]
  public DateTime CreateTime { get; set; }

  [JsonProperty("availability")]
      [JsonConverter(typeof(AvailabilityConverter))]
  public AvailabilityType Availability { get; set; }
*/
}
