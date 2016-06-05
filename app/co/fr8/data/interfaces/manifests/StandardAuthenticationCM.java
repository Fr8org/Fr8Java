package co.fr8.data.interfaces.manifests;

import co.fr8.data.constants.MT;

/**
 * TODO: Document
 */
public class StandardAuthenticationCM extends Manifest {

  private AuthenticationMode mode;
  private boolean revocation;

  public StandardAuthenticationCM() {
    super(MT.StandardAuthentication);
  }

  public AuthenticationMode getMode() {
    return mode;
  }

  public StandardAuthenticationCM(AuthenticationMode mode, boolean revocation) {
    super(MT.StandardAuthentication);
    this.mode = mode;
    this.revocation = revocation;
  }

  public void setMode(AuthenticationMode mode) {
    this.mode = mode;
  }

  public boolean isRevocation() {
    return revocation;
  }

  public void setRevocation(boolean revocation) {
    this.revocation = revocation;
  }
}
