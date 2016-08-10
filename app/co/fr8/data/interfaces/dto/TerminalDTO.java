package co.fr8.data.interfaces.dto;

import java.util.Arrays;

/**
 * TODO: Implement
 */
public class TerminalDTO {

  private String internalId;
  private String name;
  private String label;
  private String version;
  private int terminalStatus;
  private int participationState;
  private String endpoint;
  private String description;
  private int authenticationType;
  private String devUrl;
  private String prodUrl;
  private boolean isFr8OwnTerminal;
  private String[] roles;

  public TerminalDTO() {
  }

  public TerminalDTO(String name, String label, String version, int terminalStatus, String endpoint,
                     String description, int authenticationType) {
    this.internalId = "00000000-0000-0000-0000-000000000000";
    this.name = name;
    this.label = label;
    this.version = version;
    this.terminalStatus = terminalStatus;
    this.participationState = 0;
    this.endpoint = endpoint;
    this.description = description;
    this.authenticationType = authenticationType;
    this.devUrl = "";
    this.prodUrl = "";
    this.isFr8OwnTerminal = Boolean.FALSE;
    this.roles = new String[0];
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

  public String getInternalId() {
    return internalId;
  }

  public void setInternalId(String internalId) {
    this.internalId = internalId;
  }

  public int getParticipationState() {
    return participationState;
  }

  public void setParticipationState(int participationState) {
    this.participationState = participationState;
  }

  public String getDevUrl() {
    return devUrl;
  }

  public void setDevUrl(String devUrl) {
    this.devUrl = devUrl;
  }

  public String getProdUrl() {
    return prodUrl;
  }

  public void setProdUrl(String prodUrl) {
    this.prodUrl = prodUrl;
  }

  public boolean getIsFr8OwnTerminal() {
    return isFr8OwnTerminal;
  }

  public void setIsFr8OwnTerminal(boolean isFr8OwnTerminal) {
    this.isFr8OwnTerminal = isFr8OwnTerminal;
  }

  public String[] getRoles() {
    return roles;
  }

  public void setRoles(String[] roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return "TerminalDTO{" +
        "internalId='" + internalId + '\'' +
        ", name='" + name + '\'' +
        ", label='" + label + '\'' +
        ", version='" + version + '\'' +
        ", terminalStatus=" + terminalStatus +
        ", participationState=" + participationState +
        ", endpoint='" + endpoint + '\'' +
        ", description='" + description + '\'' +
        ", authenticationType=" + authenticationType +
        ", devUrl='" + devUrl + '\'' +
        ", prodUrl='" + prodUrl + '\'' +
        ", isFr8OwnTerminal=" + isFr8OwnTerminal +
        ", roles=" + Arrays.toString(roles) +
        '}';
  }
}
