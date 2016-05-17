package co.fr8.data.interfaces.dto;

/**
 * TODO: Implement
 */
public class CrateStorageDTO {

  private CrateDTO[] crates;

  public CrateStorageDTO() {
  }

  public CrateDTO[] getCrates() {
    return crates;
  }

  public void setCrates(CrateDTO[] crates) {
    this.crates = crates;
  }
}
