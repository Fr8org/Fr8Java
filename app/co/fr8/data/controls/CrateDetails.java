package co.fr8.data.controls;

import co.fr8.data.controls.impl.DropDownList;

/**
 * Contains two DropDownList objects which outline the details for a Crate object
 */
public class CrateDetails {

  private DropDownList manifestType;
  private DropDownList label;

  public DropDownList getManifestType() {
    return manifestType;
  }

  public void setManifestType(DropDownList manifestType) {
    this.manifestType = manifestType;
  }

  public DropDownList getLabel() {
    return label;
  }

  public void setLabel(DropDownList label) {
    this.label = label;
  }
}
