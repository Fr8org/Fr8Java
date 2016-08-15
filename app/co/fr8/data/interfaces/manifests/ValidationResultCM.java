package co.fr8.data.interfaces.manifests;

import co.fr8.data.constants.MT;
import co.fr8.data.crates.CrateManifestType;

public class ValidationResultCM extends Manifest {

  private String[] controlNames;
  private String errorMessage;

  public ValidationResultCM(CrateManifestType crateManifestType) {
    super(crateManifestType);
  }

  public ValidationResultCM(String errorMessage, String... controlNames) {
    super(MT.ValidationResults);
    setErrorMessage(errorMessage);
    setControlNames(controlNames);
  }

  public String[] getControlNames() {
    return controlNames;
  }

  public void setControlNames(String[] controlNames) {
    this.controlNames = controlNames;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

}
