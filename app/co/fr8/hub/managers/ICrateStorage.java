package co.fr8.hub.managers;

import co.fr8.data.crates.Crate;
import co.fr8.data.interfaces.manifests.Manifest;

import java.util.List;

/**
 * TODO: Document
 */
public interface ICrateStorage extends Iterable<Crate> {

  List<Crate> getCratesAsList();
  int getCount();
  void add(Crate crate);
  void add(Crate[] crate);
  void clear();
  <T extends Manifest> int remove(Class<T> manifest);
  int replace(Crate original, Crate replacement);
}
