package by.epamtc.enrollmentsystem.service;

import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.SystemInformation;

import java.util.Optional;

public interface SystemInformationService {
    Optional<SystemInformation> getValueByName(String name) throws ServiceException;
}
