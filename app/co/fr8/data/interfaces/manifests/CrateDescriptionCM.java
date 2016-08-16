package co.fr8.data.interfaces.manifests;

import co.fr8.data.constants.MT;
import co.fr8.data.interfaces.dto.CrateDescriptionDTO;
import co.fr8.util.logging.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO: Document
 */
public class CrateDescriptionCM extends Manifest {

  private List<CrateDescriptionDTO> crateDescriptions = new ArrayList<>();

  public CrateDescriptionCM() {
    super(MT.CrateDescription);
  }

  public CrateDescriptionCM(List<CrateDescriptionDTO> crateDescriptions) {
    super(MT.CrateDescription);
    this.crateDescriptions = crateDescriptions;
  }

  public CrateDescriptionDTO addOrUpdate(CrateDescriptionDTO crateDescriptionDTO) {
    Optional<CrateDescriptionDTO> ret =
        crateDescriptions.stream().filter(cd ->
            cd.getLabel().equals(crateDescriptionDTO.getLabel())
                && cd.getManifestId() == crateDescriptionDTO.getManifestId()).findFirst();

    if (ret.isPresent())
      return ret.get();

    Logger.debug("Adding CrateDescription to CrateDescriptionCM");

    crateDescriptions.add(crateDescriptionDTO);

    return crateDescriptionDTO;
  }

  public List<CrateDescriptionDTO> getCrateDescriptions() {
    return crateDescriptions;
  }

  public void setCrateDescriptions(List<CrateDescriptionDTO> crateDescriptions) {
    this.crateDescriptions = crateDescriptions;
  }

  @Override
  public String toString() {
    return "CrateDescriptionCM{" +
        "crateDescriptions=" + crateDescriptions +
        '}';
  }
}
