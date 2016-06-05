package co.fr8.data.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * TODO: Implement
 */
public class PayloadDTO {

  private UUID containerId;

  @JsonProperty("container")
  private CrateStorageDTO crateStorage;

  public PayloadDTO() {
  }

  public PayloadDTO(UUID containerId) {
    this.containerId = containerId;
  }

  public UUID getContainerId() {
    return containerId;
  }

  public void setContainerId(UUID containerId) {
    this.containerId = containerId;
  }

  public CrateStorageDTO getCrateStorage() {
    return crateStorage;
  }

  public void setCrateStorage(CrateStorageDTO crateStorage) {
    this.crateStorage = crateStorage;
  }
}
