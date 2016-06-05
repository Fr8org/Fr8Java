package co.fr8.terminal.infrastructure;

import co.fr8.data.interfaces.dto.PayloadDTO;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.net.HttpUtils;

import java.util.UUID;

import static co.fr8.play.ApplicationConstants.HUB_API_PATH;

/**
 * TODO: Implement
 */
public class DefaultHubCommunicator implements IHubCommunicator {

  @Override
  public void configure(String terminalName) {

  }

  @Override
  public PayloadDTO getPayload(UUID containerId) {
    String response = HttpUtils.get(buildContainerPath(containerId.toString()));
//    var uri = new Uri($"{GetHubUrlWithApiVersion()}/containers/payload?id={containerId.ToString("D")}", UriKind.Absolute);
//    var payloadDTOTask = await _restfulServiceClient.GetAsync<PayloadDTO>(uri, containerId.ToString(), await GetHMACHeader(uri));
    return JsonUtils.writeStringToObject(response, PayloadDTO.class);
  }

  private String buildContainerPath(String containerId) {
    return HUB_API_PATH + "/containers/payload?id=" + containerId;
  }
}
