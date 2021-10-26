package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.mapping.fields.FacultyMapping;
import by.epamtc.enrollmentsystem.dao.mapping.SchemaMapping;
import by.epamtc.enrollmentsystem.dao.template.FacultyDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.dao.composer.builders.FacultyBuilder;

import java.util.List;
import java.util.Optional;

public final class FacultyMySQL extends AbstractDAO<Faculty> implements FacultyDAO {

    private static final String tableName = SchemaMapping.faculty;

    @Override
    public Optional<Faculty> getByID(long id) throws DAOException {
        String idFieldName = FacultyMapping.id;
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
    public int getNumberOfRecords() throws DAOException {
     return super.getNumberOfRecords(tableName);
    }

    @Override
    public Optional<Faculty> getByName(String name) throws DAOException {
        return Optional.empty();
    }
}
