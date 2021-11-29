package by.epamtc.enrollmentsystem.service.template;

import by.epamtc.enrollmentsystem.exception.ServiceException;

import java.util.Optional;
/*
 * A basic interface, marks and objects, that has Name field
 * Contains templates for operations with nameable objects
 */
public interface Nameable<T> {
    Optional<T> getByName(String name) throws ServiceException;
}
