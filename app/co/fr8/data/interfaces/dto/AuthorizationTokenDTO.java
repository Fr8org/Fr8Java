package co.fr8.data.interfaces.dto;

/**
 * DataTransactionObject for Authorization Token
 */
public class AuthorizationTokenDTO {

  private String id;
  private String token;
  private String externalAccountId;
  private String externalDomainId;
  private String userId;
  private String externalStateToken;
  private String additionalAttributes;
  private String error;
  private boolean authCompleteNotificationRequired;

  public AuthorizationTokenDTO(String id, String token, String externalAccountId,
                               String externalDomainId, String userId, String externalStateToken,
                               String additionalAttributes, String error, boolean authCompleteNotificationRequired) {
    this.id = id;
    this.token = token;
    this.externalAccountId = externalAccountId;
    this.externalDomainId = externalDomainId;
    this.userId = userId;
    this.externalStateToken = externalStateToken;
    this.additionalAttributes = additionalAttributes;
    this.error = error;
    this.authCompleteNotificationRequired = authCompleteNotificationRequired;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getExternalAccountId() {
    return externalAccountId;
  }

  public void setExternalAccountId(String externalAccountId) {
    this.externalAccountId = externalAccountId;
  }

  public String getExternalDomainId() {
    return externalDomainId;
  }

  public void setExternalDomainId(String externalDomainId) {
    this.externalDomainId = externalDomainId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getExternalStateToken() {
    return externalStateToken;
  }

  public void setExternalStateToken(String externalStateToken) {
    this.externalStateToken = externalStateToken;
  }

  public String getAdditionalAttributes() {
    return additionalAttributes;
  }

  public void setAdditionalAttributes(String additionalAttributes) {
    this.additionalAttributes = additionalAttributes;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public boolean isAuthCompleteNotificationRequired() {
    return authCompleteNotificationRequired;
  }

  public void setAuthCompleteNotificationRequired(boolean authCompleteNotificationRequired) {
    this.authCompleteNotificationRequired = authCompleteNotificationRequired;
  }

  /*
  public string Id { get; set; }
  public string Token { get; set; }
  public string ExternalAccountId { get; set; }

  public string ExternalDomainId { get; set; }
  public string UserId { get; set; }
  public string ExternalStateToken { get; set; }
  public string AdditionalAttributes { get; set; }
  public string Error { get; set; }
  public bool AuthCompletedNotificationRequired { get; set; }
*/

}
