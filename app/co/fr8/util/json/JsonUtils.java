package co.fr8.util.json;

import co.fr8.data.controls.AbstractControlDefinition;
import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ActivityDTO;
import co.fr8.data.interfaces.dto.CrateStorageDTO;
import co.fr8.data.interfaces.manifests.Manifest;
import co.fr8.play.ApplicationConstants;
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

  static {
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    if (ApplicationConstants.DEBUG_JSON)
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
  }

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
      Logger.error("Unable to map node " + node + " to " + type.getName(), e);
    }
    return null;
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

  /*
   Since we are getting controls in Json, we need heavy json processing.
    Methods like this will increase in time.
   */
  public static <T extends AbstractControlDefinition> T getControl(JsonNode controls, T control, ControlTypeEnum controlType) {
    if (controls.has("Controls")) {
      for (JsonNode controlNode : controls.get("Controls")) {
        Logger.debug("Checking control of type: " + controlNode.get("type"));
        if (controlNode.get("type").textValue().equals(controlType.getFriendlyName())) {
          if (control != null && control.getName() != null) {
            if (controlNode.get("name").textValue().equals(control.getName())) {
              control = JsonUtils.writeNodeAsObject(controlNode, (Class<T>) control.getClass());
            }
          } else {
            Logger.error("Error. Check the initialization of control: " + control + ", name is required");
          }
        }
      }//end of for
    } else {
      Logger.error("No Controls in the crate");
    }
    return control;
  }
}
