package co.fr8.data.interfaces.manifests;

import co.fr8.data.constants.MT;

/**
 * ManifestDescription Crate Manifest type
 * Defines the description of a manifest
 */
public class ManifestDescriptionCM extends Manifest {

  public String id;
  public String name;
  public String version;
  public String sampleJSON;
  public String description;
  public String registeredBy;

  public ManifestDescriptionCM() {
    super(MT.ManifestDescription);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getSampleJSON() {
    return sampleJSON;
  }

  public void setSampleJSON(String sampleJSON) {
    this.sampleJSON = sampleJSON;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getRegisteredBy() {
    return registeredBy;
  }

  public void setRegisteredBy(String registeredBy) {
    this.registeredBy = registeredBy;
  }
}
