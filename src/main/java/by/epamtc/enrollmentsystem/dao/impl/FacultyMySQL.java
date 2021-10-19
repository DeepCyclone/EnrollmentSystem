package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.composers.builders.entity.EntityBuilder;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.tables.fields.EducationFormFields;
import by.epamtc.enrollmentsystem.dao.tables.fields.FacultyFields;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.templates.FacultyDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.dao.composers.builders.entity.FacultyBuilder;
import by.epamtc.enrollmentsystem.utils.SQLGenerator;

import java.sql.*;
import java.util.List;

public class FacultyMySQL extends AbstractDAO<Faculty> implements FacultyDAO {

    private static final String SELECT_RANGE = "SELECT * FROM " + TablesNames.faculty + " LIMIT ?,?";
    private static final String GET_RECORDS_NUMBER = "SELECT COUNT(*) FROM " + TablesNames.faculty;

    @Override
    public Faculty getByID(long id) throws DAOException {
        String tableName = TablesNames.faculty;
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
        String tableName = TablesNames.faculty;
        return super.getAll(tableName,new FacultyBuilder());
    }

    @Override
    public void updateRowByID(Faculty note, long id) {

    }

    @Override
    public int getIdByName(String name) throws DAOException {
        String tableName = TablesNames.faculty;
        String idField = FacultyFields.id;
        String nameField = FacultyFields.name;
        return super.getIdByName(tableName,idField,nameField,name);
    }

    @Override
    public String getNameById(long id) throws DAOException {
        String tableName = TablesNames.faculty;
        String nameField = FacultyFields.name;
        String idField = FacultyFields.id;
        return super.getNameById(tableName,idField,nameField,id);
    }

    @Override
    public List<Faculty> getFacultiesRange(int from, int offset) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Faculty> faculties = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(SELECT_RANGE);
            stmt.setInt(1,from);
            stmt.setInt(2,offset);
            rs = stmt.executeQuery();
            FacultyBuilder builder = new FacultyBuilder();
            faculties = builder.buildObjectsList(rs);
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
        }
        return faculties;
    }

    @Override
    public int getRecordsNumber() throws DAOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int records = 0;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_RECORDS_NUMBER);
            while(rs.next()){
                records = rs.getInt(1);
            }
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
        }
        return records;
    }
}
