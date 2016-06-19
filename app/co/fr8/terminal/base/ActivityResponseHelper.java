package co.fr8.terminal.base;

import co.fr8.data.interfaces.dto.*;
import co.fr8.util.json.JsonUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import play.libs.Json;

/**
 * TODO: DOCUMENT
 */
public class ActivityResponseHelper {

  private static ActivityResponseDTO addBaseDTO(ActivityResponseDTO activityResponse,
                                                String propertyName, Object objectToAdd) {

    // TODO: check the serialization that happens here. There are multiple calls
    ObjectNode responseBody = (StringUtils.isBlank(activityResponse.getBody()) ?
        Json.newObject() : (ObjectNode) JsonUtils.writeStringToObject(activityResponse.getBody()));

    if (responseBody != null) {
      responseBody.set(propertyName, JsonUtils.writeObjectToJsonNode(objectToAdd));
    }

    activityResponse.setBody(JsonUtils.writeObjectAsString(responseBody));

    // maybe don't have to return anything here
    return activityResponse;
  }

  public static ActivityResponseDTO addErrorDTO(ActivityResponseDTO activityResponse,
                                                ErrorDTO errorDTO) {
    return addBaseDTO(activityResponse, "error", errorDTO);
  }

  public static ActivityResponseDTO addResponseMessageDTO(ActivityResponseDTO activityResponse,
                                                          ResponseMessageDTO responseMessage) {
    return addBaseDTO(activityResponse, "responseMessage", responseMessage);
  }

}
