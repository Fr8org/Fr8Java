package co.fr8.data.interfaces.dto;

/**
 * TODO: Implement
 */
public class ExternalAuthUrlDTO {
  private final String url;
  private final String externalStateToken;

  public ExternalAuthUrlDTO(String url, String externalStateToken) {
    this.url = url;
    this.externalStateToken = externalStateToken;
  }

  public String getUrl() {
    return url;
  }

  public String getExternalStateToken() {
    return externalStateToken;
  }
}
