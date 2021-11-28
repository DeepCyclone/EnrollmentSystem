package by.epamtc.enrollmentsystem.service.validator;

import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;

public class ResultValidator {
    public static boolean validateCertificate(String markName,String markValue){
        return RequestMapping.SCHOOL_CERTIFICATE.equals(markName) &&
                RegexValidator.validateFieldWithRegex(markValue, RegexHolder.CERTIFICATE_MARK_REGEX);
    }
    public static boolean validateTestingMark(String markName,String markValue){
        return !RequestMapping.SCHOOL_CERTIFICATE.equals(markName) &&
                RegexValidator.validateFieldWithRegex(markValue, RegexHolder.TESTING_MARK_REGEX);
    }
}
