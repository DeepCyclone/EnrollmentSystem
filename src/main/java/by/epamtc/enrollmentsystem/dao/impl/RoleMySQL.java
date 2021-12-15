package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.QueryExecutor;
import by.epamtc.enrollmentsystem.dao.mapping.SchemaMapping;
import by.epamtc.enrollmentsystem.dao.template.RoleDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Role;

import java.util.List;
import java.util.Optional;

public final class RoleMySQL extends AbstractDAO<Role> implements RoleDAO {

    private static final String TABLE_NAME = SchemaMapping.role;

    public RoleMySQL(QueryExecutor<Role> executor) {
        super(executor);
    }

    @Override
    public List<Role> getAll() throws DAOException {
        return null;
    }

    @Override
    public Optional<Role> getByID(long id) throws DAOException {
        return null;
    }

    @Override
    public void insertInto(Role object) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public int getNumberOfRecords() throws DAOException {
        return 0;
    }

    @Override
    public void updateRowByID(Role note) throws DAOException {

    }

    @Override
    public void deleteRowByID(long id) throws DAOException {

    }

    @Override
    public List<Role> getEntitiesRange(int from, int offset) throws DAOException {
        return null;
    }

    @Override
    public Optional<Role> getByName(String name) throws DAOException {
        return Optional.empty();
    }
}
