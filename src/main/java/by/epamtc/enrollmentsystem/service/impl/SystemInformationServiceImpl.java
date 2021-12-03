package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.template.SystemInformationDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.SystemInformation;
import by.epamtc.enrollmentsystem.service.template.SystemInformationService;

import java.util.Optional;
import java.util.concurrent.locks.Lock;

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
    public void deleteRowByID(long id) throws ServiceException {

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
