package co.fr8.data.interfaces.dto;

import co.fr8.terminal.base.ErrorType;

/**
 * TODO: Document
 */
public class ResponseMessageDTO {

  private String message;
  private String errorCode;
  private String type;
  private Object details;
  private String currentActivity;
  private String currentTerminal;

  public ResponseMessageDTO() {
  }
  public ResponseMessageDTO(String type) {
    this.type = type;
  }

  public ResponseMessageDTO(String message, String errorCode, String type, Object details) {
    this.message = message;
    this.errorCode = errorCode;
    this.type = type;
    this.details = details;
  }

  public ResponseMessageDTO(String message, String errorCode, String type, Object details,
                            String currentActivity, String currentTerminal) {
    this.message = message;
    this.errorCode = errorCode;
    this.type = type;
    this.details = details;
    this.currentActivity = currentActivity;
    this.currentTerminal = currentTerminal;
  }

  protected static String errorTypeToString(ErrorType errorType) {
    switch(errorType) {
      case Generic:
        return "int";
      case Authentication:
        return "auth";
      case Critical:
        return "critical";
      default:
        throw new IllegalArgumentException(("Unknown error type" + errorType));
    }
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Object getDetails() {
    return details;
  }

  public void setDetails(Object details) {
    this.details = details;
  }

  public String getCurrentActivity() {
    return currentActivity;
  }

  public void setCurrentActivity(String currentActivity) {
    this.currentActivity = currentActivity;
  }

  public String getCurrentTerminal() {
    return currentTerminal;
  }

  public void setCurrentTerminal(String currentTerminal) {
    this.currentTerminal = currentTerminal;
  }
}
