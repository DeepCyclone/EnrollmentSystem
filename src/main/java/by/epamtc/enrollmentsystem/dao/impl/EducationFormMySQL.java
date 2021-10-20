package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.tables.fields.EducationFormFields;
import by.epamtc.enrollmentsystem.dao.templates.EducationFormDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.EducationForm;
import by.epamtc.enrollmentsystem.dao.composers.builders.EducationFormBuilder;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public final class EducationFormMySQL extends AbstractDAO<EducationForm> implements EducationFormDAO {
    private static final String UPDATE_RECORD_BY_ID = " UPDATE " + TablesNames.education_form +
                                                      " SET " + EducationFormFields.name  + " = ? " +
                                                      " WHERE " + EducationFormFields.id + " = ?";

    @Override
    public void insertInto(EducationForm object) throws DAOException {

    }
    @Override
    public void deleteAll() {

    }

    @Override
    public void updateRowByID(EducationForm note) throws DAOException {
        executeUpdateQuery(UPDATE_RECORD_BY_ID,note.getName(),note.getId());
    }

    @Override
    public List<EducationForm> getAll() throws DAOException {
        String tableName = TablesNames.education_form;
        return super.getAll(tableName,new EducationFormBuilder());
    }

    @Override
    public Optional<EducationForm> getByID(long id) throws DAOException {
        String tableName = TablesNames.education_form;
        String idFieldName = EducationFormFields.id;
        return super.getByID(tableName,idFieldName,id,new EducationFormBuilder());
    }

    @Override
    public int getIdByName(String name) throws DAOException {
        String tableName = TablesNames.education_form;
        String nameField = EducationFormFields.name;
        String idField = EducationFormFields.id;
        return super.getIdByName(tableName,idField,nameField,name);
    }

    @Override
    public String getNameById(long id) throws DAOException {
        String tableName = TablesNames.education_form;
        String nameField = EducationFormFields.name;
        String idField = EducationFormFields.id;
        return super.getNameById(tableName,idField,nameField,id);
    }

}
