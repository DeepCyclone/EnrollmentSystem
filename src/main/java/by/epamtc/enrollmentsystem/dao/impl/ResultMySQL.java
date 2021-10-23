package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.composers.builders.ResultBuilder;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.tables.fields.ApplicantEnrollmentFields;
import by.epamtc.enrollmentsystem.dao.tables.fields.ResultFields;
import by.epamtc.enrollmentsystem.dao.tables.fields.SubjectFields;
import by.epamtc.enrollmentsystem.dao.interfaces.ResultDAO;
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
import java.util.Optional;

public final class ResultMySQL extends AbstractDAO<Result> implements ResultDAO {

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


    private static final String GET_RESULT_BY_USER_ID_AND_SUBJECT_NAME = "SELECT * FROM " + TablesNames.result +
                                                                        " JOIN " + TablesNames.subject + " ON " + ResultFields.subjectId + " = " + SubjectFields.id +
                                                                        " WHERE " + SubjectFields.name + " = ?" +
                                                                        " AND " + ResultFields.userInfoUserId + " = ?";


    @Override
    public List<Result> getAll() throws DAOException {
        return null;
    }

    @Override
    public Optional<Result> getByID(long id) throws DAOException {
        return null;
    }

    @Override
    public void insertInto(Result object) throws DAOException {
        executeInsertQuery(INSERT_INTO,object.getSubjectId(),object.getUserInfoUserId(),object.getResultValue());
    }

    @Override
    public void updateRowByID(Result note) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public int getNumberOfRecords() throws DAOException {
        return 0;
    }

    @Override
    public List<Result> getEntitiesRange(int from, int offset) throws DAOException {
        return null;
    }

    @Override
    public List<MarkValue> retrieveResultsByUserId(long userID) throws DAOException {//TODO это куда-то перенести
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
        executeUpdateQuery(UPDATE_USER_RESULT,res.getResultValue(),res.getUserInfoUserId(),res.getSubjectId());
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
    public int getResultValueBySubjectName(String subjectName,long userId) throws DAOException {
        Optional<Result> result = executeSingleResultQuery(GET_RESULT_BY_USER_ID_AND_SUBJECT_NAME, new ResultBuilder(), subjectName,userId);//TODO return
        int resValue = 0;
        if(result.isPresent()){
            resValue = result.get().getResultValue();
        }
        return resValue;
    }

    @Override
    public Optional<Result> getByName(String name) throws DAOException {
        return Optional.empty();
    }
}
