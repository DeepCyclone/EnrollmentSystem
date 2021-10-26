package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.template.FacultyDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.service.template.FacultyService;

import java.util.List;
import java.util.Optional;

public class FacultyServiceImpl implements FacultyService {
    public List<Faculty> getAll() throws ServiceException {
        try {
            FacultyDAO dao = DAOProvider.getInstance().getFacultyDAO();
            return dao.getAll();
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }

    @Override
    public List<Faculty> getFacultiesRange(int from, int offset) throws ServiceException {
        try {
            FacultyDAO dao = DAOProvider.getInstance().getFacultyDAO();
            return dao.getEntitiesRange(from,offset);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }

    @Override
    public int getFacultiesNumber() throws ServiceException {
        try {
            FacultyDAO dao = DAOProvider.getInstance().getFacultyDAO();
            return dao.getNumberOfRecords();
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }

    @Override
    public Optional<Faculty> getByID(long id) throws ServiceException {
        try {
            FacultyDAO dao = DAOProvider.getInstance().getFacultyDAO();
            return dao.getByID(id);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }

    @Override
    public void updateRowByID(Faculty note) throws ServiceException {
        try {
            FacultyDAO dao = DAOProvider.getInstance().getFacultyDAO();
            dao.updateRowByID(note);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }

    @Override
    public Optional<Faculty> getByName(String name) throws ServiceException {
        try {
            FacultyDAO dao = DAOProvider.getInstance().getFacultyDAO();
            return dao.getByName(name);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }
}
