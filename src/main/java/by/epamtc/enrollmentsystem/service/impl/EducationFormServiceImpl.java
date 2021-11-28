package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.template.EducationFormDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.EducationForm;
import by.epamtc.enrollmentsystem.service.template.EducationFormService;

import java.util.Optional;

public class EducationFormServiceImpl implements EducationFormService {
    @Override
    public Optional<EducationForm> getByID(long id) throws ServiceException {
        try {
            EducationFormDAO dao = DAOProvider.getInstance().getEducationFormDAO();
            return dao.getByID(id);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }

    @Override
    public void updateRowByID(EducationForm note) throws ServiceException {
        try {
            EducationFormDAO dao = DAOProvider.getInstance().getEducationFormDAO();
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
    public Optional<EducationForm> getByName(String name) throws ServiceException {
        try {
            EducationFormDAO dao = DAOProvider.getInstance().getEducationFormDAO();
            return dao.getByName(name);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }
}
