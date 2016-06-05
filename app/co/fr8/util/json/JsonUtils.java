package co.fr8.util.json;

import co.fr8.data.interfaces.dto.ActivityDTO;
import co.fr8.data.interfaces.dto.CrateStorageDTO;
import co.fr8.data.interfaces.manifests.Manifest;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

  public static <T> T writeNodeAsObject(JsonNode node, Class<T> type) {
    try {
      return objectMapper.treeToValue(node, type);
    } catch (JsonProcessingException e) {
      Logger.error("Unable to map node " + node + " to " + type.getName());
    }
    return null;
  }

  public static String prettyPrintObjectString(Object object) {
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    return writeObjectAsString(object);
  }

  public static String writeObjectAsString(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      Logger.error("Exception converting object of type " + object.getClass().getName(), e);
    }

    return StringUtils.EMPTY;
  }

  public static JsonNode writeObjectToJsonNode(Object object) {
    try {
      return objectMapper.valueToTree(object);
    } catch (IllegalArgumentException e) {
      Logger.error("Exception converting object to JsonNode: " + object, e);
    }

    return null;
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

      Logger.debug("Extracted ActivityPayload as JSON: " + dtoString);

//      ActivityPayload ret = new ActivityPayload();
      return objectMapper.readValue(dtoString, ActivityDTO.class);
//      return ret;
    } catch (IOException e) {
      Logger.error("Exception parsing activityDTO from " + dtoString, e);
    }
    return null;
  }

  public static <T> T writeStringToObject(String rawString, Class<T> theClass) {
    try {
      return objectMapper.readValue(rawString, theClass);
    } catch (IOException e) {
      Logger.error("Exception while converting string " + rawString + " to class " + theClass.getName());
    }

    return null;
  }

  public static CrateStorageDTO writeStringToCrateStorageDTO(String dtoString) {
    return writeStringToObject(dtoString, CrateStorageDTO.class);
  }
}
