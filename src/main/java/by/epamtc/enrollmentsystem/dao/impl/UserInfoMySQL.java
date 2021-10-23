package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.tables.fields.UserInfoFields;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.interfaces.UserInfoDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.UserInfo;
import by.epamtc.enrollmentsystem.dao.composers.builders.UserInfoBuilder;

import java.util.List;
import java.util.Optional;

public final class UserInfoMySQL extends AbstractDAO<UserInfo> implements UserInfoDAO {
    private static final String tableName = TablesNames.user_info;

    private static final String INSERT_INTO = "INSERT INTO " + TablesNames.user_info +
                                             " VALUES (?,?,?,?,?,?,?)";

    private static final String UPDATE_NOTE = "UPDATE " + TablesNames.user_info +
                                              " SET " + UserInfoFields.name + " = ?," + UserInfoFields.surname + " = ?," +
                                                UserInfoFields.patronymic +" = ?," + UserInfoFields.photoAddress + " = ?," +
                                                UserInfoFields.address +" = ?," + UserInfoFields.passport + " = ? " +
                                              " WHERE " + UserInfoFields.userId + " = ?";

    private static final String GET_BY_USERNAME = "SELECT * FROM " + TablesNames.user_info +
                                                 " WHERE " + UserInfoFields.name + " = ?";

    @Override
    public List<UserInfo> getAll() throws DAOException {
        return super.getAll(tableName,new UserInfoBuilder());
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
    public int getNumberOfRecords() throws DAOException {
        return 0;
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
    public Optional<UserInfo> getByUserName(String userName) throws DAOException {
        return executeSingleResultQuery(GET_BY_USERNAME,new UserInfoBuilder(),userName);
    }
}
