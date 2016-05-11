package co.fr8.data.crates.serializers;

import co.fr8.data.crates.Crate;
import co.fr8.data.crates.ICrateStorage;
import co.fr8.data.interfaces.dto.CrateDTO;
import co.fr8.data.interfaces.dto.CrateStorageDTO;

/**
 * Defines method for an object that serializes CrateStorage objects
 */
public interface ICrateStorageSerializer {

  CrateStorageDTO convertToDto(ICrateStorage storage);
  CrateDTO convertToDto(Crate crate);
  ICrateStorage convertFromDto(CrateStorageDTO dto);
  Crate convertFromDto(CrateDTO dto);

}
