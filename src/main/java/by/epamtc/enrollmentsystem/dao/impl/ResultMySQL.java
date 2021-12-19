package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.QueryExecutor;
import by.epamtc.enrollmentsystem.dao.composer.builders.MarkValueBuilder;
import by.epamtc.enrollmentsystem.dao.composer.builders.UserTotalResultWithFacultyBuilder;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.connectionpool.PoolException;
import by.epamtc.enrollmentsystem.dao.mapping.SchemaMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.ResultMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.SubjectMapping;
import by.epamtc.enrollmentsystem.dao.template.ResultDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Result;
import by.epamtc.enrollmentsystem.model.dto.MarkValue;
import by.epamtc.enrollmentsystem.model.dto.UserTotalResultWithFaculty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public final class ResultMySQL extends AbstractDAO<Result> implements ResultDAO {

    private static final String TABLE_NAME = SchemaMapping.result;

    public ResultMySQL(QueryExecutor<Result> executor) {
        super(executor);
    }

    private static final String GET_APPLICANT_MARKS = "SELECT s_id,s_name,r_value FROM result LEFT JOIN (SELECT s_id,s_name FROM subject) as result ON result.r_s_id = s_id WHERE r_ui_u_id = ?";
    private static final String UPDATE_USER_RESULT = "UPDATE "+ SchemaMapping.result +
                                                    " SET " + ResultMapping.resultValue + "= ?" +
                                                    " WHERE " + ResultMapping.userInfoUserId + "= ? AND " + ResultMapping.subjectId + "= ?";
    private static final String GET_USER_RESULT_ON_SUBJECT = "SELECT "+ ResultMapping.resultValue +
                                                            " FROM " + SchemaMapping.result +
                                                            " WHERE " + ResultMapping.userInfoUserId + "= ? AND " + ResultMapping.subjectId + "= ?";

    private static final String GET_TOTAL_USER_RESULTS_BY_FACULTY_AND_EDUCATION_FORM = "SELECT ae_u_id,ae_f_id,SUM(r_value) FROM applicant_enrollment" +
                                                                                      " JOIN subject_m2m_faculty on ae_f_id = smf_f_id" +
                                                                                      " JOIN result ON ae_u_id = r_ui_u_id AND smf_s_id = r_s_id" +
                                                                                      " WHERE ae_ef_id = ?" +
                                                                                      " GROUP BY ae_f_id,ae_u_id" +
                                                                                      " ORDER BY SUM(r_value) DESC";

    private static final String GET_TOTAL_USERS_RESULTS = "SELECT ae_u_id,ae_f_id,SUM(r_value),ae_priority FROM applicant_enrollment" +
                                                         " JOIN subject_m2m_faculty on ae_f_id = smf_f_id" +
                                                         " JOIN result ON ae_u_id = r_ui_u_id AND smf_s_id = r_s_id" +
                                                         " WHERE ae_ef_id = ?" +
                                                         " GROUP BY ae_u_id,ae_f_id";

    private static final String INSERT_INTO = "INSERT INTO " + SchemaMapping.result +
                                             " VALUES (?,?,?)";


    private static final String GET_RESULT_BY_USER_ID_AND_SUBJECT_NAME = "SELECT * FROM " + SchemaMapping.result +
                                                                        " JOIN " + SchemaMapping.subject + " ON " + ResultMapping.subjectId + " = " + SubjectMapping.id +
                                                                        " WHERE " + SubjectMapping.name + " = ?" +
                                                                        " AND " + ResultMapping.userInfoUserId + " = ?";

    private static final String DELETE_BY_USER_ID = "DELETE FROM " + SchemaMapping.result +
                                                    " WHERE " + ResultMapping.userInfoUserId + " = ?";

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
        executor.executeInsertQuery(INSERT_INTO,object.getSubjectId(),object.getUserInfoUserId(),object.getResultValue());
    }

    @Override
    public void updateRowByID(Result note) throws DAOException {

    }

    @Override
    public void deleteRowByID(long id) throws DAOException {

    }

    @Override
    public void deleteAll() throws DAOException {
        super.deleteAll(SchemaMapping.result);
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
    public List<MarkValue> retrieveResultsByUserId(long userID) throws DAOException {
        QueryExecutor<MarkValue> executorLocal = new QueryExecutor<>(new MarkValueBuilder());
        return executorLocal.executeSelectQuery(GET_APPLICANT_MARKS,userID);
    }

    @Override
    public void updateUserResult(Result res) throws DAOException {
        executor.executeUpdateQuery(UPDATE_USER_RESULT,res.getResultValue(),res.getUserInfoUserId(),res.getSubjectId());
    }

    @Override
    public boolean userHasMarkOnSubject(long userId,long subjectId) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
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
        catch (SQLException | PoolException exception){
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
    public List<UserTotalResultWithFaculty> getUserTotalResults(long educationFormId) throws DAOException {
        QueryExecutor<UserTotalResultWithFaculty> executorLocal = new QueryExecutor<>(new UserTotalResultWithFacultyBuilder());
        return executorLocal.executeSelectQuery(GET_TOTAL_USERS_RESULTS,educationFormId);
    }

    @Override
    public int getResultValueBySubjectName(String subjectName,long userId) throws DAOException {
        Optional<Result> result = executor.executeSingleResultQuery(GET_RESULT_BY_USER_ID_AND_SUBJECT_NAME, subjectName,userId);
        int resValue = 0;
        if(result.isPresent()){
            resValue = result.get().getResultValue();
        }
        return resValue;
    }

    @Override
    public void deleteByUserID(long userID) throws DAOException {
        executor.executeUpdateQuery(DELETE_BY_USER_ID,userID);
    }

    @Override
    public Optional<Result> getByName(String name) throws DAOException {
        return Optional.empty();
    }
}
