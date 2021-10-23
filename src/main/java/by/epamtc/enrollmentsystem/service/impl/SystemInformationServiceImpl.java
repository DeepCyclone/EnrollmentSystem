package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.interfaces.SubjectDAO;
import by.epamtc.enrollmentsystem.dao.interfaces.SystemInformationDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Subject;
import by.epamtc.enrollmentsystem.model.SystemInformation;
import by.epamtc.enrollmentsystem.service.templates.SystemInformationService;

import java.util.Optional;

public class SystemInformationServiceImpl implements SystemInformationService {
    @Override
    public Optional<SystemInformation> getByID(long id) throws ServiceException {
        try {
            SystemInformationDAO dao = DAOProvider.getInstance().getSystemInformationDAO();
            return dao.getByID(id);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }

    @Override
    public void updateRowByID(SystemInformation note) throws ServiceException {
        try {
            SystemInformationDAO dao = DAOProvider.getInstance().getSystemInformationDAO();
            dao.updateRowByID(note);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }

    @Override
    public Optional<SystemInformation> getByName(String name) throws ServiceException {
        try {
            SystemInformationDAO dao = DAOProvider.getInstance().getSystemInformationDAO();
            return dao.getByName(name);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }
}
