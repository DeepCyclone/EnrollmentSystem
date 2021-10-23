package by.epamtc.enrollmentsystem.service.templates;

import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;

import java.util.Optional;

public interface Nameable<T> {
    Optional<T> getByName(String name) throws ServiceException;
}
