package by.epamtc.enrollmentsystem.dao.interfaces;

import by.epamtc.enrollmentsystem.exception.DAOException;

import java.util.Optional;

public interface Identifiable<T> {
    Optional<T> getByID(long id) throws DAOException;
    void updateRowByID(T note) throws DAOException;
}
