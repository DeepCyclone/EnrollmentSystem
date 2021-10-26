package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.mapping.SchemaMapping;
import by.epamtc.enrollmentsystem.dao.template.SystemInformationDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.SystemInformation;
import by.epamtc.enrollmentsystem.dao.mapping.fields.SystemInformationMapping;

import java.util.List;
import java.util.Optional;

public final class SystemInformationMySQL extends AbstractDAO<SystemInformation> implements SystemInformationDAO {
    private static final String GET_BY_NAME = "SELECT * FROM " + SchemaMapping.system_information +
                                              " WHERE " + SystemInformationMapping.name +  "= ?";


    @Override
    public List<SystemInformation> getAll() throws DAOException {
        return null;
    }


    @Override
    public Optional<SystemInformation> getByID(long id) throws DAOException {
        return null;
    }

    @Override
    public void insertInto(SystemInformation object) throws DAOException {
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public int getNumberOfRecords() throws DAOException {
        return 0;
    }

    @Override
    public List<SystemInformation> getEntitiesRange(int from, int offset) throws DAOException {
        return null;
    }

    @Override
    public void updateRowByID(SystemInformation note) {

    }

    @Override
    public Optional<SystemInformation> getByName(String name) throws DAOException {
        return Optional.empty();
    }
}
