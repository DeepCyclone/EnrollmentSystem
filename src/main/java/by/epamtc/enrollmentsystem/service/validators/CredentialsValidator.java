package by.epamtc.enrollmentsystem.service.validators;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.UserMySQL;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.dto.UserCredentials;
import by.epamtc.enrollmentsystem.utils.PasswordCodec;

public class CredentialsValidator {
    public static boolean isCorrectCredentials(String login, String password) throws ServiceException {
        UserMySQL userMySQL = DAOProvider.getInstance().getUserDAO();
        boolean result = false;
        try {
            UserCredentials userCredentials = userMySQL.getCredentials(login);
            if (userCredentials != null) {
                String hashedPassword = userCredentials.getPassword();
                if(PasswordCodec.validatePassword(hashedPassword, password)){
                    result = true;
                }
            }
        }
        catch (DAOException e) {//TODO SQLException

        }
        return result;
    }
}
