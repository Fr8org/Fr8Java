package co.fr8.terminal.base;

import co.fr8.data.interfaces.dto.*;
import co.fr8.util.json.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
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

    return activityResponse;
  }

  private static <T> T tryParseBaseDTO(ActivityResponseDTO activityResponse,
                                         String propertyName, Class<T> type) {
    if (StringUtils.isBlank(activityResponse.getBody()))
      return null;

    JsonNode responseBody = JsonUtils.writeStringToObject(activityResponse.getBody());

    if (responseBody == null || !responseBody.has(propertyName))
      return null;

    return JsonUtils.writeNodeAsObject(responseBody, type);
  }

  public static ActivityResponseDTO addErrorDTO(ActivityResponseDTO activityResponse,
                                                ErrorDTO errorDTO) {
    return addBaseDTO(activityResponse, "error", errorDTO);
  }

  public static ErrorDTO tryParseErrorDTO(ActivityResponseDTO activityResponse) {
    return tryParseBaseDTO(activityResponse, "error", ErrorDTO.class);
  }

  public static ActivityResponseDTO addPayloadDTO(ActivityResponseDTO activityResponse,
                                                  PayloadDTO payload) {
    return addBaseDTO(activityResponse, "payload", payload);
  }

  public static PayloadDTO tryParsePayloadDTO(ActivityResponseDTO activityResponse) {
    return tryParseBaseDTO(activityResponse, "payload", PayloadDTO.class);
  }

  public static ActivityResponseDTO addResponseMessageDTO(ActivityResponseDTO activityResponse,
                                                          ResponseMessageDTO responseMessage) {
    return addBaseDTO(activityResponse, "responseMessage", responseMessage);
  }

  public static ResponseMessageDTO tryParseResponseMessageDTO(ActivityResponseDTO activityResponse) {
    return tryParseBaseDTO(activityResponse, "responseMessage", ResponseMessageDTO.class);
  }

  public static ActivityResponseDTO addActivityDTO(ActivityResponseDTO activityResponse, ActivityDTO activity) {
    return addBaseDTO(activityResponse, "activity", activity);
  }

  public static ActivityDTO tryParseActivityDTO(ActivityResponseDTO activityResponse) {
    return tryParseBaseDTO(activityResponse, "activity", ActivityDTO.class);
  }

  public static ActivityResponseDTO addDocumentationDTO(ActivityResponseDTO activityResponse,
                                                        DocumentationDTO documentation) {
    return addBaseDTO(activityResponse, "documentation", documentation);
  }

  public static DocumentationDTO tryParseDocumentationDTO(ActivityResponseDTO activityResponse) {
    return tryParseBaseDTO(activityResponse, "documentation", DocumentationDTO.class);
  }
}
