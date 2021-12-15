package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.QueryExecutor;
import by.epamtc.enrollmentsystem.dao.mapping.SchemaMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.FacilityMapping;
import by.epamtc.enrollmentsystem.dao.template.FacilityDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Facility;
import by.epamtc.enrollmentsystem.dao.composer.builders.FacilityBuilder;

import java.util.List;
import java.util.Optional;

public final class FacilityMySQL extends AbstractDAO<Facility> implements FacilityDAO {

    public FacilityMySQL(QueryExecutor<Facility> executor) {
        super(executor);
    }

    private static final String TABLE_NAME = SchemaMapping.facility;

    @Override
    public List<Facility> getAll() throws DAOException {
        return super.getAll(TABLE_NAME);
    }

    @Override
    public Optional<Facility> getByID(long id) throws DAOException {
        String idField = FacilityMapping.id;
        return super.getByID(TABLE_NAME,idField,id);
    }

    @Override
    public void updateRowByID(Facility note) throws DAOException {

    }

    @Override
    public void deleteRowByID(long id) throws DAOException {

    }

    @Override
    public void insertInto(Facility object) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public int getNumberOfRecords() throws DAOException {
        return 0;
    }

    @Override
    public List<Facility> getEntitiesRange(int from, int offset) throws DAOException {
        return null;
    }

    @Override
    public Optional<Facility> getByName(String name) throws DAOException {
        return Optional.empty();
    }
}
