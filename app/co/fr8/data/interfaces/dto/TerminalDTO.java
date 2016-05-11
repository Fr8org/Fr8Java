package co.fr8.data.interfaces.dto;

/**
 * TODO: Implement
 */
public class TerminalDTO {

  private final String name;
  private final String label;
  private final String version;
  private int terminalStatus;
  private final String endpoint;
  private final String description;

  // TODO: Should this be enum type?
  private final int authenticationType;

  public TerminalDTO(String name, String label, String version, String endpoint,
                     String description, int authenticationType) {
    this.name = name;
    this.label = label;
    this.version = version;
    this.endpoint = endpoint;
    this.description = description;
    this.authenticationType = authenticationType;
  }

  public String getName() {
    return name;
  }

  public String getLabel() {
    return label;
  }

  public String getVersion() {
    return version;
  }

  public int getTerminalStatus() {
    return terminalStatus;
  }

  public void setTerminalStatus(int terminalStatus) {
    this.terminalStatus = terminalStatus;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public String getDescription() {
    return description;
  }

  public int getAuthenticationType() {
    return authenticationType;
  }
}
