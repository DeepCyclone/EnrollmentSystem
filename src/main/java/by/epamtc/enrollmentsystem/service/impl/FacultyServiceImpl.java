package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.DAOTemplate;
import by.epamtc.enrollmentsystem.dao.templates.FacultyDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.service.FacultyService;

import java.util.List;

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
            return dao.getRecordsNumber();
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }
}
