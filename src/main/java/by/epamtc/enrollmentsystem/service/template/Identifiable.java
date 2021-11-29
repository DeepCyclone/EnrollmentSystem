package by.epamtc.enrollmentsystem.service.template;

import by.epamtc.enrollmentsystem.exception.ServiceException;

import java.util.Optional;

/*
 * A basic interface, marks and objects, that has ID field
 * Contains templates for operations with identifiable objects
 */
public interface Identifiable<T> {
    Optional<T> getByID(long id) throws ServiceException;
    void updateRowByID(T note) throws ServiceException;
    void deleteRowByID(long id) throws ServiceException;
}
