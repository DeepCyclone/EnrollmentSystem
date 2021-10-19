package by.epamtc.enrollmentsystem.dao;

import by.epamtc.enrollmentsystem.dao.composers.builders.entity.EntityBuilder;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.utils.SQLGenerator;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T> extends QueryExecutor<T> implements DAOTemplate<T>{

    private static final String SELECT_ALL = "SELECT * FROM ";

    private static final String GET_NUMBER_OF_RECORDS = "SELECT COUNT(*) FROM ";

    protected List<T> getAll(String tableName, EntityBuilder<T> entityBuilder) throws DAOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<T> entities = null;
        String selectQuery = SQLGenerator.generateSelectAllQuery(tableName);
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            entities = entityBuilder.buildObjectsList(rs,entityBuilder);
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
        }
        return entities;
    }

    protected Optional<T> getByID(String tableName, String idField, long id, EntityBuilder<T> entityBuilder) throws DAOException {
        String query = SQLGenerator.generateGetByIdPreparedQuery(tableName,idField);
        return executeSingleResultQuery(query,entityBuilder,id);
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        T entity = null;
//        try{
//            conn = ConnectionPool.getInstance().getConnection();
//
//            stmt = conn.prepareStatement(query);
//            stmt.setLong(1,id);
//            rs = stmt.executeQuery(query);
//            entity = entityBuilder.singleObjectBuilder(rs,entityBuilder);
//        }
//        catch (SQLException e){
//            throw new DAOException(e.getMessage(),e);
//        }
//        finally {
//            ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
//        }
//        return entity;
    }

//    public abstract int getIdByName(String name) throws DAOException;

    protected int getIdByName(String tableName,String idField,String nameField,String nameValue) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            String query = SQLGenerator.getIdByNamePreparedQuery(tableName, idField, nameField);
            stmt = conn.prepareStatement(query);
            stmt.setString(1,nameValue);
            rs = stmt.executeQuery(query);
            while(rs.next()){
                id = rs.getInt(1);
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

    protected String getNameById(String tableName,String idField,String nameField,long idValue) throws DAOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String name = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.createStatement();
            String query = SQLGenerator.getNameByIdQuery(tableName, idField, nameField, idValue);
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

//    public abstract T getByID(int id) throws DAOException;

    @Override
    public void insertInto(T object) throws DAOException {

    }

    @Override
    public void updateRowByID(T note, long id) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public int getNumberOfRecords(String tableName) throws DAOException {
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
