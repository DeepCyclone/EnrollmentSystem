package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.tables.fields.UserInfoFields;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.templates.UserInfoDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.UserInfo;
import by.epamtc.enrollmentsystem.dao.composers.builders.UserInfoBuilder;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public final class UserInfoMySQL extends AbstractDAO<UserInfo> implements UserInfoDAO {
    private static final String tableName = TablesNames.user_info;

    private static final String INSERT_INTO = "INSERT INTO " + TablesNames.user_info +
                                "(" + UserInfoFields.userId + "," + UserInfoFields.name + "," + UserInfoFields.surname + "," +
                                      UserInfoFields.patronymic + "," + UserInfoFields.photoAddress + "," +
                                      UserInfoFields.address + "," + UserInfoFields.passport + ") " +
                                "VALUES (?,?,?,?,?,?,?)";

    private static final String UPDATE_NOTE = "UPDATE " + TablesNames.user_info +
                                              " SET " + UserInfoFields.name + " = ?," + UserInfoFields.surname + " = ?," +
                                                UserInfoFields.patronymic +" = ?," + UserInfoFields.photoAddress + " = ?," +
                                                UserInfoFields.address +" = ?," + UserInfoFields.passport + " = ? " +
                                              " WHERE " + UserInfoFields.userId + " = ?";

    @Override
    public List<UserInfo> getAll() throws DAOException {
        return super.getAll(tableName,new UserInfoBuilder());
    }

    @Override
    public long getIdByName(String name) throws DAOException {
        return 0;
    }

    @Override
    public Optional<UserInfo> getByID(long id) throws DAOException {
        String idFieldName = UserInfoFields.userId;
        return super.getByID(tableName,idFieldName,id,new UserInfoBuilder());
    }

    @Override
    public void insertInto(UserInfo object) throws DAOException {
        executeInsertQuery(INSERT_INTO,object.getId(),object.getName(),
                        object.getSurname(),object.getPatronymic(),object.getPhotoPath(),
                        object.getAddress(),object.getPassport());
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<UserInfo> getEntitiesRange(int from, int offset) throws DAOException {
        return null;
    }

    @Override
    public void updateRowByID(UserInfo note) throws DAOException {
        executeUpdateQuery(UPDATE_NOTE,note.getName(),note.getSurname(),note.getPatronymic(),
                        note.getPhotoPath(),note.getAddress(),note.getPassport(),note.getId());
    }

    @Override
    public String getNameById(long userId) throws DAOException {
//        executeSingleResultQuery().get().getName();
        String nameField = UserInfoFields.name;
        String idField = UserInfoFields.userId;
        return super.getNameById(tableName,idField,nameField,userId);
    }
}
