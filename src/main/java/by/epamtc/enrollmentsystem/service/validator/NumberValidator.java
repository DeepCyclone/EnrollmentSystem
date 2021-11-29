package by.epamtc.enrollmentsystem.service.validator;

public class NumberValidator {
    public static boolean isInteger(String number){
        boolean isNumber = true;
        for(int i = 0;i<number.length();++i){
            isNumber = Character.isDigit(number.charAt(i));
            if(!isNumber){
                break;
            }
        }
        return isNumber;
    }
}
