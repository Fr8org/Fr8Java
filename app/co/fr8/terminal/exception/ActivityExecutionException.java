package co.fr8.terminal.exception;

import co.fr8.terminal.base.ActivityErrorCode;
import co.fr8.terminal.base.exception.ActivityErrorCodeException;

/**
 * TODO: DOCUMENT
 */
public class ActivityExecutionException extends ActivityErrorCodeException {

  public ActivityExecutionException(String message, ActivityErrorCode activityErrorCode) {
    // TODO: verify the ActivityErrorCode type
    super(message, activityErrorCode);
  }
}
