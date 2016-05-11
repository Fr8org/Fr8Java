package co.fr8.data.states;

/**
 * Enum representation of AuthenticationType objects
 */
public enum AuthenticationTypeEnum {
  NONE(1),
  INTERNAL(2),
  EXTERNAL(3),
  INTERNALWITHDOMAIN(4);

  private int code;

  AuthenticationTypeEnum(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  /*
  public const int None = 1;
  public const int Internal = 2;
  public const int External = 3;
  public const int InternalWithDomain = 4;
  */
}
