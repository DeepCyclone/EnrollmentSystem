package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.tables.fields.FacultyFields;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.templates.FacultyTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.services.composers.builders.entity.FacultyBuilder;

import java.util.List;

public class FacultyMySQL extends AbstractDAO<Faculty> implements FacultyTempl {
    private static final String tableName = TablesNames.faculty;
    private static final String DELETE_ALL = "DELETE * FROM " + TablesNames.faculty;
    private static final String SELECT_ALL = "SELECT * FROM " + TablesNames.faculty;
    private static final String UPDATE_NOTE = "UPDATE" + TablesNames.faculty + " SET ? WHERE id = ?";
    private static final String GET_BY_ID = "SELECT * FROM " + TablesNames.faculty +
                                            " WHERE " + FacultyFields.id + " = ?";
    @Override
    public Faculty getByID(int id) throws DAOException {
        String idFieldName = FacultyFields.id;
        return super.getByID(tableName,idFieldName,id,new FacultyBuilder());
    }

    @Override
    public void insertInto(Faculty object) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Faculty> getAll() throws DAOException {
        return super.getAll(tableName,new FacultyBuilder());
    }

    @Override
    public void updateRowByID(Faculty note, int id) {

    }

    @Override
    public int getIdByName(String name) {

        String idField = FacultyFields.id;
        String nameField = FacultyFields.name;
        return super.getIdByName(tableName,idField,nameField,name);
    }
}
