package by.epamtc.enrollmentsystem.dao;

import by.epamtc.enrollmentsystem.dao.composers.builders.EntityBuilder;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.utils.QueryGenerator;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T> extends QueryExecutor<T> implements DAOTemplate<T>{

    private static final String SELECT_ALL = "SELECT * FROM ";

    private static final String GET_NUMBER_OF_RECORDS = "SELECT COUNT(*) FROM ";

    protected final List<T> getAll(String tableName, EntityBuilder<T> entityBuilder) throws DAOException {
        String query = SELECT_ALL + tableName;
        return executeSelectQuery(query,entityBuilder);
    }

    protected final Optional<T> getByID(String tableName, String idField, long id, EntityBuilder<T> entityBuilder) throws DAOException {
        String query = QueryGenerator.generateGetByIdPreparedQuery(tableName, idField);
        return executeSingleResultQuery(query, entityBuilder, id);
    }

    protected final long getIdByName(String tableName,String idField,String nameField,String nameValue) throws DAOException {
//        String query = SQLGenerator.getIdByNamePreparedQuery(tableName, idField, nameField);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long id = 0;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            String query = QueryGenerator.getIdByNamePreparedQuery(tableName, idField, nameField);
            stmt = conn.prepareStatement(query);
            stmt.setObject(1,nameValue);
            rs = stmt.executeQuery();
            while(rs.next()){
                id = rs.getLong(1);
            }
        }
        catch (SQLException e){
             throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
        }
        return id;
    }

    protected final String getNameById(String tableName,String idField,String nameField,long idValue) throws DAOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String name = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.createStatement();
            String query = QueryGenerator.getNameByIdQuery(tableName, idField, nameField, idValue);
            rs = stmt.executeQuery(query);
            while(rs.next()){
                name = rs.getString(nameField);
            }
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
        }
        return name;
    }

    public void insertInto(String query,Object... params) throws DAOException {

        //executeInsertQuery(query, params);
    }

    public void insertInto() throws DAOException{}

    @Override
    public void updateRowByID(T note) throws DAOException {
//        executeUpdateQuery();
    }

    @Override
    public void deleteAll() {

    }

    protected final List<T> getEntitiesRange(String tableName,int from,int offset,EntityBuilder<T> entityBuilder) throws DAOException{
        String query = SELECT_ALL + tableName + " LIMIT ?,?";
        return executeSelectQuery(query,entityBuilder,from,offset);
    }

    @Override
    public int getNumberOfRecords(String tableName) throws DAOException {//служебный метод, чтобы не считать элементы List

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int records = 0;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            String query = GET_NUMBER_OF_RECORDS + tableName;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
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
