package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.QueryExecutor;
import by.epamtc.enrollmentsystem.dao.mapping.SchemaMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.EnrollmentStatusMapping;
import by.epamtc.enrollmentsystem.dao.template.EnrollmentStatusDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.EnrollmentStatus;
import by.epamtc.enrollmentsystem.dao.composer.builders.EnrollmentStatusBuilder;

import java.util.List;
import java.util.Optional;

public final class EnrollmentStatusMySQL extends AbstractDAO<EnrollmentStatus> implements EnrollmentStatusDAO {

    public EnrollmentStatusMySQL(QueryExecutor<EnrollmentStatus> executor) {
        super(executor);
    }

    private static final String TABLE_NAME = SchemaMapping.enrollment_status;

    @Override
    public List<EnrollmentStatus> getAll() throws DAOException {
        return super.getAll(TABLE_NAME);
    }

    @Override
    public Optional<EnrollmentStatus> getByID(long id) throws DAOException {
        String idField = EnrollmentStatusMapping.id;
        return super.getByID(TABLE_NAME,idField,id);
    }

    @Override
    public void insertInto(EnrollmentStatus object) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public int getNumberOfRecords() throws DAOException {
        return 0;
    }

    @Override
    public void updateRowByID(EnrollmentStatus note) throws DAOException {

    }

    @Override
    public void deleteRowByID(long id) throws DAOException {

    }

    @Override
    public List<EnrollmentStatus> getEntitiesRange(int from, int offset) throws DAOException {
        return null;
    }

    @Override
    public Optional<EnrollmentStatus> getByName(String name) throws DAOException {
        return super.getByName(TABLE_NAME,EnrollmentStatusMapping.name,name);
    }
}
