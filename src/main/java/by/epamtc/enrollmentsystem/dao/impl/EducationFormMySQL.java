package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.tables.fields.EducationFormFields;
import by.epamtc.enrollmentsystem.dao.interfaces.EducationFormDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.EducationForm;
import by.epamtc.enrollmentsystem.dao.composers.builders.EducationFormBuilder;

import java.util.List;
import java.util.Optional;

public final class EducationFormMySQL extends AbstractDAO<EducationForm> implements EducationFormDAO {

    private static final String tableName = TablesNames.education_form;

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
    public int getNumberOfRecords() throws DAOException {
        return 0;
    }

    @Override
    public List<EducationForm> getEntitiesRange(int from, int offset) throws DAOException {
        return null;
    }

    @Override
    public void updateRowByID(EducationForm note) throws DAOException {
        executeUpdateQuery(UPDATE_RECORD_BY_ID,note.getName(),note.getId());
    }

    @Override
    public List<EducationForm> getAll() throws DAOException {
        return super.getAll(tableName,new EducationFormBuilder());
    }

    @Override
    public Optional<EducationForm> getByID(long id) throws DAOException {
        String idFieldName = EducationFormFields.id;
        return super.getByID(tableName,idFieldName,id,new EducationFormBuilder());
    }

    @Override
    public Optional<EducationForm> getByName(String name) {
        return Optional.empty();
    }
}
