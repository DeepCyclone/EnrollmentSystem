package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.interfaces.FacultyDAO;
import by.epamtc.enrollmentsystem.dao.interfaces.RoleDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.model.Role;
import by.epamtc.enrollmentsystem.service.templates.RoleService;

import java.util.Optional;

public class RoleServiceImpl implements RoleService {
    @Override
    public Optional<Role> getByID(long id) throws ServiceException {
        try {
            RoleDAO dao = DAOProvider.getInstance().getRoleDAO();
            return dao.getByID(id);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }

    @Override
    public void updateRowByID(Role note) throws ServiceException {
        try {
            RoleDAO dao = DAOProvider.getInstance().getRoleDAO();
            dao.updateRowByID(note);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }

    @Override
    public Optional<Role> getByName(String name) throws ServiceException {
        try {
            RoleDAO dao = DAOProvider.getInstance().getRoleDAO();
            return dao.getByName(name);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }
}
