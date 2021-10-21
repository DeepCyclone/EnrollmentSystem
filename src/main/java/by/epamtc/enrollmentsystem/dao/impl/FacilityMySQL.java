package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.tables.fields.FacilityFields;
import by.epamtc.enrollmentsystem.dao.templates.FacilityDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Facility;
import by.epamtc.enrollmentsystem.dao.composers.builders.FacilityBuilder;

import java.util.List;
import java.util.Optional;

public final class FacilityMySQL extends AbstractDAO<Facility> implements FacilityDAO {

    private static final String tableName = TablesNames.facility;

    @Override
    public List<Facility> getAll() throws DAOException {
        return super.getAll(tableName,new FacilityBuilder());
    }

    @Override
    public long getIdByName(String name) throws DAOException {
        String idField = FacilityFields.id;
        String nameField = FacilityFields.name;
        return super.getIdByName(tableName,idField,nameField,name);
    }

    @Override
    public Optional<Facility> getByID(long id) throws DAOException {
        String idField = FacilityFields.id;
        return super.getByID(tableName,idField,id,new FacilityBuilder());
    }

    public String getNameById(long id) throws DAOException {
        String nameField = FacilityFields.name;
        String idField = FacilityFields.id;
        return super.getNameById(tableName,idField,nameField,id);
    }

    @Override
    public void insertInto(Facility object) throws DAOException {

    }

    @Override
    public void updateRowByID(Facility note) throws DAOException {

    }

    @Override
    public List<Facility> getEntitiesRange(int from, int offset) throws DAOException {
        return null;
    }
}
