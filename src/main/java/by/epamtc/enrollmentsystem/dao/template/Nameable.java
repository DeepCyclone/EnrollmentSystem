package by.epamtc.enrollmentsystem.dao.template;

import by.epamtc.enrollmentsystem.exception.DAOException;

import java.util.Optional;

public interface Nameable<T> {
    Optional<T> getByName(String name) throws DAOException;
}
