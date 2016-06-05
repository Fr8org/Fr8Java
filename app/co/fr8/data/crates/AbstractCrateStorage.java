package co.fr8.data.crates;

import co.fr8.data.constants.MT;
import co.fr8.data.interfaces.dto.CrateDescriptionDTO;
import co.fr8.data.interfaces.manifests.Manifest;
import co.fr8.hub.managers.IUpdatableCrateStorage;
import co.fr8.util.logging.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO: Implement
 */
abstract public class AbstractCrateStorage implements Iterable<Crate>, IUpdatableCrateStorage {

  protected Map<String, Crate> crateMap;


  @Override
  public List<Crate> getCratesAsList() {
    return new ArrayList<>(crateMap.values());
  }


  public Crate getFirstCrateOrDefault(CrateDescriptionDTO selectedCrate) {
    Optional<Crate> ret = crateMap.values().stream().filter(
        c -> c.getLabel().equalsIgnoreCase(selectedCrate.getLabel()) &&
            c.getCrateManifestType().equals(selectedCrate.getManifestType())
    ).findFirst();

    if (ret.isPresent())
      return ret.get();

    return null;
  }

  public List<Crate> getCratesOfType(MT type) {
    MT manifestType = ManifestTypeCache.tryResolveManifest(type);

    if (manifestType != null) {
      return crateMap.values().stream().filter(c -> c.getCrateManifestType()
          .equals(manifestType)).collect(Collectors.toList());
    }

    return null;
  }

  public MT getManifestType(MT type) {
    return ManifestTypeCache.tryResolveManifest(type);
  }

  public int remove(MT manifest) {

    if (manifest!= null) {
      crateMap.values().removeIf(c -> c.getCrateManifestType().equals(manifest));
    }

    return 0;
  }

  public void add(Crate crate) {
    // TODO: make sure very crate has an ID
    Logger.debug("Adding crate: " + crate);
    crateMap.put(crate.getId(), crate);
  }

  abstract public String findField(String key);
}
