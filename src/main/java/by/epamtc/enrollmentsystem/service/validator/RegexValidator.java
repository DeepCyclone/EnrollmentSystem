package by.epamtc.enrollmentsystem.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidator{

    public static boolean validateFieldWithRegex(String field,String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(field);
        return matcher.matches();
    }

}
