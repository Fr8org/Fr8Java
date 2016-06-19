package co.fr8.hub.managers;

import co.fr8.data.crates.AbstractCrateStorage;
import co.fr8.data.crates.Crate;

/**
 * TODO: Implement
 */
public interface IUpdatableCrateStorage extends ICrateStorage {

  void replace(AbstractCrateStorage storage);
  int replaceByLabel(Crate crate);

}
