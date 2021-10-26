package by.epamtc.enrollmentsystem.dao.template;

import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.User;

import java.util.Optional;

public interface UserDAO extends DAOTemplate<User>, Identifiable<User> {
    Optional<User> getByLogin(String login) throws DAOException;
}
