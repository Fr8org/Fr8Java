package co.fr8.data.crates;

import co.fr8.data.constants.MT;
import co.fr8.data.interfaces.dto.CrateDTO;
import co.fr8.data.interfaces.manifests.Manifest;
import co.fr8.data.states.AvailabilityTypeEnum;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.UUID;

/**
 * Object representation of a Crate
 */
public class Crate<T extends Manifest> {

  private MT crateManifestType;
  private Object knownContent;
  private JsonNode rawContent;
  private String id;
  private String label;
  private AvailabilityTypeEnum availability;
  private T content;

  public Crate(MT crateManifestType) {
    this.crateManifestType = crateManifestType;
    this.id = generateUUIDString();
  }

  public Crate(MT crateManifestType, String id) {
    this.crateManifestType = crateManifestType;
    this.id = id;
  }

  public Crate(MT crateManifestType, JsonNode content) {
    this.crateManifestType = crateManifestType;
    this.id = generateUUIDString();
  }

  public Crate(MT crateManifestType, String id, Object content) {
    this.crateManifestType = crateManifestType;
    this.id = id;
    this.knownContent = content;
  }

  public Crate(MT crateManifestType, T content) {
    this.crateManifestType = crateManifestType;
    this.content = content;
    this.id = generateUUIDString();
  }

  public Crate(MT crateManifestType, String label, T content) {
    this.crateManifestType = crateManifestType;
    this.content = content;
    this.id = generateUUIDString();
    this.label = label;
  }

  public Crate(MT crateManifestType, String id, JsonNode content) {
    this.crateManifestType = crateManifestType;
    this.id = id;
    this.rawContent = content;
  }

  public Crate(String label, JsonNode content) {
    this.crateManifestType = MT.UNKNOWN;
    this.id = generateUUIDString();
    this.label = label;
    this.rawContent = content;
  }

  public Crate(MT crateManifestType, String id, AvailabilityTypeEnum availability) {
    this.crateManifestType = crateManifestType;
    this.id = id;
    this.availability = availability;
  }

  public Crate(MT crateManifestType, String label, Object content, AvailabilityTypeEnum availability) {
    this.crateManifestType = crateManifestType;
    this.id = generateUUIDString();
    this.availability = (availability == null) ? AvailabilityTypeEnum.NotSet : availability;
    this.knownContent = content;
    this.label = label;
  }

  public Crate(MT crateManifestType, String label, JsonNode content, AvailabilityTypeEnum availability) {
    this.crateManifestType = crateManifestType;
    this.id = generateUUIDString();
    this.availability = (availability == null) ? AvailabilityTypeEnum.NotSet : availability;
    this.rawContent = content;
    this.label = label;
  }

  public Crate(MT crateManifestType, UUID id,
               AvailabilityTypeEnum availability, String label, Object content) {
    this.id = id.toString();
    this.availability = availability;
    this.crateManifestType = crateManifestType;
    this.label = label;
    this.knownContent = content;
  }

  public Crate(CrateDTO crateDTO) {
    this.id = crateDTO.getId();
    this.availability = AvailabilityTypeEnum.getByCode(crateDTO.getAvailability());
    this.crateManifestType =
        MT.findByFriendlyName(crateDTO.getManifestType());
    this.label = crateDTO.getLabel();
    this.rawContent = crateDTO.getContents();
    this.knownContent = crateDTO.getContents();
  }
//
//  public static Crate<T> fromContent(String label, T content) {
//    return fromContent(label, content, AvailabilityTypeEnum.NotSet);
//  }

//  public static Crate<T> fromContent(String label, T content, AvailabilityTypeEnum availability) {
//    return new Crate(fromContentUnsafe(label, content, availability));
//  }

  public static Crate fromContent(String label, Manifest content) {
    return new Crate(getManifest(content), label, content, AvailabilityTypeEnum.NotSet);
  }

  public static Crate fromContent(String label, Manifest content, AvailabilityTypeEnum availabilityType) {
    return new Crate(getManifest(content), label, content, availabilityType);
  }

  public static Crate fromContentUnsafe(String label, Object content, AvailabilityTypeEnum availabilityType) {
    return new Crate(getManifest(content), label, content, availabilityType);
  }

  public static Crate fromJson(String label, JsonNode content) {
    return new Crate(label, content);
  }

  public static Crate fromJson(MT manifestType, String id, JsonNode content) {
    return new Crate(manifestType, id, content);
  }

  public static Crate fromJson(MT manifestType, JsonNode content) {
    return new Crate(manifestType, content);
  }

  public static Crate fromContent(Object content, String id) {
    return new Crate(getManifest(content), id, content);
  }

  public void putContent(Object content) {
    if (getManifest(content) != crateManifestType) {
      Logger.error("Content manifest is not compatible with crate manifest " + content);
      throw new IllegalArgumentException("Content manifest is not compatible with crate manifest " + content);
    }
    knownContent = content;
    rawContent = JsonUtils.writeObjectToJsonNode(content);
  }

  public boolean isOfType(MT type) {
    MT manifestType = ManifestTypeCache.tryResolveManifest(type);

    if(manifestType == null) {
      return false;
    }

    return crateManifestType.equals(manifestType);
  }

  public <T> T getTypedContent(Class<T> type) {
    return type.cast(knownContent);
  }

  public Object getKnownContent() {
    return knownContent;
  }

  private static MT getManifest(Object content) {
    MT manifestType = ManifestTypeCache.tryResolveManifest(content);
    if (manifestType == null) {
      Logger.error("Content does not have MT annotation " + content);
      throw new IllegalArgumentException("Content does not have MT annotation");
    }
    return manifestType;
  }

  private String generateUUIDString() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }

  public String getId() {
    return id;
  }

  public MT getCrateManifestType() {
    return crateManifestType;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public AvailabilityTypeEnum getAvailability() {
    return availability;
  }

  public void setAvailability(AvailabilityTypeEnum availability) {
    this.availability = availability;
  }

  public JsonNode getRawContent() {
    return rawContent;
  }

  public T getContent() {
    return content;
  }

  @Override
  public String toString() {
    return "Crate{" +
        "crateManifestType=" + crateManifestType +
        ", knownContent=" + knownContent +
        ", rawContent=" + rawContent +
        ", id='" + id + '\'' +
        ", label='" + label + '\'' +
        ", availability=" + availability +
        ", content=" + content +
        '}';
  }
}