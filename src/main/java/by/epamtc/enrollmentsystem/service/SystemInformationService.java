package by.epamtc.enrollmentsystem.service;

import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.SystemInformation;

public interface SystemInformationService {
    SystemInformation getValueByName(String name) throws ServiceException;
}
