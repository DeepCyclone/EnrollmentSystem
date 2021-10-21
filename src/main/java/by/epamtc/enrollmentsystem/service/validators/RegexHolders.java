package by.epamtc.enrollmentsystem.service.validators;

public final class RegexHolders {
    private RegexHolders(){}

    public static final String PASSWORD_REGEX = "[a-zA-Z0-9_.-]{4,10}";
    public static final String USERNAME_REGEX = "[a-zA-Z0-9_.-]{3,15}";
    public static final String EMAIL_REGEX = "([a-zA-Z0-9_.-]+)@([a-zA-Z]+)([\\.])([a-zA-Z]+)";
}
