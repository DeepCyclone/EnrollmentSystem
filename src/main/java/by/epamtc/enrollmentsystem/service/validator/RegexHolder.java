package by.epamtc.enrollmentsystem.service.validator;

public final class RegexHolder {
    private RegexHolder(){}

    public static final String PASSWORD_REGEX = "[\\w.-]{4,10}";
    public static final String USERNAME_REGEX = "[\\w.-]{3,15}";
    public static final String EMAIL_REGEX = "([\\w.-]+)@([a-zA-Z]+)([\\.])([a-zA-Z]{2,6})";
    public static final String TESTING_MARK_REGEX = "^$|[7-9]|[1-9][\\d]|100";
    public static final String CERTIFICATE_MARK_REGEX = "^$|[4-9]|10";
}
