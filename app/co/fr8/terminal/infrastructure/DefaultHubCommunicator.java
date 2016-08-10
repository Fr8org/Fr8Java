package co.fr8.terminal.infrastructure;

import co.fr8.data.interfaces.dto.Fr8HubSecurityDTO;
import co.fr8.data.interfaces.dto.PayloadDTO;

import java.util.UUID;

import static co.fr8.play.ApplicationConstants.HUB_API_PATH;

/**
 * TODO: Implement
 */
public class DefaultHubCommunicator implements IHubCommunicator {

  private Fr8HubSecurityDTO fr8HubSecurityDTO;

  @Override
  public void configure(Fr8HubSecurityDTO fr8HubSecurityDTO) {
    this.fr8HubSecurityDTO = fr8HubSecurityDTO;
  }

  @Override
  public PayloadDTO getPayload(UUID containerId) {
//    String response = HttpUtils.getSecure(buildContainerPath(containerId.toString()), getFr8HubSecurityDTO());
//    var uri = new Uri($"{GetHubUrlWithApiVersion()}/containers/payload?id={containerId.ToString("D")}", UriKind.Absolute);
//    var payloadDTOTask = await _restfulServiceClient.GetAsync<PayloadDTO>(uri, containerId.ToString(), await GetHMACHeader(uri));
//    return JsonUtils.writeStringToObject(response, PayloadDTO.class);
    return null;
  }

  private String buildContainerPath(String containerId) {
    return HUB_API_PATH + "/containers/payload?id=" + containerId;
  }

  public Fr8HubSecurityDTO getFr8HubSecurityDTO() {
    return fr8HubSecurityDTO;
  }

  public void setFr8HubSecurityDTO(Fr8HubSecurityDTO fr8HubSecurityDTO) {
    this.fr8HubSecurityDTO = fr8HubSecurityDTO;
  }

}
