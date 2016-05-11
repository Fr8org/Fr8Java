package co.fr8.data.crates.serializers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;

/**
 * TODO: Implement
 */
public class DefaultSerializer implements IManifestSerializer {

  private final Class _targetType;

  private static JsonSerializer SERIALIZER;
//  = JsonSerializer.Create(new JsonSerializerSettings {
//    ReferenceLoopHandling = ReferenceLoopHandling.Ignore
//  });

  public DefaultSerializer(Class targetType) {
    _targetType = targetType;
  }

  public void initialize(ICrateStorageSerializer storageSerializer) {
  }

  public Object deserialize(JsonNode crateContent) {
    if (crateContent == null) {
      return null;
    }

    return null;
//    return crateContent.ToObject(_targetType, Serializer);
  }

  public JsonNode serialize(Object content) {
//    using (JTokenWriter writer = new JTokenWriter())
//    {
//      Serializer.Serialize(writer, content);
//      return writer.Token;
//    }
    return null;
  }
}
