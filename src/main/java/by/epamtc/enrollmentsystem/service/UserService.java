package by.epamtc.enrollmentsystem.service;

import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.dto.UserCredentials;
import by.epamtc.enrollmentsystem.model.dto.UserStyledToAdminPanel;

import java.util.List;

public interface UserService {
    int getIdByLogin(String login) throws ServiceException;
    UserCredentials getCredentials(String login) throws ServiceException;
    int getRoleByLogin(String login) throws ServiceException;
    List<UserStyledToAdminPanel> getStyledUserInfo() throws ServiceException;
}
