package by.epamtc.enrollmentsystem.dao.template;

import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;

import java.util.Optional;

public interface Identifiable<T> {
    Optional<T> getByID(long id) throws DAOException;
    void updateRowByID(T note) throws DAOException;
    void deleteRowByID(long id) throws DAOException;
}
