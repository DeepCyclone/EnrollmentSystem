package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.tables.fields.ApplicantEnrollmentFields;
import by.epamtc.enrollmentsystem.dao.templates.ApplicantEnrollmentDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.dao.composers.builders.entity.ApplicantEnrollmentBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public final class ApplicantEnrollmentMySQL extends AbstractDAO<ApplicantEnrollment> implements ApplicantEnrollmentDAO {

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

    private static final String GET_TABLE_INFO_BY_USER_ID = "SELECT * FROM " + TablesNames.applicant_enrollment +
                                                           " WHERE " + ApplicantEnrollmentFields.userId + " = ?";

    private static final String UPDATE_ENROLLMENT_STATUS = "UPDATE " + TablesNames.applicant_enrollment +
                                                        " SET " + ApplicantEnrollmentFields.enrollmentStatusId + " = ?" +
                                                        " WHERE " + ApplicantEnrollmentFields.userId + " = ? " +
                                                        " AND " + ApplicantEnrollmentFields.facultyId + " = ?" +
                                                        " AND " + ApplicantEnrollmentFields.educationFormId + " = ?";




    @Override
    public List<ApplicantEnrollment> getAll() throws DAOException {
        String tableName = TablesNames.applicant_enrollment;
        return super.getAll(tableName,new ApplicantEnrollmentBuilder());//TODO protected - антипаттерн. И как иначе?
    }

    @Override
    public int getIdByName(String name){
        throw new UnsupportedOperationException();
    }

    @Override
    public String getNameById(long id){
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ApplicantEnrollment> getByID(long id){
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertInto(ApplicantEnrollment object) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(INSERT_INTO);
            stmt.setLong(1,object.getUserId());
            stmt.setLong(2,object.getFacultyId());
            stmt.setLong(3,object.getEducationFormId());
            stmt.setLong(4,object.getEnrollmentStatusId());
            stmt.executeUpdate();
        }
        catch (SQLException exception){
            throw new DAOException(exception.getMessage(),exception);
        }
        finally {
            if(conn !=null){
                ConnectionPool.getInstance().closeConnection(conn,stmt);
            }
        }
    }

    @Override
    public void updateRowByID(ApplicantEnrollment note, long id) throws DAOException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(UPDATE_ENROLLMENT_STATUS);
            preparedStatement.setLong(1,note.getEnrollmentStatusId());
            preparedStatement.setLong(2,note.getUserId());
            preparedStatement.setLong(3,note.getFacultyId());
            preparedStatement.setLong(4,note.getEducationFormId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException exception){
            throw new DAOException(exception.getMessage(),exception);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,preparedStatement);
        }
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Map<Long,List<Long>> getFacultiesWithFormsByUserId(long userId) throws DAOException {//TODO убрать
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<Long,List<Long>> facultyForms = null;
        try{
            facultyForms = new HashMap<>();
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(GET_INFO_BY_USER_ID);
            stmt.setLong(1,userId);
            rs = stmt.executeQuery();
            while(rs.next()){
                long key = rs.getInt(ApplicantEnrollmentFields.facultyId);
                if(!facultyForms.containsKey(key)) {
                    facultyForms.put(key,new ArrayList<>());
                }
                long value = rs.getInt(ApplicantEnrollmentFields.educationFormId);
                facultyForms.get(key).add(value);
            }

        }
        catch (SQLException exception){
            throw new DAOException(exception.getMessage(),exception);
        }
        finally {
            if(conn !=null){
                ConnectionPool.getInstance().closeConnection(conn,stmt);
            }
        }
        return facultyForms;
    }

    @Override
    public void updateEducationForm(long userId, long facultyId,long educationFormId) throws DAOException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(UPDATE_EDUCATION_FORM);
            preparedStatement.setLong(1,educationFormId);
            preparedStatement.setLong(2,userId);
            preparedStatement.setLong(3,facultyId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException exception){
            throw new DAOException(exception.getMessage(),exception);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,preparedStatement);
        }
    }

    @Override
    public void deleteFacultiesByUserId(long userId) throws DAOException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(DELETE_FACULTIES_BY_USER_ID);
            preparedStatement.setLong(1,userId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException exception){
            throw new DAOException(exception.getMessage(),exception);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,preparedStatement);
        }
    }

    @Override
    public List<ApplicantEnrollment> getByUserId(long userId) throws DAOException {//TODO исключения вылетает от абстрактного класса. Здесь нужно делать новое DAO исключение и оборачивать в него старое или просто пробрасывать? А ещё этот метод можно отдельно куда-то вынести
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ApplicantEnrollment> applicantEnrollmentList = new ArrayList<>();
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(GET_TABLE_INFO_BY_USER_ID);
            stmt.setLong(1,userId);
            rs = stmt.executeQuery();

            while (rs.next()){
                int facultyId = rs.getInt(ApplicantEnrollmentFields.facultyId);
                int educationFormId = rs.getInt(ApplicantEnrollmentFields.educationFormId);
                int enrollmentStatusId = rs.getInt(ApplicantEnrollmentFields.enrollmentStatusId);
                ApplicantEnrollment applicantEnrollment = new ApplicantEnrollment();
                applicantEnrollment.setFacultyId(facultyId);
                applicantEnrollment.setEducationFormId(educationFormId);
                applicantEnrollment.setEnrollmentStatusId(enrollmentStatusId);
                applicantEnrollmentList.add(applicantEnrollment);
            }

        }
        catch (SQLException exception){
            throw new DAOException(exception.getMessage(),exception);
        }
        finally {
            if(conn !=null){
                ConnectionPool.getInstance().closeConnection(conn,stmt);
            }
        }
        return applicantEnrollmentList;
    }

    @Override
    public void updateRowByUserId(long userId, ApplicantEnrollment note) throws DAOException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(UPDATE_ENROLLMENT_STATUS);
            preparedStatement.setLong(1,note.getEnrollmentStatusId());
            preparedStatement.setLong(2,note.getUserId());
            preparedStatement.setLong(3,note.getFacultyId());
            preparedStatement.setLong(4,note.getEducationFormId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException exception){
            throw new DAOException(exception.getMessage(),exception);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,preparedStatement);
        }
    }

    @Override
    public boolean userHasFaculty(long userId, long facultyId) throws DAOException {//TODO скорее всего это бизнес логика - перенести
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;
        try{

            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(GET_FACULTY_BY_USER_ID);
            stmt.setLong(1,userId);
            stmt.setLong(2,facultyId);
            rs = stmt.executeQuery();

            if(rs.next()){//TODO check it
                result = true;
            }

        }
        catch (SQLException exception){
            throw new DAOException(exception.getMessage(),exception);
        }
        finally {
            if(conn !=null){
                ConnectionPool.getInstance().closeConnection(conn,stmt);
            }
        }
        return result;
    }

}
