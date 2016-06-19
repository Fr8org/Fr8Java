package co.fr8.data.crates;

import java.util.*;

/**
 * TODO: Implement
 */
public class CrateStorage extends AbstractCrateStorage {

  public CrateStorage() {
    crates = new TreeMap<>();
  }

  public CrateStorage(Map<String, Crate> crateMap) {
    this.crates = crateMap;
  }

}
