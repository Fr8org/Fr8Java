package co.fr8.terminal.base.exception;

import co.fr8.terminal.base.ActivityErrorCode;

/**
 * TODO: Document
 */
abstract public class ActivityErrorCodeException extends Exception {

  private final ActivityErrorCode activityErrorCode;

  public ActivityErrorCodeException(ActivityErrorCode activityErrorCode) {
    this.activityErrorCode = activityErrorCode;
  }

  public ActivityErrorCodeException(String message, ActivityErrorCode activityErrorCode) {
    super(message);
    this.activityErrorCode = activityErrorCode;
  }

  public ActivityErrorCode getActivityErrorCode() {
    return activityErrorCode;
  }
}
