package co.fr8.data.interfaces.manifests;

import co.fr8.util.json.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * TODO: DOCUMENT
 */
public class StackLocalData {

  private String type;
  private JsonNode data;

  public <T> T readAs(Class<T> type) {
    return JsonUtils.writeNodeAsObject(data, type);
  }
}
