package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.tables.fields.EnrollmentStatusFields;
import by.epamtc.enrollmentsystem.dao.templates.EnrollmentStatusDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.EnrollmentStatus;
import by.epamtc.enrollmentsystem.dao.composers.builders.EnrollmentStatusBuilder;

import java.util.List;
import java.util.Optional;

public final class EnrollmentStatusMySQL extends AbstractDAO<EnrollmentStatus> implements EnrollmentStatusDAO {

    private static final String tableName = TablesNames.enrollment_status;

    @Override
    public List<EnrollmentStatus> getAll() throws DAOException {
        return super.getAll(tableName,new EnrollmentStatusBuilder());
    }

    @Override
    public long getIdByName(String name) throws DAOException {
        String idField = EnrollmentStatusFields.id;
        String nameField = EnrollmentStatusFields.name;
        return super.getIdByName(tableName,idField,nameField,name);
    }

    @Override
    public Optional<EnrollmentStatus> getByID(long id) throws DAOException {
        String idField = EnrollmentStatusFields.id;
        return super.getByID(tableName,idField,id,new EnrollmentStatusBuilder());
    }

    @Override
    public String getNameById(long id) throws DAOException {
        String nameField = EnrollmentStatusFields.name;
        String idField = EnrollmentStatusFields.id;
        return super.getNameById(tableName,idField,nameField,id);
    }

    @Override
    public void insertInto(EnrollmentStatus object) throws DAOException {

    }

    @Override
    public void updateRowByID(EnrollmentStatus note) throws DAOException {

    }

    @Override
    public List<EnrollmentStatus> getEntitiesRange(int from, int offset) throws DAOException {
        return null;
    }
}
