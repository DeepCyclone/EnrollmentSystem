package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.tables.fields.FacultyFields;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.templates.FacultyDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.dao.composers.builders.FacultyBuilder;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public final class FacultyMySQL extends AbstractDAO<Faculty> implements FacultyDAO {

    private static final String tableName = TablesNames.faculty;
    private static final String GET_RECORDS_NUMBER = "SELECT COUNT(*) FROM " + TablesNames.faculty;


    @Override
    public Optional<Faculty> getByID(long id) throws DAOException {
        String idFieldName = FacultyFields.id;
        return super.getByID(tableName,idFieldName,id,new FacultyBuilder());
    }

    @Override
    public void insertInto(Faculty object) throws DAOException {

    }

    @Override
    public void updateRowByID(Faculty note) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Faculty> getEntitiesRange(int from, int offset) throws DAOException {
        return super.getEntitiesRange(tableName,from,offset,new FacultyBuilder());
    }

    @Override
    public List<Faculty> getAll() throws DAOException {
        return super.getAll(tableName,new FacultyBuilder());
    }

    @Override
    public long getIdByName(String name) throws DAOException {
        String idField = FacultyFields.id;
        String nameField = FacultyFields.name;
        return super.getIdByName(tableName,idField,nameField,name);
    }

    @Override
    public String getNameById(long id) throws DAOException {
        String nameField = FacultyFields.name;
        String idField = FacultyFields.id;
        return super.getNameById(tableName,idField,nameField,id);
    }

    @Override
    public int getRecordsNumber() throws DAOException {
     return super.getNumberOfRecords(tableName);
    }
}
