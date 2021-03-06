package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.template.FacilityDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Facility;
import by.epamtc.enrollmentsystem.service.template.FacilityService;

import java.util.Optional;

public class FacilityServiceImpl implements FacilityService {
    @Override
    public Optional<Facility> getByID(long id) throws ServiceException {
        try {
            FacilityDAO dao = DAOProvider.getInstance().getFacilityDAO();
            return dao.getByID(id);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }

    @Override
    public void updateRowByID(Facility note) throws ServiceException {
        try {
            FacilityDAO dao = DAOProvider.getInstance().getFacilityDAO();
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
    public Optional<Facility> getByName(String name) throws ServiceException {
        try {
            FacilityDAO dao = DAOProvider.getInstance().getFacilityDAO();
            return dao.getByName(name);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }
}
