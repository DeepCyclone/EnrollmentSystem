package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.templates.FacultyDAO;
import by.epamtc.enrollmentsystem.dao.templates.SystemInformationDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.SystemInformation;
import by.epamtc.enrollmentsystem.service.SystemInformationService;

public class SystemInformationServiceImpl implements SystemInformationService {
    @Override
    public SystemInformation getValueByName(String name) throws ServiceException {
        try {
            SystemInformationDAO dao = DAOProvider.getInstance().getSystemInformationDAO();
            return dao.getValueByName(name);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }
}
