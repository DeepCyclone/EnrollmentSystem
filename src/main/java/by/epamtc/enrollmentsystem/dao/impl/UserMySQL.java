package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.tables.fields.UserFields;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.templates.UserTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.model.dto.UserCredentials;
import by.epamtc.enrollmentsystem.services.composers.builders.entity.UserBuilder;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.List;

public class UserMySQL extends AbstractDAO<User> implements UserTempl {
    private static final String GET_ALL_USERS = "SELECT * FROM " + TablesNames.user;
    private static final String GET_ALL_APPLICANTS = "SELECT * FROM " + TablesNames.user +
                                                     " WHERE ";
    private static final String GET_ROLE = "SELECT " + UserFields.roleId  +
                                           " FROM "+ TablesNames.user +
                                           " WHERE " + UserFields.login  +  " = ?";
    private static final String GET_PASSWORD = "SELECT " + UserFields.password +
                                                " FROM " + TablesNames.user +
                                                " WHERE " + UserFields.login + " = ?";
    private static final String INSERT_INTO = "INSERT INTO " + TablesNames.user +
                                                "("+ UserFields.login + "," + UserFields.password +
                                                "," + UserFields.email + "," + UserFields.roleId +") " +
                                                "VALUES (?,?,?,?)";

    private static final String UPDATE_NOTE = "UPDATE " + TablesNames.user +
                                              " SET ? WHERE " + UserFields.id + " = ?";


    @Override
    public User getByID(int id) throws DAOException {
        String tableName = TablesNames.user;
        String idFieldName = UserFields.id;
        return super.getByID(tableName,idFieldName,id,new UserBuilder());
    }

    @Override
    public void insertInto(User object) throws DAOException {
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_INTO);
            stmt.setString(1,object.getLogin());
            stmt.setString(2, new String(object.getPassword(), StandardCharsets.UTF_8));
            stmt.setString(3,object.getEmail());
            stmt.setInt(4,object.getRoleId());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }
    }

    @Override
    public int getRoleByLogin(String login){
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(GET_ROLE);
            stmt.setString(1,login);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("u_r_id");
        }
        catch (Exception e){

        }
        return 1;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<User> getAll() throws DAOException {
        String tableName = TablesNames.user;
        return super.getAll(tableName,new UserBuilder());
    }

    @Override
    public int getIdByName(String name) throws DAOException {
        throw new DAOException();//TODO вместо возврата норм exception
    }

    @Override
    public void updateRowByID(User note, int id) {

    }

    @Override
    public UserCredentials getCredentials(String login) throws DAOException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(GET_PASSWORD);
            stmt.setString(1,login);
            rs = stmt.executeQuery();
            String pass = null;
            while (rs.next()){
                pass = rs.getString(1);
            }
            UserCredentials userCredentials = null;
            if(null != pass) {
                userCredentials = new UserCredentials();
                userCredentials.setLogin(login);
                userCredentials.setPassword(pass);
            }
            return userCredentials;
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
        }


    }

    @Override
    public int getIdByLogin(String login) throws DAOException {
        String tableName = TablesNames.user;
        String nameField = UserFields.login;
        String idField = UserFields.id;
        return super.getIdByName(tableName,idField,nameField,login);
    }
}
