package by.epamtc.enrollmentsystem.service.template;

import by.epamtc.enrollmentsystem.exception.ServiceException;

import java.util.Optional;

public interface Nameable<T> {
    Optional<T> getByName(String name) throws ServiceException;
}
