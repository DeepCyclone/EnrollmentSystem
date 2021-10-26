package by.epamtc.enrollmentsystem.service.validator;

import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.dto.UserCredential;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.UserService;
import by.epamtc.enrollmentsystem.service.util.PasswordCodec;

public class CredentialsValidator {
    public static boolean isCorrectCredentials(String login, String password) throws ServiceException {
        UserService userService = ServiceProvider.getInstance().getUserService();
        boolean result = false;
        UserCredential userCredential = userService.getCredentials(login);
        if (userCredential != null) {
            String hashedPassword = userCredential.getPassword();
            if(PasswordCodec.validatePassword(hashedPassword, password)){
                result = true;
            }
        }
        return result;
    }
}
