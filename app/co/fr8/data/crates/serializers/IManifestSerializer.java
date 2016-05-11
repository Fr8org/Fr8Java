package co.fr8.data.crates.serializers;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Interface for the classes that can be usd to serialize manifests
 */
public interface IManifestSerializer {
  /// <summary>
  ///  This will be called once per CrateStorageSerializer. Use this if you need to serialize/deserialize nested CrateStorage.
  /// </summary>
  /// <param name="storageSerializer"></param>
  void initialize(ICrateStorageSerializer storageSerializer);

  /// <summary>
  /// Deserialize manifest from JToken
  /// </summary>
  /// <param name="crateContent"></param>
  /// <returns></returns>
  Object deserialize(JsonNode crateContent);

  /// <summary>
  /// Serialize manifest to JToken
  /// </summary>
  /// <param name="content"></param>
  /// <returns></returns>
  JsonNode serialize(Object content);
}
