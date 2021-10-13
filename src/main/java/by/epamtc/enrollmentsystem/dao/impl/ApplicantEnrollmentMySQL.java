package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.tables.fields.ApplicantEnrollmentFields;
import by.epamtc.enrollmentsystem.dao.templates.ApplicantEnrollmentTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.services.composers.builders.entity.ApplicantEnrollmentBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicantEnrollmentMySQL extends AbstractDAO<ApplicantEnrollment> implements ApplicantEnrollmentTempl {

    private static final String INSERT_INTO = "INSERT INTO " + TablesNames.applicant_enrollment +
                                              " VALUES (?,?,?,?)";

    private static final String GET_INFO_BY_USER_ID = "SELECT " + ApplicantEnrollmentFields.facultyId + ", " +
                                                      ApplicantEnrollmentFields.educationFormId + " FROM " + TablesNames.applicant_enrollment +
                                                      " WHERE " + ApplicantEnrollmentFields.userId + " = ?";

    private static final String UPDATE_EDUCATION_FORM = "UPDATE " + TablesNames.applicant_enrollment +
                                                        " SET " + ApplicantEnrollmentFields.educationFormId + " = ?" +
                                                        " WHERE " + ApplicantEnrollmentFields.userId +  " = ?" +
                                                        " AND " + ApplicantEnrollmentFields.facultyId + " = ?";

    private static final String GET_FACULTY_BY_USER_ID = "SELECT * FROM " + TablesNames.applicant_enrollment +
                                                        " WHERE " + ApplicantEnrollmentFields.userId + " = ?" +
                                                        " AND " + ApplicantEnrollmentFields.facultyId + " = ?";

    private static final String DELETE_FACULTIES_BY_USER_ID = "DELETE FROM " + TablesNames.applicant_enrollment +
                                                            " WHERE " + ApplicantEnrollmentFields.userId + " = ?";



    @Override
    public List<ApplicantEnrollment> getAll() throws DAOException {
        String tableName = TablesNames.applicant_enrollment;
        return super.getAll(tableName,new ApplicantEnrollmentBuilder());//TODO protected - антипаттерн. И как иначе?
    }

    @Override
    public int getIdByName(String name) throws DAOException {
        return 1;//TODO Throw unsupported operation
    }

    @Override
    public ApplicantEnrollment getByID(int id) throws DAOException {
        return null;//TODO Throw unsupported operation
    }

    @Override
    public void insertInto(ApplicantEnrollment object) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(INSERT_INTO);
            stmt.setInt(1,object.getUserId());
            stmt.setInt(2,object.getFacultyId());
            stmt.setInt(3,object.getEducationFormId());
            stmt.setInt(4,object.getEnrollmentStatusId());
            stmt.executeUpdate();
        }
        catch (Exception ignored){

        }
        finally {
            if(conn !=null){
                ConnectionPool.getInstance().closeConnection(conn,stmt);
            }
        }
    }

    @Override
    public void updateRowByID(ApplicantEnrollment note, int id) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Map<Integer,List<Integer>> getSelectedFacultiesByUserId(int userId){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<Integer,List<Integer>> facultyForms = null;
        try{
            facultyForms = new HashMap<>();
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(GET_INFO_BY_USER_ID);
            stmt.setInt(1,userId);
            rs = stmt.executeQuery();
            while(rs.next()){
                int key = rs.getInt(ApplicantEnrollmentFields.facultyId);
                if(!facultyForms.containsKey(key)) {
                    facultyForms.put(key,new ArrayList<>());
                }
                int value = rs.getInt(ApplicantEnrollmentFields.educationFormId);
                facultyForms.get(key).add(value);
            }

        }
        catch (Exception ignored){

        }
        finally {
            if(conn !=null){
                ConnectionPool.getInstance().closeConnection(conn,stmt);
            }
        }
        return facultyForms;
    }

    @Override
    public void updateEducationForm(int userId, int facultyId,int educationFormId) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(UPDATE_EDUCATION_FORM);
            preparedStatement.setInt(1,educationFormId);
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(1,facultyId);
            preparedStatement.executeUpdate();
        }
        catch (Exception e){
//            throw new DAOException(e.getMessage(),e.getCause());
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,preparedStatement);
        }
    }

    @Override
    public void deleteFacultiesByUserId(int userId) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(DELETE_FACULTIES_BY_USER_ID);
            preparedStatement.setInt(1,userId);
            preparedStatement.executeUpdate();
        }
        catch (Exception e){
            System.err.println("Exception");
//            throw new DAOException(e.getMessage(),e.getCause());
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,preparedStatement);
        }
    }

    @Override
    public boolean userHasFaculty(int userId, int facultyId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;
        try{

            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(GET_FACULTY_BY_USER_ID);
            stmt.setInt(1,userId);
            stmt.setInt(2,facultyId);
            rs = stmt.executeQuery();

            if(rs.next()){//TODO check it
                result = true;
            }

        }
        catch (Exception ignored){

        }
        finally {
            if(conn !=null){
                ConnectionPool.getInstance().closeConnection(conn,stmt);
            }
        }
        return result;
    }

}
