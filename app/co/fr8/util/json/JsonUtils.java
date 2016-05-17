package co.fr8.util.json;

import co.fr8.data.interfaces.dto.ActivityDTO;
import co.fr8.data.interfaces.manifests.Manifest;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * TODO: Implement
 */
public class JsonUtils {
  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static String writeManifestJsonString(Manifest manifest) {
    return writeObjectAsString(manifest);
    /*try {
      return objectMapper.writeValueAsString(manifest);
    } catch (JsonProcessingException e) {
      Logger.error("Exception while writing object to JSON string" + manifest, e);
    }

    return StringUtils.EMPTY;*/
  }

  public static String writeObjectAsString(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      Logger.error("Exception converting object of type " + object.getClass().getName(), e);
    }

    return StringUtils.EMPTY;
  }

  public static JsonNode writeStringToObject(String jsonString) {
    try {
      return objectMapper.readTree(jsonString);
    } catch (IOException e) {
      Logger.error("Exception converting string to JsonNode " + jsonString, e);
    }
    return null;
  }

  public static ActivityDTO writeStringToActivityDTO(String dtoString) {
    try {
//      JsonNode activityDTOAsJson = writeStringToObject(dtoString);

      Logger.debug("Extracted ActivityDTO as JSON: " + dtoString);

//      ActivityDTO ret = new ActivityDTO();
      return objectMapper.readValue(dtoString, ActivityDTO.class);
//      return ret;
    } catch (IOException e) {
      Logger.error("Exception parsing activityDTO from " + dtoString, e);
    }
    return null;
  }
}
