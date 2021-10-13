package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.templates.RoleTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Role;

import java.util.List;

public class RoleMySQL extends AbstractDAO<Role> implements RoleTempl {

    @Override
    public List<Role> getAll() throws DAOException {
        return null;
    }

    @Override
    public int getIdByName(String name) throws DAOException {
        return 0;
    }

    @Override
    public Role getByID(int id) throws DAOException {
        return null;
    }
}
