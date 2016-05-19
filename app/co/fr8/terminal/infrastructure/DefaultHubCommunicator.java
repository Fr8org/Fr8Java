package co.fr8.terminal.infrastructure;

import co.fr8.data.interfaces.dto.ActivityDTO;
import co.fr8.data.interfaces.dto.PayloadDTO;

import java.util.UUID;

/**
 * TODO: Implement
 */
public class DefaultHubCommunicator implements IHubCommunicator {

  @Override
  public void configure(String terminalName) {

  }

  @Override
  public PayloadDTO getPayload(ActivityDTO activityDO, UUID containerId, String userId) {
    return null;
  }
}
