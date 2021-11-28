package by.epamtc.enrollmentsystem.service.template;

import by.epamtc.enrollmentsystem.exception.ServiceException;

import java.util.Optional;

public interface Identifiable<T> {
    Optional<T> getByID(long id) throws ServiceException;
    void updateRowByID(T note) throws ServiceException;
    void deleteRowByID(long id) throws ServiceException;
}
