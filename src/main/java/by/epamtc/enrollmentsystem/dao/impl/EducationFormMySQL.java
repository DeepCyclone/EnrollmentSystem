package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.QueryExecutor;
import by.epamtc.enrollmentsystem.dao.mapping.SchemaMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.EducationFormMapping;
import by.epamtc.enrollmentsystem.dao.template.EducationFormDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.EducationForm;
import by.epamtc.enrollmentsystem.dao.composer.builders.EducationFormBuilder;

import java.util.List;
import java.util.Optional;

public final class EducationFormMySQL extends AbstractDAO<EducationForm> implements EducationFormDAO {

    public EducationFormMySQL(QueryExecutor<EducationForm> executor){super(executor);}

    private static final String TABLE_NAME = SchemaMapping.education_form;

    private static final String UPDATE_RECORD_BY_ID = " UPDATE " + SchemaMapping.education_form +
                                                      " SET " + EducationFormMapping.name  + " = ? " +
                                                      " WHERE " + EducationFormMapping.id + " = ?";

    @Override
    public void insertInto(EducationForm object) throws DAOException {

    }
    @Override
    public void deleteAll() {

    }

    @Override
    public int getNumberOfRecords() throws DAOException {
        return 0;
    }

    @Override
    public List<EducationForm> getEntitiesRange(int from, int offset) throws DAOException {
        return null;
    }

    @Override
    public void updateRowByID(EducationForm note) throws DAOException {
        executor.executeUpdateQuery(UPDATE_RECORD_BY_ID,note.getName(),note.getId());
    }

    @Override
    public void deleteRowByID(long id) throws DAOException {

    }

    @Override
    public List<EducationForm> getAll() throws DAOException {
        return super.getAll(TABLE_NAME);
    }

    @Override
    public Optional<EducationForm> getByID(long id) throws DAOException {
        String idFieldName = EducationFormMapping.id;
        return super.getByID(TABLE_NAME,idFieldName,id);
    }

    @Override
    public Optional<EducationForm> getByName(String name) throws DAOException {
        return super.getByName(TABLE_NAME,EducationFormMapping.name,name);
    }
}
