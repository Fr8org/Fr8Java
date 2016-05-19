package co.fr8.data.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * TODO: Implement
 */
public class PayloadDTO {

  @JsonProperty("container")
  private CrateStorageDTO crateStorage;

  private final UUID containerId;

  public PayloadDTO(UUID containerId) {
    this.containerId = containerId;
  }

  public CrateStorageDTO getCrateStorage() {
    return crateStorage;
  }

  public void setCrateStorage(CrateStorageDTO crateStorage) {
    this.crateStorage = crateStorage;
  }

  public UUID getContainerId() {
    return containerId;
  }
}
