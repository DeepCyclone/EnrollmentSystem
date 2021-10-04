package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.templates.DAOTemplate;
import by.epamtc.enrollmentsystem.dao.templates.RoleTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Role;

import java.util.List;

public class RoleMySQL implements RoleTempl {
    @Override
    public Role getByID(int id) {
        return null;
    }

    @Override
    public void insertInto(Role object) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public void updateRowByID(Role note, int id) {

    }
}
