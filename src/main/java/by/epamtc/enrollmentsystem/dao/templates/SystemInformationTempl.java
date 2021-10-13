package by.epamtc.enrollmentsystem.dao.templates;

import by.epamtc.enrollmentsystem.dao.DAOTemplate;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.SystemInformation;

public interface SystemInformationTempl {
    SystemInformation getValueByName(String name) throws DAOException;
}
