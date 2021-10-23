package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.interfaces.DAOTemplate;
import by.epamtc.enrollmentsystem.dao.QueryExecutor;
import by.epamtc.enrollmentsystem.dao.composers.builders.EntityBuilder;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.utils.QueryGenerator;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T> extends QueryExecutor<T> implements DAOTemplate<T> {

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

    protected final void deleteAll(String tableName) {

    }

    protected final List<T> getEntitiesRange(String tableName,int from,int offset,EntityBuilder<T> entityBuilder) throws DAOException{
        String query = SELECT_ALL + tableName + " LIMIT ?,?";
        return executeSelectQuery(query,entityBuilder,from,offset);
    }

    protected final int getNumberOfRecords(String tableName) throws DAOException {//служебный метод, чтобы не считать элементы List

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
