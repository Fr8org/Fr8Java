package co.fr8.data.interfaces.dto;

/**
 * TODO: DOCUMENT
 */
public class ErrorDTO extends ResponseMessageDTO {

  public ErrorDTO() {
  }

  public ErrorDTO(String type) {
    super(type);
  }

  public ErrorDTO(String message, String errorCode, String type, Object details) {
    super(message, errorCode, type, details);
  }

  public ErrorDTO(String message, String errorCode, String type, Object details,
                  String currentActivity, String currentTerminal) {
    super(message, errorCode, type, details, currentActivity, currentTerminal);
  }
}
