package co.fr8.data.crates;

import java.util.Map;
import java.util.TreeMap;

/**
 * TODO: Implement
 */
public class CrateStorage extends AbstractCrateStorage {

  ManifestTypeCache manifestTypeCache;

  public CrateStorage() {
    crates = new TreeMap<>();
    manifestTypeCache = new ManifestTypeCache();
  }

  public CrateStorage(Map<String, Crate> crateMap) {
    this.crates = crateMap;
  }

}
