package co.fr8.data.crates;

import co.fr8.data.constants.MT;
import co.fr8.data.interfaces.manifests.Manifest;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * This class acts as an in-memory cache for CrateManifestType objects
 */
class ManifestTypeCache {

  private static Map<Class, CrateManifestType> MANIFESTS_CACHE = new HashMap<>();
  private static Object GlobalTypeCacheLock = new Object();

  private static Map<String, Integer> nameMap;

  static {
    nameMap = new HashMap<>();

    for (MT manifestType : MT.values()) {
      nameMap.put(manifestType.getFriendlyName(), manifestType.getId());
    }
  }

  public static CrateManifestType tryResolveManifest(String type) {
    Integer typeId = nameMap.get(type);
    CrateManifestType ret = CrateManifestType.UNKNOWN;
    if (typeId != null) {
      ret = new CrateManifestType(type, typeId);
    }

    return ret;
  }

  /**
   * Resolves a CrateManifestType by an associated Manifest class
   * @param type the Manifest instance to use when resolving the
   *             CrateManifestType
   * @return a CrateManifestType object
   */
  public static CrateManifestType tryResolveManifest(final Class<Manifest> type) {
    synchronized (GlobalTypeCacheLock) {
      CrateManifestType manifestType = MANIFESTS_CACHE.get(type);
      if (manifestType != null) {
        return manifestType;
      }

      CrateManifestTypeAnnotation crateManifestTypeAnnotation =
          type.getAnnotation(CrateManifestTypeAnnotation.class);

      manifestType = CrateManifestType.UNKNOWN;

      if (crateManifestTypeAnnotation != null) {
        MT mtEnum = crateManifestTypeAnnotation.manifestType();
        manifestType = MANIFESTS_CACHE.get(mtEnum.getClass());

        if (manifestType == null) {
          if (type.isAssignableFrom(Manifest.class) && !Modifier.isAbstract(type.getModifiers())) {
            manifestType = new CrateManifestType(mtEnum.getFriendlyName(), mtEnum.getId());
          } else {
            manifestType = CrateManifestType.UNKNOWN;
          }
        }
      }

      MANIFESTS_CACHE.put(type, manifestType);

      return manifestType;
    }
  }

  /**
   * Method to resolve a CrateManifestType from a Manifest Object
   * @param content the object of type Manifest for which the CrateManifestType
   *                should be resolved
   * @return a CrateManifestType, if one exists
   */
/*  public static CrateManifestType tryResolveManifest(Object content) {
    if (content == null) {
      return CrateManifestType.UNKNOWN;
    }

    if (content instanceof Manifest) {
      Manifest manifest = (Manifest) content;
      synchronized (GlobalTypeCacheLock) {
        CrateManifestType crateType = MANIFESTS_CACHE.get(manifest.getCrateManifestType().getClass());
        if (crateType == null) {
          MANIFESTS_CACHE.put(manifest.getClass(), manifest.getCrateManifestType());
          return manifest.getCrateManifestType();
        }
      }
    }

    return tryResolveManifest(content);
  }*/

  public static MT tryResolveManifest(Object content) {

    MT ret = null;
    if (content instanceof Manifest)
      ret = MT.findByFriendlyName(((Manifest)content).getCrateManifestType().getType());

    return ret;
  }
}
