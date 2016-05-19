package co.fr8.hub.managers;

import co.fr8.data.crates.Crate;
import co.fr8.data.crates.ICrateStorage;

/**
 * TODO: Implement
 */
public interface IUpdatableCrateStorage {

  void replace(ICrateStorage storage);

  void flush();

  void discardChanges();

  Crate getFirstCrate();
  int replaceByLabel(Crate crate);

}
