package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.tables.fields.EducationFormFields;
import by.epamtc.enrollmentsystem.dao.tables.fields.RoleFields;
import by.epamtc.enrollmentsystem.dao.templates.RoleDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Role;

import java.util.List;

public class RoleMySQL extends AbstractDAO<Role> implements RoleDAO {

    @Override
    public List<Role> getAll() throws DAOException {
        return null;
    }

    @Override
    public int getIdByName(String name) throws DAOException {
        return 0;
    }

    @Override
    public Role getByID(long id) throws DAOException {
        return null;
    }

    @Override
    public String getNameById(long id) throws DAOException {
        String tableName = TablesNames.role;
        String nameField = RoleFields.name;
        String idField = RoleFields.id;
        return super.getNameById(tableName,idField,nameField,id);
    }
}
