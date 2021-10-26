package by.epamtc.enrollmentsystem.service.template;

import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.model.dto.UserCredential;
import by.epamtc.enrollmentsystem.model.dto.UserStyledToAdminPanel;

import java.util.List;
import java.util.Optional;

public interface UserService extends Identifiable<User>{
    Optional<User> getByLogin(String login) throws ServiceException;
    UserCredential getCredentials(String login) throws ServiceException;
    List<UserStyledToAdminPanel> getStyledUserInfo(int from,int offset) throws ServiceException;
    boolean isValidData(String login,String email,String password);
    int getNumberOfRecords() throws ServiceException;
    void insertInto(User data) throws ServiceException;
}
