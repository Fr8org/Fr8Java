package co.fr8.data.crates;

import co.fr8.data.interfaces.dto.CrateDescriptionDTO;

import java.util.Map;
import java.util.Optional;

/**
 * TODO: Implement
 */
abstract public class ICrateStorage implements Iterable<Crate> {

  protected Map<String, Crate> crateMap;

  public Crate getFirstCrateOrDefault(CrateDescriptionDTO selectedCrate) {
    Optional<Crate> ret = crateMap.values().stream().filter(
        c -> c.getLabel().equalsIgnoreCase(selectedCrate.getLabel()) &&
            c.getCrateManifestType().getType().equals(selectedCrate.getManifestType())
    ).findFirst();

    if (ret.isPresent())
      return ret.get();

    return null;
  }

  abstract public String findField(String key);
}
