package co.fr8.terminal.base.exception;

import co.fr8.terminal.base.ActivityErrorCode;

/**
 * TODO: DOCUMENT
 */
public class AuthorizationTokenExpiredOrInvalidException extends ActivityErrorCodeException {

  public AuthorizationTokenExpiredOrInvalidException(String message) {
    super(message, ActivityErrorCode.AUTH_TOKEN_NOT_PROVIDED_OR_INVALID);
  }
}
