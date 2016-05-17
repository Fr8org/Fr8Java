package co.fr8.data.interfaces.dto;

/**
 * Data Transfer Object for the External Authorization object
 */
public class ExternalAuthDTO {

  private final String requestQueryString;
  private final String fr8UserId;

  /**
   * Constructor
   * @param requestQueryString the query string for the auth
   * @param fr8UserId the user ID for the auth
   */
  public ExternalAuthDTO(String requestQueryString, String fr8UserId) {
    this.requestQueryString = requestQueryString;
    this.fr8UserId = fr8UserId;
  }

  /**
   * Getter for the requestQueryString property
   * @return a String
   */
  public String getRequestQueryString() {
    return requestQueryString;
  }

  /**
   * Getter for the fr8UserId property
   * @return a String
   */
  public String getFr8UserId() {
    return fr8UserId;
  }
}
