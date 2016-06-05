package co.fr8.data.interfaces.dto;

/**
 * TODO: Document
 */
public class SolutionPageDTO {

  public String name;
  public double version;
  //TODO: To be changed with another type
  public String terminal;
  //This field is to hold an HTML
  public String body;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getVersion() {
    return version;
  }

  public void setVersion(double version) {
    this.version = version;
  }

  public String getTerminal() {
    return terminal;
  }

  public void setTerminal(String terminal) {
    this.terminal = terminal;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
