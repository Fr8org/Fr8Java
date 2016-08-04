package co.fr8.data.interfaces.dto;

/**
 * TODO: Document
 */
public class AuthorizationTokenDTO {

  private String id;
  private String token;
  private String externalAccountId;
  private String externalAccountName;
  private String externalDomainId;
  private String externalDomainName;
  private String userId;
  private String externalStateToken;
  private String additionalAttributes;
  private String error;
  private String expiresAt;
  private boolean authCompletedNotificationRequired;
  /**
   * @deprecated property no longer in use and replaced by id
   */
  private int terminalId;

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

  public String getExternalAccountName() {
    return externalAccountName;
  }

  public void setExternalAccountName(String externalAccountName) {
    this.externalAccountName = externalAccountName;
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

  public boolean isAuthCompletedNotificationRequired() {
    return authCompletedNotificationRequired;
  }

  public void setAuthCompletedNotificationRequired(boolean authCompletedNotificationRequired) {
    this.authCompletedNotificationRequired = authCompletedNotificationRequired;
  }

  public int getTerminalId() {
    return terminalId;
  }

  public void setTerminalId(int terminalId) {
    this.terminalId = terminalId;
  }

  public String getExternalDomainName() {
    return externalDomainName;
  }

  public void setExternalDomainName(String externalDomainName) {
    this.externalDomainName = externalDomainName;
  }

  public String getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(String expiresAt) {
    this.expiresAt = expiresAt;
  }
}
