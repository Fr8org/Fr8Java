package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

/**
 * DTO Implementation for UpstreamDataChooser UI Control
 */
public class UpstreamDataChooser extends ControlDefinitionDTO {

  private String selectedManifest;
  private String selectedLabel;
  private String selectedFieldType;

  public UpstreamDataChooser() {
    super(ControlTypeEnum.UPSTREAM_DATA_CHOOSER);
  }

  public String getSelectedManifest() {
    return selectedManifest;
  }

  public void setSelectedManifest(String selectedManifest) {
    this.selectedManifest = selectedManifest;
  }

  public String getSelectedLabel() {
    return selectedLabel;
  }

  public void setSelectedLabel(String selectedLabel) {
    this.selectedLabel = selectedLabel;
  }

  public String getSelectedFieldType() {
    return selectedFieldType;
  }

  public void setSelectedFieldType(String selectedFieldType) {
    this.selectedFieldType = selectedFieldType;
  }
}
