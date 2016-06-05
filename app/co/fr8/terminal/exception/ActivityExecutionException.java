package co.fr8.terminal.exception;

import co.fr8.terminal.base.ActivityErrorCode;
import co.fr8.terminal.base.exception.ActivityErrorCodeException;

/**
 * TODO: DOCUMENT
 */
public class ActivityExecutionException extends ActivityErrorCodeException {

  private final ActivityErrorCode activityErrorCode;

  public ActivityExecutionException(String message, ActivityErrorCode activityErrorCode) {
    // TODO: verify the ActivityErrorCode type
    super(message, ActivityErrorCode.DESIGN_TIME_DATA_MISSING);
    this.activityErrorCode = activityErrorCode;
  }

  public ActivityErrorCode getActivityErrorCode() {
    return activityErrorCode;
  }
}
