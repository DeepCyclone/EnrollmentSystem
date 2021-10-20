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

    @Override
    public List<Facility> getAll() throws DAOException {
        String tableName = TablesNames.facility;
        return super.getAll(tableName,new FacilityBuilder());
    }

    @Override
    public int getIdByName(String name) throws DAOException {
        String tableName = TablesNames.facility;
        String idField = FacilityFields.id;
        String nameField = FacilityFields.name;
        return super.getIdByName(tableName,idField,nameField,name);
    }

    @Override
    public Optional<Facility> getByID(long id) throws DAOException {
        String tableName = TablesNames.facility;
        String idField = FacilityFields.id;
        return super.getByID(tableName,idField,id,new FacilityBuilder());
    }

    public String getNameById(long id) throws DAOException {
        String tableName = TablesNames.facility;
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
}
