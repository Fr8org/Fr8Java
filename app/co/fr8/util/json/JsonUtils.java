package co.fr8.util.json;

import co.fr8.data.interfaces.manifests.Manifest;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

/**
 * TODO: Implement
 */
public class JsonUtils {
  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static String writeManifestJsonString(Manifest manifest) {
    try {
      return objectMapper.writeValueAsString(manifest);
    } catch (JsonProcessingException e) {
      Logger.error("Exception while writing object to JSON string" + manifest, e);
    }

    return StringUtils.EMPTY;
  }
}
