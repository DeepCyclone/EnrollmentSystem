package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.tables.fields.FacilityFields;
import by.epamtc.enrollmentsystem.dao.templates.FacilityTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Facility;
import by.epamtc.enrollmentsystem.services.composers.builders.entity.FacilityBuilder;

import java.util.List;

public class FacilityMySQL extends AbstractDAO<Facility> implements FacilityTempl {

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
    public Facility getByID(int id) throws DAOException {
        return null;
    }
}
