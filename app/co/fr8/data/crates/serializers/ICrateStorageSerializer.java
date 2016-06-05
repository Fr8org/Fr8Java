package co.fr8.data.crates.serializers;

import co.fr8.data.crates.AbstractCrateStorage;
import co.fr8.data.crates.Crate;
import co.fr8.data.interfaces.dto.CrateDTO;
import co.fr8.data.interfaces.dto.CrateStorageDTO;

/**
 * Defines method for an object that serializes CrateStorage objects
 */
public interface ICrateStorageSerializer {

  CrateStorageDTO convertToDto(AbstractCrateStorage storage);
  CrateDTO convertToDto(Crate crate);
  AbstractCrateStorage convertFromDto(CrateStorageDTO dto);
  Crate convertFromDto(CrateDTO dto);

}
