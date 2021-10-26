package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.template.EnrollmentStatusDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.EnrollmentStatus;
import by.epamtc.enrollmentsystem.service.template.EnrollmentStatusService;

import java.util.Optional;

public class EnrollmentStatusServiceImpl implements EnrollmentStatusService {
    @Override
    public Optional<EnrollmentStatus> getByID(long id) throws ServiceException {
        try {
            EnrollmentStatusDAO dao = DAOProvider.getInstance().getEnrollmentStatusDAO();
            return dao.getByID(id);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }

    @Override
    public void updateRowByID(EnrollmentStatus note) throws ServiceException {
        try {
            EnrollmentStatusDAO dao = DAOProvider.getInstance().getEnrollmentStatusDAO();
            dao.updateRowByID(note);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }

    @Override
    public Optional<EnrollmentStatus> getByName(String name) throws ServiceException {
        try {
            EnrollmentStatusDAO dao = DAOProvider.getInstance().getEnrollmentStatusDAO();
            return dao.getByName(name);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }
}
