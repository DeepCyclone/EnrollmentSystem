package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.tables.fields.ApplicantEnrollmentFields;
import by.epamtc.enrollmentsystem.dao.tables.fields.ResultFields;
import by.epamtc.enrollmentsystem.dao.tables.fields.SubjectFields;
import by.epamtc.enrollmentsystem.dao.templates.ResultDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Result;
import by.epamtc.enrollmentsystem.model.dto.MarkValue;
import by.epamtc.enrollmentsystem.model.dto.UserResultByFaculty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ResultMySQL extends AbstractDAO<Result> implements ResultDAO {

    private static final String GET_APPLICANT_MARKS = "SELECT s_id,s_name,r_value FROM subject LEFT JOIN (SELECT r_s_id,r_value FROM result WHERE r_ui_u_id = ?) as result ON result.r_s_id = s_id";
    private static final String UPDATE_USER_RESULT = "UPDATE "+ TablesNames.result +
                                                    " SET " + ResultFields.resultValue + "= ?" +
                                                    " WHERE " + ResultFields.userInfoUserId + "= ? AND " + ResultFields.subjectId + "= ?";
    private static final String GET_USER_RESULT_ON_SUBJECT = "SELECT "+ ResultFields.resultValue +
                                                            " FROM " + TablesNames.result +
                                                            " WHERE " + ResultFields.userInfoUserId + "= ? AND " + ResultFields.subjectId + "= ?";

    private static final String GET_TOTAL_USER_RESULTS_BY_FACULTY_AND_EDUCATION_FORM = "SELECT ae_u_id,ae_f_id,SUM(r_value) FROM applicant_enrollment" +
                                                                                      " JOIN subject_m2m_faculty on ae_f_id = smf_f_id" +
                                                                                      " JOIN result ON ae_u_id = r_ui_u_id AND smf_s_id = r_s_id" +
                                                                                      " WHERE ae_ef_id = ?" +
                                                                                      " GROUP BY ae_f_id,ae_u_id" +
                                                                                      " ORDER BY SUM(r_value) DESC";
    private static final String INSERT_INTO = "INSERT INTO " + TablesNames.result +
                                             " VALUES (?,?,?)";


    @Override
    public List<Result> getAll() throws DAOException {
        return null;
    }

    @Override
    public int getIdByName(String name) throws DAOException {
        return 0;
    }

    @Override
    public Result getByID(long id) throws DAOException {
        return null;
    }

    @Override
    public void insertInto(Result object) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(INSERT_INTO);
            stmt.setLong(1,object.getSubjectId());
            stmt.setLong(2,object.getUserInfoUserId());
            stmt.setInt(3,object.getResultValue());
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
    public void updateRowByID(Result note, long id) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<MarkValue> retrieveResultByUserId(long userID) throws DAOException {//TODO это куда-то перенести
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<MarkValue> mv = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(GET_APPLICANT_MARKS);
            stmt.setLong(1,userID);
            rs = stmt.executeQuery();
            mv = new ArrayList<>();
            while(rs.next()) {
                int subjectId = rs.getInt(SubjectFields.id);
                String subjectName = rs.getString(SubjectFields.name);
                int resultValue = rs.getInt(ResultFields.resultValue);
                mv.add(new MarkValue(subjectId,subjectName,resultValue));
            }
        }
        catch (SQLException exception){
            throw new DAOException(exception.getMessage(),exception);
        }
        finally {
            if(conn !=null){
                ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
            }
        }
        return mv;
    }

    @Override
    public void updateUserResult(Result res) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(UPDATE_USER_RESULT);
            stmt.setInt(1,res.getResultValue());
            stmt.setLong(2,res.getUserInfoUserId());
            stmt.setLong(3,res.getSubjectId());
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
    public boolean userHasMarkOnSubject(long userId,long subjectId) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(GET_USER_RESULT_ON_SUBJECT);
            stmt.setLong(1,userId);
            stmt.setLong(2,subjectId);
            rs = stmt.executeQuery();
            if(rs.next()){
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

    @Override
    public List<UserResultByFaculty> getUserResultByFacultyAndEduForm(long educationFormId) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<UserResultByFaculty> userResults = new LinkedList<>();
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(GET_TOTAL_USER_RESULTS_BY_FACULTY_AND_EDUCATION_FORM);
            stmt.setLong(1,educationFormId);
            rs = stmt.executeQuery();
            while(rs.next()) {
                UserResultByFaculty result = new UserResultByFaculty();
                result.setUserId(rs.getInt(ApplicantEnrollmentFields.userId));
                result.setFacultyId(rs.getInt(ApplicantEnrollmentFields.facultyId));
                result.setResult(rs.getInt(3));//TODO отметить сумму в запросе через AS
                userResults.add(result);
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
        return userResults;
    }

    @Override
    public String getNameById(long id) {
        throw new UnsupportedOperationException();
    }
}
