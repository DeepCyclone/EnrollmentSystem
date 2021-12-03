package by.epamtc.enrollmentsystem.service.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class NumberValidator {
    public static final Pattern PATTERN = Pattern.compile("^\\d+$");

    public static boolean isInteger(String number){

        return number != null && PATTERN.matcher(number).matches();
    }
}
