package co.fr8.data.crates;

import co.fr8.data.constants.MT;
import co.fr8.data.interfaces.dto.CrateDescriptionDTO;
import co.fr8.data.interfaces.manifests.Manifest;
import co.fr8.hub.managers.IUpdatableCrateStorage;
import co.fr8.util.CollectionUtils;
import co.fr8.util.logging.Logger;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * TODO: Implement
 */
abstract public class AbstractCrateStorage implements Iterable<Crate>, IUpdatableCrateStorage {

  protected Map<String, Crate> crates;


  @Override
  public List<Crate> getCratesAsList() {
    return new ArrayList<>(crates.values());
  }


  public Crate getFirstCrateOrDefault(CrateDescriptionDTO selectedCrate) {
    Optional<Crate> ret = crates.values().stream().filter(
        c -> c.getLabel().equalsIgnoreCase(selectedCrate.getLabel()) &&
            c.getCrateManifestType().getFriendlyName().equals(selectedCrate.getManifestType())
    ).findFirst();

    if (ret.isPresent())
      return ret.get();

    return null;
  }

  public List<Crate> getCratesOfType(MT type) {
//    MT manifestType = ManifestTypeCache.tryResolveManifest(type);
//    System.out.println("CENK WATCH OUT!");
//    System.out.println("incoming type: " + type.getFriendlyName());
//    System.out.println("crates length: " + crates.size());
    return crates.values().stream().filter(crate -> type == crate.getCrateManifestType()).collect(Collectors.toList());
    //      System.out.println(crate.getCrateManifestType());
//        System.out.println("HEHEHE");

//    return cratesToReturn;
//    return crates.values().stream().filter(c -> c.getCrateManifestType() .equals(type)).collect(Collectors.toList());
//    if (manifestType != null) {
//      return crates.values().stream().filter(c -> c.getCrateManifestType()
//          .equals(manifestType)).collect(Collectors.toList());
//    }

//    return null;
  }

  public MT getManifestType(MT type) {
    return ManifestTypeCache.tryResolveManifest(type);
  }

  public int remove(MT manifest) {

    if (manifest!= null) {
      crates.values().removeIf(c -> c.getCrateManifestType().equals(manifest));
    }

    return 0;
  }


  public Crate getCrateByKey(String key) {
    Crate ret = null;

    if (crates != null) {
      ret = crates.get(key);
    }

    return ret;
  }

  public void setCrateForKey(String key, Crate crate) {
    initializeMap();
    crates.put(key, crate);
  }

  public void addCrate(Crate crate) {
    initializeMap();
    add(crate);
  }

  public void clear() {
    initializeMap();
  }

  @Override
  public void remove(String key) {
    if (CollectionUtils.isNotEmpty(crates))
      crates.remove(key);
  }

  public int removeCrates(Predicate<Crate> predicate) {
    int affectedItems = 0;

    for(String key : crates.keySet()) {
      if(predicate.test(crates.get(key))) {
        affectedItems++;
        crates.remove(key);
      }
    }

    return affectedItems;
  }

  public int replaceCrate(Predicate<Crate> predicate, Crate crate) {
    int affectedItems = 0;

    for(String key : crates.keySet()) {
      if (predicate.test(crates.get(key))) {
        affectedItems++;
        crates.replace(key, crate);
      }
    }

    return affectedItems;
  }

  public Iterator<Crate> getIterator() {
    initializeMap();
    return crates.values().iterator();
  }


  private void initializeMap() {
    if (crates == null) {
      crates = new TreeMap<>();
    }
  }

  public void add(Crate crate) {
    // TODO: make sure very crate has an ID
    Logger.debug("Adding crate: " + crate);
    crates.put(crate.getId(), crate);
  }

  @Override
  public Iterator<Crate> iterator() {
    return crates.values().iterator();
  }

  @Override
  public void forEach(Consumer<? super Crate> action) {

  }

  @Override
  public Spliterator<Crate> spliterator() {
    return null;
  }

  @Override
  public void replace(AbstractCrateStorage storage) {
    this.crates = storage.getCratesMap();
  }

  @Override
  public Crate getFirstCrate() {
    if (CollectionUtils.isNotEmpty(crates))
      return ((TreeMap<String, Crate>)crates).firstEntry().getValue();

    Logger.warn("getFirstCrate: The map is empty, returning null");

    return null;
  }

  @Override
  public int replaceByLabel(Crate crate) {
    int ret = 0;
    if (crate != null && CollectionUtils.isNotEmpty(crates)) {
      for(Map.Entry<String, Crate> entry : crates.entrySet()) {
        if (entry.getValue().getLabel().equalsIgnoreCase(crate.getLabel())) {
          entry.setValue(crate);
          ret++;
        }
      }
    }

    return ret;
  }

  @Override
  public int getCount() {
    return crates.size();
  }

  @Override
  public void add(Crate[] crateArray) {
    if (CollectionUtils.isNotEmpty(crateArray)) {
      initializeMap();
      for (Crate crate : crateArray)
        add(crate);
    }
  }

  @Override
  public Map<String, Crate> getCratesMap() {
    initializeMap();
    return crates;
  }

  @Override
  public <T extends Manifest> void remove(Class<T> manifestType) {
    for (Map.Entry<String, Crate> entry : crates.entrySet()) {
      if (entry.getValue().getCrateManifestType().getFriendlyName().equalsIgnoreCase(manifestType.getName()))
        crates.remove(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public String findField(String key) {
    return null;
  }

  @Override
  public String toString() {
    final StringBuilder cratesSB = new StringBuilder();
    crates.forEach((k,v)-> {
      cratesSB.append("Crate with key: " + k).append(", has Crate: ").append(v.toString());
    });
    return "AbstractCrateStorage{" +
        "crates= " + cratesSB.toString() +
        '}';
  }
}
