package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.crates.Crate;
import co.fr8.data.crates.AbstractCrateStorage;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;
import co.fr8.data.interfaces.dto.CrateDescriptionDTO;
import co.fr8.util.CollectionUtils;
import co.fr8.util.logging.Logger;

import java.util.List;
import java.util.Optional;

/**
 * DTO Implementation for a CrateChooser control
 */
public class CrateChooser extends ControlDefinitionDTO {

  private static boolean isSelected(CrateDescriptionDTO c) {
    return c.isSelected();
  }

  private List<CrateDescriptionDTO> crateDescriptions;
  private boolean singleManifestOnly = false;
  private boolean requestUpstream = false;

  public CrateChooser() {
    super(ControlTypeEnum.CRATE_CHOOSER);
  }

  public boolean hasValue() {
    return CollectionUtils.isNotEmpty(crateDescriptions) &&
        crateDescriptions.stream().filter(CrateChooser::isSelected).findAny().isPresent();
  }

  public boolean canGetValue(AbstractCrateStorage crateStorage) {
    return crateStorage != null;
  }

  public Crate getValue(AbstractCrateStorage crateStorage) {
    if (CollectionUtils.isEmpty(crateDescriptions))
      return null;

    Optional<CrateDescriptionDTO> ret =
        crateDescriptions.stream().filter(CrateChooser::isSelected).findFirst();

    if (ret.isPresent()) {
//      CrateDescriptionDTO selectedCrate = ;
      return crateStorage.getFirstCrateOrDefault(ret.get());
    } else {
      return null;
    }
  }

  /**
   * @deprecated in favor of lambda expression and method reference
   * @return true if the any of the CrateDescriptionDTO in crateDescriptions
   *         has a selected property which is set to true
   */
  private boolean hasSelectedValue() {
    for (CrateDescriptionDTO crateDescription : crateDescriptions)
      if (crateDescription.isSelected()) {
        Logger.debug("Checking whether crateDescription " + crateDescription.getLabel() +
            " is selected " + crateDescription.isSelected() );
        return true;
      }

    Logger.debug("There are no selected crateDescriptions");

    return false;
  }
}
