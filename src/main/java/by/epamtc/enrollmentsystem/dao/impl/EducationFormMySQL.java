package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.tables.fields.EducationFormFields;
import by.epamtc.enrollmentsystem.dao.templates.EducationFormTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.EducationForm;
import by.epamtc.enrollmentsystem.services.composers.builders.entity.EducationFormBuilder;

import java.sql.*;
import java.util.List;

public class EducationFormMySQL extends AbstractDAO<EducationForm> implements EducationFormTempl {
    private static final String tableName = TablesNames.education_form;
    private static final String DELETE_ALL = "DELETE * FROM " + TablesNames.education_form;
    private static final String SELECT_ALL = "SELECT * FROM " + TablesNames.education_form;
    private static final String UPDATE_NOTE = "UPDATE " + TablesNames.education_form +
                                              " SET ? WHERE " + EducationFormFields.id + " = ?";

    @Override
    public void insertInto(EducationForm object) throws DAOException {

    }
    @Override
    public void deleteAll() {

    }

    @Override
    public void updateRowByID(EducationForm note,int id) throws DAOException {

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(UPDATE_NOTE);
            preparedStatement.setString(0,note.getName());
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
        catch (Exception e){
            throw new DAOException(e.getMessage(),e.getCause());
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,preparedStatement);
        }
    }

    @Override
    public List<EducationForm> getAll() throws DAOException {
        return super.getAll(tableName,new EducationFormBuilder());
    }

    @Override
    public EducationForm getByID(int id) throws DAOException {
        String idFieldName = EducationFormFields.id;
        return super.getByID(tableName,idFieldName,id,new EducationFormBuilder());
    }

    @Override
    public int getIdByName(String name) {
        String tableName = TablesNames.education_form;
        String nameField = EducationFormFields.name;
        String idField = EducationFormFields.id;
        return super.getIdByName(tableName,idField,nameField,name);
    }

}
