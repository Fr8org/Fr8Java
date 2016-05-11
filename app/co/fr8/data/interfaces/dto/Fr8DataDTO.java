package co.fr8.data.interfaces.dto;

import java.util.UUID;

/**
 * Datat Transfer Object which represents Fr8Data
 */
public class Fr8DataDTO {
  private ActivityDTO activityDTO;
  private UUID containerId;


  /// <summary>
  /// This property is used for integration tests
  /// </summary>
  private String explicitData;

  // TODO: Is this necessary
  public Fr8DataDTO() {
  }

  public Fr8DataDTO(ActivityDTO activityDTO, UUID containerId, String explicitData) {
    this.activityDTO = activityDTO;
    this.containerId = containerId;
    this.explicitData = explicitData;
  }

  public ActivityDTO getActivityDTO() {
    return activityDTO;
  }

  public void setActivityDTO(ActivityDTO activityDTO) {
    this.activityDTO = activityDTO;
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
}
