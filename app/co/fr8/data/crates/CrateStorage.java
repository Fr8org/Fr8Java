package co.fr8.data.crates;

import co.fr8.data.interfaces.manifests.Manifest;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Charles Pretzer on 4/19/2016.
 */
public class CrateStorage extends ICrateStorage {

  private Map<String, Crate> crateMap;

  public CrateStorage() {
    crateMap = new HashMap<>();
  }

  public CrateStorage(Map<String, Crate> crateMap) {
    this.crateMap = crateMap;
  }

  public int getEntryCount() {
    return (crateMap == null) ? 0 : crateMap.size();
  }

  public Crate getCrateByKey(String key) {
    Crate ret = null;

    if (crateMap != null) {
      ret = crateMap.get(key);
    }

    return ret;
  }

  public void setCrateForKey(String key, Crate crate) {
    initializeMap();
    crateMap.put(key, crate);
  }

  public void addCrate(Crate crate) {
    initializeMap();
    crateMap.put(crate.getId(), crate);
  }

  public void clear() {
    initializeMap();
  }

  public int removeCrates(Predicate<Crate> predicate) {
    int affectedItems = 0;

    for(String key : crateMap.keySet()) {
      if(predicate.test(crateMap.get(key))) {
        affectedItems++;
        crateMap.remove(key);
      }
    }

    return affectedItems;
  }

  public int replaceCrate(Predicate<Crate> predicate, Crate crate) {
    int affectedItems = 0;

    for(String key : crateMap.keySet()) {
      if (predicate.test(crateMap.get(key))) {
        affectedItems++;
        crateMap.replace(key, crate);
      }
    }

    return affectedItems;
  }

  public Iterator<Crate> getIterator() {
    initializeMap();
    return crateMap.values().iterator();
  }

  public CrateManifestType getManifestType(Class<Manifest> type) {
    return ManifestTypeCache.tryResolveManifest(type.getClass());
  }

  private void initializeMap() {
    if (crateMap == null) {
      crateMap = new HashMap<>();
    }
  }

  @Override
  public Iterator<Crate> iterator() {
    return crateMap.values().iterator();
  }

  @Override
  public void forEach(Consumer<? super Crate> action) {

  }

  @Override
  public Spliterator<Crate> spliterator() {
    return null;
  }
}
