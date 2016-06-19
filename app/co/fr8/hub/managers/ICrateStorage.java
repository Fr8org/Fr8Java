package co.fr8.hub.managers;

import co.fr8.data.crates.Crate;
import co.fr8.data.interfaces.manifests.Manifest;

import java.util.List;
import java.util.Map;

/**
 * TODO: Document
 */
public interface ICrateStorage {

  List<Crate> getCratesAsList();
  Map<String, Crate> getCratesMap();
  int getCount();
  void add(Crate crate);
  void add(Crate[] crate);
  void clear();
  Crate getFirstCrate();
  void remove(String key);
  <T extends Manifest> void remove(Class<T> manifestType);
  String findField(String key);
}
