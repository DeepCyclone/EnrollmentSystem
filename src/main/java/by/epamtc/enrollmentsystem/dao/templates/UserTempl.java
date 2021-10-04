package by.epamtc.enrollmentsystem.dao.templates;

import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.User;

public interface UserTempl extends DAOTemplate<User> {
    int getIdByLogin(String login) throws DAOException;
}
