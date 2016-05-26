package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.controls.CrateDetails;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO Implementation for the UpstreamCrateChooser UI control
 */
public class UpstreamCrateChooser extends ControlDefinitionDTO {

  private List<CrateDetails> selectedCrates = new ArrayList<>();
  private boolean multiSelection;

  public UpstreamCrateChooser() {
    super(ControlTypeEnum.UPSTREAM_CRATE_CHOOSER);
  }

  public List<CrateDetails> getSelectedCrates() {
    return selectedCrates;
  }

  public void setSelectedCrates(List<CrateDetails> selectedCrates) {
    this.selectedCrates = selectedCrates;
  }

  public boolean isMultiSelection() {
    return multiSelection;
  }

  public void setMultiSelection(boolean multiSelection) {
    this.multiSelection = multiSelection;
  }
}
