package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.tables.fields.EducationFormFields;
import by.epamtc.enrollmentsystem.dao.tables.fields.RoleFields;
import by.epamtc.enrollmentsystem.dao.templates.RoleDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Facilitym2mUserInfo;
import by.epamtc.enrollmentsystem.model.Role;

import java.util.List;
import java.util.Optional;

public final class RoleMySQL extends AbstractDAO<Role> implements RoleDAO {

    @Override
    public List<Role> getAll() throws DAOException {
        return null;
    }

    @Override
    public long getIdByName(String name) throws DAOException {
        return 0;
    }

    @Override
    public Optional<Role> getByID(long id) throws DAOException {
        return null;
    }

    @Override
    public String getNameById(long id) throws DAOException {
        String tableName = TablesNames.role;
        String nameField = RoleFields.name;
        String idField = RoleFields.id;
        return super.getNameById(tableName,idField,nameField,id);
    }

    @Override
    public void insertInto(Role object) throws DAOException {

    }

    @Override
    public void updateRowByID(Role note) throws DAOException {

    }

    @Override
    public List<Role> getEntitiesRange(int from, int offset) throws DAOException {
        return null;
    }
}
