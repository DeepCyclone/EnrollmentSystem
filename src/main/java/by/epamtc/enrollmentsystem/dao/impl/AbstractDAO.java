package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.connectionpool.PoolException;
import by.epamtc.enrollmentsystem.dao.template.DAOTemplate;
import by.epamtc.enrollmentsystem.dao.QueryExecutor;
import by.epamtc.enrollmentsystem.dao.composer.builders.EntityBuilder;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.service.util.QueryGenerator;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/*
 * Basic and auxiliary class at the same time.
 * Provides a full implementation of methods, contains queries
 * A class that extends this class may don't worry about nuances of queries when any method called
 */

public abstract class AbstractDAO<T> extends QueryExecutor<T> implements DAOTemplate<T> {

    private static final String SELECT_ALL = "SELECT * FROM ";

    private static final String DELETE_ALL = "DELETE FROM ";

    private static final String GET_NUMBER_OF_RECORDS = "SELECT COUNT(*) FROM ";

    protected final List<T> getAll(String tableName, EntityBuilder<T> entityBuilder) throws DAOException {
        String query = SELECT_ALL + tableName;
        return executeSelectQuery(query,entityBuilder);
    }

    protected final Optional<T> getByID(String tableName, String idField, long id, EntityBuilder<T> entityBuilder) throws DAOException {
        String query = QueryGenerator.generateGetByIdPreparedQuery(tableName, idField);
        return executeSingleResultQuery(query, entityBuilder, id);
    }

    protected final Optional<T> getByName(String tableName,String nameField,String name,EntityBuilder<T> entityBuilder) throws DAOException{
        String query = QueryGenerator.getByNamePreparedQuery(tableName,nameField);
        return executeSingleResultQuery(query,entityBuilder,name);
    }

    protected final void deleteAll(String tableName) throws DAOException {
        String query = DELETE_ALL + tableName;
        executeUpdateQuery(query);
    }

    protected final void deleteByID(String tableName,String idField,long id) throws DAOException {
        String query = DELETE_ALL + tableName + " WHERE " + idField + " = " + id;
        executeUpdateQuery(query);
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
        catch (SQLException | PoolException e){
            throw new DAOException(e.getMessage(),e);
        }
        finally {
            if(conn != null) {
                ConnectionPool.getInstance().closeConnection(conn, stmt, rs);
            }
        }
        return records;
    }
}
