package co.fr8.data.crates.serializers;

import co.fr8.data.crates.*;
import co.fr8.data.interfaces.dto.CrateDTO;
import co.fr8.data.interfaces.dto.CrateStorageDTO;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Serializer class for the CrateStorage object
 */
public class CrateStorageSerializer implements ICrateStorageSerializer {
  /**********************************************************************************/
  // Declarations
  /**********************************************************************************/

  public static final CrateStorageSerializer _internalSerializer = new CrateStorageSerializer();

  /**********************************************************************************/

  private Map<CrateManifestType, IManifestSerializer> _serializers = new HashMap<>();

  /**********************************************************************************/
  // Functions
  /**********************************************************************************/

  private CrateStorageSerializer() {
  }

  /**********************************************************************************/
  /// <summary>
  /// Register custom serializer for manifest type.
  /// You don't have to call this method wile your manifest resides in Data project.
  /// </summary>
  /// <param name="manifestType"></param>
  /// <param name="serializer"></param>
  public void registerSerializer(CrateManifestType manifestType, IManifestSerializer serializer) {
    synchronized (_serializers) {
      _serializers.put(manifestType, serializer);
    }
  }

  /**********************************************************************************/

  private IManifestSerializer getSerializer(CrateManifestType type)
  {
    synchronized (_serializers)
    {
      IManifestSerializer serializer = _serializers.get(type);

      if (serializer != null)
      {
        return serializer;
      }

      /* TODO: determine the correct return type */
      Object clrType = ManifestDiscovery.instance.tryResolveType(type);

      if (clrType != null) {
        CrateManifestSerializerAnnotation manifestAttr =
            clrType.getClass().getAnnotation(CrateManifestSerializerAnnotation.class);
/*

        if (manifestAttr == null) {
          serializer = new DefaultSerializer(clrType.getClass());
        } else {
          if (manifestAttr.serializer().getClass().isAssignableFrom(IManifestSerializer.class) &&
              manifestAttr.serializer().GetConstructor(Type.EmptyTypes) != null)
          {
            serializer = (IManifestSerializer) Activator.CreateInstance(manifestAttr.Serializer);
          } else {
            throw new IllegalArgumentException("Invalid serializer was specified for given manifest");
          }
        }
*/

        serializer.initialize(this);
      }

      _serializers.put(type, serializer);

      return serializer;
    }
  }

  /**********************************************************************************/
  /// <summary>
  /// Convert CrateStorageDTO to CrateStorage
  /// </summary>
  /// <param name="rawStorage"></param>
  /// <returns></returns>
  public ICrateStorage convertFromDto(CrateStorageDTO rawStorage) {
    CrateStorage storage = new CrateStorage();
/*
    if (rawStorage != null && rawStorage.Crates != null)
    {
      foreach (var crateDto in rawStorage.Crates)
      {
        storage.Add(convertFromDto(crateDto));
      }
    }*/

    return storage;
  }

  /**********************************************************************************/
  /// <summary>
  /// Convert CrateStorage to DTO
  /// </summary>
  /// <param name="storage"></param>
  /// <returns></returns>
  public CrateStorageDTO convertToDto(ICrateStorage storage)
  {
    CrateStorageDTO storageSerializationProxy = new CrateStorageDTO();

//    {
//      Crates = new CrateDTO[storage.Count]
//    };

    int id = 0;

    storage.forEach(create -> {
//      storageSerializationProxy.Crates[id] = ConvertToDto(crate);
//      id ++;
    });


    return storageSerializationProxy;
  }

  /**********************************************************************************/
  /// <summary>
  /// Convert DTO to Crate instance
  /// </summary>
  /// <param name="proxy"></param>
  /// <returns></returns>
  public Crate convertFromDto(CrateDTO proxy) {

    CrateManifestType manifestType =
        new CrateManifestType(proxy.getManifestType(), proxy.getManifestId());

    IManifestSerializer serializer = getSerializer(manifestType);
    Crate crate;

    if (serializer != null) {
      Object content = proxy.getContents() != null ? serializer.deserialize(proxy.getContents()) : null;

      if (content != null) {
        crate = Crate.fromContent(content, proxy.getId());
      } else {
        crate = new Crate(manifestType, proxy.getId());
      }
    } else {
      crate = Crate.fromJson(manifestType, proxy.getId(), proxy.getContents());
    }

    crate.setLabel(proxy.getLabel());
    crate.setAvailability(proxy.getAvailability());
    return crate;
  }

  /**********************************************************************************/
  /// <summary>
  /// Convert crate to DTO
  /// </summary>
  /// <param name="crate"></param>
  /// <returns></returns>
  public CrateDTO convertToDto(Crate crate) {
    IManifestSerializer serializer = getSerializer(crate.getCrateManifestType());
    CrateDTO crateDto = new CrateDTO();
    /**
        new CrateDTO(crate.getId(), crate.getLabel(),
        crate.getCrateManifestType().getId(), crate.getCrateManifestType().getType(),
        crate.getAvailability());

    if (serializer != null) {
      crateDto.Contents = serializer.Serialize(crate.Get<object>());
    } else {
      crateDto.Contents = crate.GetRaw();
    }
     */

    return crateDto;
  }

}
