package by.epamtc.enrollmentsystem.service.validators;

import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.dto.UserCredentials;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.templates.UserService;
import by.epamtc.enrollmentsystem.utils.PasswordCodec;

public class CredentialsValidator {
    public static boolean isCorrectCredentials(String login, String password) throws ServiceException {
        UserService userService = ServiceProvider.getInstance().getUserService();
        boolean result = false;
        UserCredentials userCredentials = userService.getCredentials(login);
        if (userCredentials != null) {
            String hashedPassword = userCredentials.getPassword();
            if(PasswordCodec.validatePassword(hashedPassword, password)){
                result = true;
            }
        }
        return result;
    }
}
