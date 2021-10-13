package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.tables.fields.SubjectFields;
import by.epamtc.enrollmentsystem.dao.templates.SubjectTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Subject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectMySQL extends AbstractDAO<Subject> implements SubjectTempl {

    private static final String SELECT_NAMES = "SELECT " + SubjectFields.name + " FROM " + TablesNames.education_form;
    private static final String SELECT_ALL = "SELECT * FROM " + TablesNames.education_form;


    @Override
    public List<Subject> getAll() throws DAOException {
        return null;
    }

    @Override
    public int getIdByName(String name) throws DAOException {
        return 0;
    }

    @Override
    public Subject getByID(int id) throws DAOException {
        return null;
    }

    @Override
    public void insertInto(Subject object) throws DAOException {

    }


    @Override
    public void deleteAll() {

    }

    @Override
    public void updateRowByID(Subject note, int id) {

    }

    @Override
    public List<String> getSubjectsNames() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<String> names = new ArrayList<>();
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SELECT_NAMES);
            while(rs.next()) {
                names.add(rs.getString(SubjectFields.name));
            }
        }
        catch (Exception ignored){

        }
        finally {
            if(conn !=null){
                ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
            }
        }
        return names;
    }
}
