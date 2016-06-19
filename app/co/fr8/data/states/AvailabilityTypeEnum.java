package co.fr8.data.states;

/**
 * Enum that represents the AvailabilityType
 */
public enum AvailabilityTypeEnum {
    NotSet(0),
    Configuration(0x1),
    RunTime(0x2),
    Always(Configuration.code | RunTime.code);

    private final int code;

    AvailabilityTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static AvailabilityTypeEnum getByCode(int code) {
        for (AvailabilityTypeEnum value : values()) {
            if (code == value.getCode())
                return value;
        }

        return null;
    }
}
