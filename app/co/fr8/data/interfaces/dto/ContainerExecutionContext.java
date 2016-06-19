package co.fr8.data.interfaces.dto;

import co.fr8.data.crates.AbstractCrateStorage;

import java.util.UUID;

/**
 * TODO: Implement
 */
public class ContainerExecutionContext {

  private AbstractCrateStorage payloadStorage;

  private final UUID containerId;

  public ContainerExecutionContext(UUID containerId) {
    this.containerId = containerId;
  }

  public ContainerExecutionContext(UUID containerId, AbstractCrateStorage payloadStorage) {
    this.containerId = containerId;
    this.payloadStorage = payloadStorage;
  }

  public AbstractCrateStorage getPayloadStorage() {
    return payloadStorage;
  }

  public void setPayloadStorage(AbstractCrateStorage payloadStorage) {
    this.payloadStorage = payloadStorage;
  }

  public UUID getContainerId() {
    return containerId;
  }
}
