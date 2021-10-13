package by.epamtc.enrollmentsystem.dao;

import by.epamtc.enrollmentsystem.services.composers.builders.entity.EntityBuilder;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.utils.SQLGenerator;

import java.sql.*;
import java.util.List;

public abstract class AbstractDAO<T> implements DAOTemplate<T>{

    @Override
    public List<T> getAll(String tableName,EntityBuilder<T> entityBuilder) throws DAOException {//можно через composeSingle и composeList,но ещё один параметр передавать как-то...
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

    public abstract List<T> getAll() throws DAOException;


    @Override
    public T getByID(String tableName,String idField,int id,EntityBuilder<T> entityBuilder) throws DAOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        T entity = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            String query = SQLGenerator.generateGetByIdQuery(tableName,idField,id);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            entity = entityBuilder.singleObjectBuilder(rs,entityBuilder);
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
        }
        return entity;
    }

    public abstract int getIdByName(String name) throws DAOException;

    @Override
    public int getIdByName(String tableName,String idField,String nameField,String nameValue){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int id = 0;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.createStatement();
            String query = SQLGenerator.getIdByNameQuery(tableName, idField, nameField, nameValue);
            rs = stmt.executeQuery(query);
            while(rs.next()){
                id = rs.getInt(1);
            }
        }
        catch (SQLException e){
            //throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
        }
        return id;
    }

    public abstract T getByID(int id) throws DAOException;

    @Override
    public void insertInto(T object) throws DAOException {

    }

    @Override
    public void updateRowByID(T note, int id) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }
}
