package co.fr8.data.crates;

import java.util.*;

/**
 * TODO: Implement
 */
public class CrateStorage extends AbstractCrateStorage {

  ManifestTypeCache manifestTypeCache;

  public CrateStorage() {
    crates = new TreeMap<>();
    System.out.println("Giriyor, girmiyor.");
    manifestTypeCache = new ManifestTypeCache();
  }

  public CrateStorage(Map<String, Crate> crateMap) {
    this.crates = crateMap;
  }

}
