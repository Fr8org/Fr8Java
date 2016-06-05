package co.fr8.terminal.base;

import co.fr8.data.interfaces.dto.AuthorizationTokenDTO;
import co.fr8.terminal.infrastructure.IHubCommunicator;

/**
 * TODO: DOCUMENT
 */
public class ActivityContext {
  private ActivityPayload activityPayload;
  private AuthorizationTokenDTO authorizationToken;
  private String userId;
  private IHubCommunicator hubCommunicator;

  public ActivityContext() {
  }

  public ActivityContext(ActivityPayload activityPayload, AuthorizationTokenDTO authorizationToken) {
    this.activityPayload = activityPayload;
    this.authorizationToken = authorizationToken;
  }

  public ActivityPayload getActivityPayload() {
    return activityPayload;
  }

  public void setActivityPayload(ActivityPayload activityPayload) {
    this.activityPayload = activityPayload;
  }

  public AuthorizationTokenDTO getAuthorizationToken() {
    return authorizationToken;
  }

  public void setAuthorizationToken(AuthorizationTokenDTO authorizationToken) {
    this.authorizationToken = authorizationToken;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public IHubCommunicator getHubCommunicator() {
    return hubCommunicator;
  }

  public void setHubCommunicator(IHubCommunicator hubCommunicator) {
    this.hubCommunicator = hubCommunicator;
  }
}
