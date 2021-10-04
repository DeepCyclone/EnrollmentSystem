package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.templates.DAOTemplate;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.templates.FacultyTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.utils.ClassesComposer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultyMySQL implements FacultyTempl {
    private static final String DELETE_ALL = "DELETE * FROM " + TablesNames.faculty;
    private static final String SELECT_ALL = "SELECT * FROM " + TablesNames.faculty;
    private static final String UPDATE_NOTE = "UPDATE" + TablesNames.faculty + " SET ? WHERE id = ?";
    @Override
    public Faculty getByID(int id) {
        return null;
    }

    @Override
    public void insertInto(Faculty object) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Faculty> getAll() throws DAOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Faculty> faculties = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SELECT_ALL);
            faculties = new ArrayList<>();
            while(rs.next()){
                Faculty faculty = ClassesComposer.composeFaculty(rs);
                faculties.add(faculty);
            }
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
            return faculties;

        }
    }

    @Override
    public void updateRowByID(Faculty note, int id) {

    }
}
