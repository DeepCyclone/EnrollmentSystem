package by.epamtc.enrollmentsystem.dao.templates;

import by.epamtc.enrollmentsystem.dao.DAOTemplate;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.model.dto.UserCredentials;

public interface UserDAO extends DAOTemplate<User> {
    int getIdByLogin(String login) throws DAOException;
    UserCredentials getCredentials(String login) throws DAOException;
    int getRoleByLogin(String login) throws DAOException;
}