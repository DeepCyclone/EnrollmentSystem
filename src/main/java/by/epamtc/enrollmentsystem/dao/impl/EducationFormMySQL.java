package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.tables.fields.EducationFormFields;
import by.epamtc.enrollmentsystem.dao.templates.EducationFormTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.EducationForm;
import by.epamtc.enrollmentsystem.utils.ClassesComposer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EducationFormMySQL implements EducationFormTempl {
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

    public List<EducationForm> getAll() throws DAOException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(SELECT_ALL);
            List<EducationForm> allNotes = new ArrayList<>();
            while(rs.next()){
                EducationForm educationForm = ClassesComposer.composeEducationForm(rs);
                allNotes.add(educationForm);
            }
            ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
            return allNotes;
        }

        catch (Exception e){
            throw new DAOException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public EducationForm getByID(int id) throws DAOException {
        return null;
    }

}
