package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.tables.fields.ResultFields;
import by.epamtc.enrollmentsystem.dao.tables.fields.SubjectFields;
import by.epamtc.enrollmentsystem.dao.templates.ResultTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Result;
import by.epamtc.enrollmentsystem.model.Subject;
import by.epamtc.enrollmentsystem.model.dto.MarkValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResultMySQL extends AbstractDAO<Result> implements ResultTempl {

    private static final String GET_APPLICANT_MARKS = "SELECT s_id,s_name,r_value FROM subject LEFT JOIN (SELECT r_s_id,r_value FROM result WHERE r_ui_u_id = ?) as result ON result.r_s_id = s_id";
    private static final String UPDATE_USER_RESULT = "UPDATE "+ TablesNames.result +
                                                    " SET " + ResultFields.resultValue + "= ?" +
                                                    " WHERE " + ResultFields.userInfoUserId + "= ? AND " + ResultFields.subjectId + "= ?";
    private static final String GET_USER_RESULT_ON_SUBJECT = "SELECT "+ ResultFields.resultValue +
                                                            " FROM " + TablesNames.result +
                                                            " WHERE " + ResultFields.userInfoUserId + "= ? AND " + ResultFields.subjectId + "= ?";
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
    public Result getByID(int id) throws DAOException {
        return null;
    }

    @Override
    public void insertInto(Result object){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(INSERT_INTO);
            stmt.setInt(1,object.getSubjectId());
            stmt.setInt(2,object.getUserInfoUserId());
            stmt.setInt(3,object.getResultValue());
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
    public void updateRowByID(Result note, int id) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<MarkValue> retrieveResultByUserId(int userID) {//TODO это куда-то перенести
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<MarkValue> mv = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(GET_APPLICANT_MARKS);
            stmt.setInt(1,userID);
            rs = stmt.executeQuery();
            mv = new ArrayList<>();
            while(rs.next()) {
                int subjectId = rs.getInt(SubjectFields.id);
                String subjectName = rs.getString(SubjectFields.name);
                int resultValue = rs.getInt(ResultFields.resultValue);
                mv.add(new MarkValue(subjectId,subjectName,resultValue));
            }
        }
        catch (Exception ignored){

        }
        finally {
            if(conn !=null){
                ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
            }
        }
        return mv;
    }

    @Override
    public void updateUserResult(Result res) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(UPDATE_USER_RESULT);
            stmt.setInt(1,res.getResultValue());
            stmt.setInt(2,res.getUserInfoUserId());
            stmt.setInt(3,res.getSubjectId());
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
    public boolean userHasMarkOnSubject(int userId,int subjectId){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(GET_USER_RESULT_ON_SUBJECT);
            stmt.setInt(1,userId);
            stmt.setInt(2,subjectId);
            rs = stmt.executeQuery();
            if(rs.next()){
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
