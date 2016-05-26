package co.fr8.data.crates;

import co.fr8.data.interfaces.manifests.Manifest;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * TODO: Implement
 * Utility class that is used to find Manifest objects
 */
public class ManifestDiscovery {
  /**********************************************************************************/
  // Declarations
  /**********************************************************************************/

  public static final ManifestDiscovery instance = new ManifestDiscovery();

  /**********************************************************************************/

  private Map<CrateManifestType, Object> _typeMapping = new HashMap<>();

  /**********************************************************************************/
  // Functions
  /**********************************************************************************/

  private ManifestDiscovery() {
    ConfigureInitial();
  }

  /**********************************************************************************/

  public void ConfigureInitial() {
    Class manifest = Manifest.class;

    /**
     * The following code gets the objects from the assembly which is equivalent to a jar file
     * any type that is not abstract or is not the Manifest class is registered
     * key parts
     * - Assembly.GetExecutingAssembly() gets the code (libraries, etc) that is currently running
     * - manifest.IsAssignableFrom(x) looks for classes that extend Manifest
     * - x.GetCustomAttribute<CrateManifestTypeAttribute>() ensures that the class has the CrateManifestTypeAttribute
     *
     * The goal is to find all concrete (non-abstract, non-interface) classes that extend Manifest, then register them
     *
     * The Java way to do this probably has nothing to do with inspecting the classes in the .jar file

    foreach (var type in ListAssemblyTypes(Assembly.GetExecutingAssembly()).Where(x => manifest.IsAssignableFrom(x) || x.GetCustomAttribute<CrateManifestTypeAttribute>() != null))
    {
      if (type.IsAbstract || type == manifest)
      {
        continue;
      }

      RegisterManifest(type);
    }
    */
  }

  /**********************************************************************************/


  private static Iterable<Class> ListAssemblyTypes(/*Assembly assembly*/) {
    return null;

    /**
     * This code gets all the types in an assembly
     *
    try
    {
      return assembly.GetTypes();
    }
    catch (ReflectionTypeLoadException ex)
    {
      return ex.Types.Where(type => type != null);
    }
   */
  }

  /**********************************************************************************/

  /**
   * TODO: Document this
   * @param manifest
   */
  public void registerManifest(CrateManifestType manifest) {
    registerManifest(manifest.getClass());
  }

  /**********************************************************************************/

  /**
   * TODO: Document this
   * @param type
   * @return
   */
  public CrateManifestType tryGetManifestType(Object type) {
    return ManifestTypeCache.tryResolveManifest(type);
  }

  /**********************************************************************************/

  /**
   * TODO: Document this
   * @param type
   * @return
   */
  public CrateManifestType getManifestType(CrateManifestType type) {
    CrateManifestType manifestType = ManifestTypeCache.tryResolveManifest(type);

    if (manifestType == null) {
      throw new IllegalArgumentException("Type " + type.getClass() + " is not marked with CrateManifestAttribute or ManifestType is not set");
    }

    return manifestType;
  }

  /**********************************************************************************/

  /**
   * TODO: Document this
   * @param crateManifestType
   * @return
   */
  public CrateManifestType getCrateManifestType(CrateManifestType crateManifestType) {
    return getManifestType(crateManifestType);
  }

  /**********************************************************************************/

  /**
   * TODO: Document this
   * @param manifestType
   * @return
   */
  public Object tryResolveType(CrateManifestType manifestType)
  {
    synchronized (_typeMapping) {
      return _typeMapping.get(manifestType);
    }
  }

  /**
   * TODO: Documnent this
   * @param manifestTypeName
   * @return
   */
  public CrateManifestType tryResolveManifestType(String manifestTypeName) {
    return ManifestTypeCache.tryResolveManifest(manifestTypeName);
  }

  /**********************************************************************************/

  public void registerManifest(Class type) {
    CrateManifestType manifestType = ManifestTypeCache.tryResolveManifest(type);

    if (manifestType == null) {
      throw new IllegalArgumentException("Type: " + type.getName() +
          " is not marked with CrateManifestAttribute or ManifestType is not set");
    }

    synchronized (_typeMapping) {
      _typeMapping.put(manifestType, type);
    }
  }

}
