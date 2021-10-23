package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.interfaces.Subjectm2mFacultyDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Subjectm2mFaculty;

import java.sql.*;
import java.util.*;

public final class Subjectm2mFacultyMySQL extends AbstractDAO<Subjectm2mFaculty> implements Subjectm2mFacultyDAO {

    private static final String GET_FACULTIES_CORRESPONDING_TO_SUBJECTS = "SELECT f_name,s_name FROM subject " +
                                                                          "JOIN subject_m2m_faculty ON s_id = smf_s_id " +
                                                                          "JOIN faculty AS f ON subject_m2m_faculty.smf_f_id = f.f_id";//TODO crash into parts and !ask if this must be here!


    @Override
    public List<Subjectm2mFaculty> getAll() throws DAOException {
        return null;
    }

    @Override
    public void insertInto(Subjectm2mFaculty object) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public int getNumberOfRecords() throws DAOException {
        return 0;
    }

    @Override
    public List<Subjectm2mFaculty> getEntitiesRange(int from, int offset) throws DAOException {
        return null;
    }

    @Override
    public Map<String, List<String>> getFacultiesCorrespondingToSubjects() throws DAOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Map <String,List<String>> facSub = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_FACULTIES_CORRESPONDING_TO_SUBJECTS);
            facSub = new HashMap<>();
            List<String> subjects = null;
            while(rs.next()) {
                String facultyName = rs.getString("f_name");
                if(!facSub.containsKey(facultyName)){
                    subjects = new ArrayList<>();
                    facSub.put(facultyName,subjects);
                }
                subjects.add(rs.getString("s_name"));
            }
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
        }
        return facSub;
    }

}
