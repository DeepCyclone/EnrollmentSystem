package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.tables.fields.UserFields;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.templates.UserDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.model.dto.UserCredentials;
import by.epamtc.enrollmentsystem.dao.composers.builders.UserBuilder;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public final class UserMySQL extends AbstractDAO<User> implements UserDAO {

    private static final String TABLE_NAME = TablesNames.user;

    private static final String GET_BY_LOGIN = "SELECT * FROM "+ TablesNames.user +
                                              " WHERE " + UserFields.login  +  " = ?";
    private static final String GET_PASSWORD = "SELECT " + UserFields.password +
                                                " FROM " + TablesNames.user +
                                                " WHERE " + UserFields.login + " = ?";
    private static final String INSERT_INTO = "INSERT INTO " + TablesNames.user +
                                                "("+ UserFields.login + "," + UserFields.password +
                                                "," + UserFields.email + "," + UserFields.roleId +") " +
                                                "VALUES (?,?,?,?)";

    @Override
    public Optional<User> getByID(long id) throws DAOException {
        String tableName = TablesNames.user;
        String idFieldName = UserFields.id;
        return super.getByID(tableName,idFieldName,id,new UserBuilder());
    }

    @Override
    public void insertInto(User object) throws DAOException {
        executeInsertQuery(INSERT_INTO,object.getLogin(),new String(object.getPassword(), StandardCharsets.UTF_8),
                        object.getEmail(),object.getRoleId());
    }

    @Override
    public long getRoleByLogin(String login) throws DAOException {
       return executeSingleResultQuery(GET_BY_LOGIN,new UserBuilder(),login).get().getRoleId();
    }

    @Override
    public int getRecordsNumber() throws DAOException {
        String tableName = TablesNames.user;
        return super.getNumberOfRecords(tableName);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<User> getEntitiesRange(int from, int offset) throws DAOException {
       return super.getEntitiesRange(TABLE_NAME,from,offset,new UserBuilder());
    }

    @Override
    public List<User> getAll() throws DAOException {
        String tableName = TablesNames.user;
        return super.getAll(tableName,new UserBuilder());
    }

    @Override
    public long getIdByName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateRowByID(User note) {

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
    public long getIdByLogin(String login) throws DAOException {
        String tableName = TablesNames.user;
        String nameField = UserFields.login;
        String idField = UserFields.id;
        return super.getIdByName(tableName,idField,nameField,login);
    }

    @Override
    public String getNameById(long id) throws DAOException {
        String tableName = TablesNames.user;
        String nameField = UserFields.login;
        String idField = UserFields.id;
        return super.getNameById(tableName,idField,nameField,id);
    }
}
