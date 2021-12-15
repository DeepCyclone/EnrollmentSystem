package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.QueryExecutor;
import by.epamtc.enrollmentsystem.dao.mapping.fields.UserMapping;
import by.epamtc.enrollmentsystem.dao.mapping.SchemaMapping;
import by.epamtc.enrollmentsystem.dao.template.UserDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.dao.composer.builders.UserBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public final class UserMySQL extends AbstractDAO<User> implements UserDAO {

    public UserMySQL(QueryExecutor<User> executor) {
        super(executor);
    }

    private static final String TABLE_NAME = SchemaMapping.user;

    private static final String GET_BY_LOGIN = "SELECT * FROM "+ SchemaMapping.user +
                                              " WHERE " + UserMapping.login  +  " = ?";
    private static final String INSERT_INTO = "INSERT INTO " + SchemaMapping.user +
                                              "(" + UserMapping.login + "," + UserMapping.password +
                                                 "," + UserMapping.email + "," + UserMapping.roleId + ")" +
                                             " VALUES (?,?,?,?)";

    @Override
    public Optional<User> getByID(long id) throws DAOException {
        String tableName = SchemaMapping.user;
        String idFieldName = UserMapping.id;
        return super.getByID(tableName,idFieldName,id);
    }

    @Override
    public void insertInto(User object) throws DAOException {
        executor.executeInsertQuery(INSERT_INTO,object.getLogin(),new String(object.getPassword(), StandardCharsets.UTF_8),
                        object.getEmail(),object.getRoleId());
    }

    @Override
    public int getNumberOfRecords() throws DAOException {
        String tableName = SchemaMapping.user;
        return super.getNumberOfRecords(tableName);
    }

    @Override
    public Optional<User> getByLogin(String login) throws DAOException {
        return executor.executeSingleResultQuery(GET_BY_LOGIN,login);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<User> getEntitiesRange(int from, int offset) throws DAOException {
       return super.getEntitiesRange(TABLE_NAME,from,offset);
    }

    @Override
    public List<User> getAll() throws DAOException {
        return super.getAll(TABLE_NAME);
    }

    @Override
    public void updateRowByID(User note) {

    }

    @Override
    public void deleteRowByID(long id) throws DAOException {
        super.deleteByID(TABLE_NAME,UserMapping.id,id);
    }

}
