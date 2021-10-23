package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.tables.fields.UserFields;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.interfaces.UserDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.dao.composers.builders.UserBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public final class UserMySQL extends AbstractDAO<User> implements UserDAO {

    private static final String TABLE_NAME = TablesNames.user;

    private static final String GET_BY_LOGIN = "SELECT * FROM "+ TablesNames.user +
                                              " WHERE " + UserFields.login  +  " = ?";
    private static final String INSERT_INTO = "INSERT INTO " + TablesNames.user +
                                             " VALUES (?,?,?,?)";

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
    public int getNumberOfRecords() throws DAOException {
        String tableName = TablesNames.user;
        return super.getNumberOfRecords(tableName);
    }

    @Override
    public Optional<User> getByLogin(String login) throws DAOException {
        return executeSingleResultQuery(GET_BY_LOGIN,new UserBuilder(),login);
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
    public void updateRowByID(User note) {

    }

}
