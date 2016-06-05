package co.fr8.data.interfaces.dto;

import java.util.UUID;

/**
 * Data Transfer Object which represents Fr8Data
 */
public class Fr8DataDTO {
  private ActivityDTO activityPayload;
  private UUID containerId;


  /// <summary>
  /// This property is used for integration tests
  /// </summary>
  private String explicitData;

  // TODO: Is this necessary
  public Fr8DataDTO() {
  }

  public Fr8DataDTO(ActivityDTO activityPayload, String containerId) {
    this.activityPayload = activityPayload;
    this.containerId = UUID.fromString(containerId);
  }

  public Fr8DataDTO(ActivityDTO activityPayload, UUID containerId, String explicitData) {
    this.activityPayload = activityPayload;
    this.containerId = containerId;
    this.explicitData = explicitData;
  }

  public ActivityDTO getActivityPayload() {
    return activityPayload;
  }

  public void setActivityPayload(ActivityDTO activityPayload) {
    this.activityPayload = activityPayload;
  }

  public UUID getContainerId() {
    return containerId;
  }

  public void setContainerId(UUID containerId) {
    this.containerId = containerId;
  }

  public String getExplicitData() {
    return explicitData;
  }

  public void setExplicitData(String explicitData) {
    this.explicitData = explicitData;
  }

  @Override
  public String toString() {
    return "Fr8DataDTO{" +
        "activityPayload=" + activityPayload +
        ", containerId=" + containerId +
        ", explicitData='" + explicitData + '\'' +
        '}';
  }
}
