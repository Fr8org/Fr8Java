package co.fr8.data.interfaces.dto;

import java.util.UUID;

/**
 * TODO: Implement
 */
public class TerminalDTO {

  private String id;
  private String name;
  private String label;
  private String version;
  private int terminalStatus;
  private String endpoint;
  private String description;
  private int authenticationType;

  public TerminalDTO() {

  }

  public TerminalDTO(String name, String label, String version, int terminalStatus, String endpoint,
                     String description, int authenticationType) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.label = label;
    this.version = version;
    this.terminalStatus = terminalStatus;
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

  @Override
  public String toString() {
    return "TerminalDTO{" +
        "name='" + name + '\'' +
        ", label='" + label + '\'' +
        ", version='" + version + '\'' +
        ", terminalStatus=" + terminalStatus +
        ", endpoint='" + endpoint + '\'' +
        ", description='" + description + '\'' +
        ", authenticationType=" + authenticationType +
        '}';
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
