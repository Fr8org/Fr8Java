package co.fr8.data.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DataTransactionObject for Authorization Token
 */
public class AuthorizationTokenDTO {

  @JsonProperty("Id")
  private String id;

  @JsonProperty("Token")
  private String token;

  @JsonProperty("ExternalAccountId")
  private String externalAccountId;

  @JsonProperty("ExternalAccountName")
  private String externalAccountName;

  @JsonProperty("ExternalDomainId")
  private String externalDomainId;

  @JsonProperty("ExternalDomainName")
  private String externalDomainName;

  @JsonProperty("UserId")
  private String userId;

  @JsonProperty("ExternalStateToken")
  private String externalStateToken;

  @JsonProperty("AdditionalAttributes")
  private String additionalAttributes;

  @JsonProperty("Error")
  private String error;

  @JsonProperty("AuthCompletedNotificationRequired")
  private boolean authCompleteNotificationRequired;

  @JsonProperty("TerminalID")
  private String terminalId;

  public AuthorizationTokenDTO() {

  }

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

  public String getExternalAccountName() {
    return externalAccountName;
  }

  public void setExternalAccountName(String externalAccountName) {
    this.externalAccountName = externalAccountName;
  }

  public String getExternalDomainName() {
    return externalDomainName;
  }

  public void setExternalDomainName(String externalDomainName) {
    this.externalDomainName = externalDomainName;
  }

  public String getTerminalId() {
    return terminalId;
  }

  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
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
