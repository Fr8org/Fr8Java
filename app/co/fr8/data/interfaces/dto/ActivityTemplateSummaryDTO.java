package co.fr8.data.interfaces.dto;

public class ActivityTemplateSummaryDTO {
  public String name;
  public String version;
  public String terminalName;
  public String terminalVersion;

  public ActivityTemplateSummaryDTO() {
  }

  public ActivityTemplateSummaryDTO(String name, String version, String terminalName, String terminalVersion) {
    this.name = name;
    this.version = version;
    this.terminalName = terminalName;
    this.terminalVersion = terminalVersion;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getTerminalName() {
    return terminalName;
  }

  public void setTerminalName(String terminalName) {
    this.terminalName = terminalName;
  }

  public String getTerminalVersion() {
    return terminalVersion;
  }

  public void setTerminalVersion(String terminalVersion) {
    this.terminalVersion = terminalVersion;
  }

  @Override
  public String toString() {
    return "ActivityTemplateSummaryDTO{" +
        "name='" + name + '\'' +
        ", version='" + version + '\'' +
        ", terminalName='" + terminalName + '\'' +
        ", terminalVersion='" + terminalVersion + '\'' +
        '}';
  }
}
