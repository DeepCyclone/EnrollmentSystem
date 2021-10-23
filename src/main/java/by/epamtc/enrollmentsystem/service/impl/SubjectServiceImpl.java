package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.interfaces.RoleDAO;
import by.epamtc.enrollmentsystem.dao.interfaces.SubjectDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Role;
import by.epamtc.enrollmentsystem.model.Subject;
import by.epamtc.enrollmentsystem.service.templates.SubjectService;

import java.util.List;
import java.util.Optional;

public class SubjectServiceImpl implements SubjectService {

    @Override
    public Optional<Subject> getByID(long id) throws ServiceException {
        try {
            SubjectDAO dao = DAOProvider.getInstance().getSubjectDAO();
            return dao.getByID(id);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }

    @Override
    public void updateRowByID(Subject note) throws ServiceException {
        try {
            SubjectDAO dao = DAOProvider.getInstance().getSubjectDAO();
            dao.updateRowByID(note);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }

    @Override
    public Optional<Subject> getByName(String name) throws ServiceException {
        try {
            SubjectDAO dao = DAOProvider.getInstance().getSubjectDAO();
            return dao.getByName(name);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }
}
