package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.QueryExecutor;
import by.epamtc.enrollmentsystem.dao.mapping.fields.UserInfoMapping;
import by.epamtc.enrollmentsystem.dao.mapping.SchemaMapping;
import by.epamtc.enrollmentsystem.dao.template.UserInfoDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.UserInfo;
import by.epamtc.enrollmentsystem.dao.composer.builders.UserInfoBuilder;

import java.util.List;
import java.util.Optional;

public final class UserInfoMySQL extends AbstractDAO<UserInfo> implements UserInfoDAO {

    public UserInfoMySQL(QueryExecutor<UserInfo> executor) {
        super(executor);
    }


    private static final String TABLE_NAME = SchemaMapping.user_info;

    private static final String INSERT_INTO = "INSERT INTO " + SchemaMapping.user_info +
                                             " VALUES (?,?,?,?,?,?,?)";

    private static final String UPDATE_NOTE = "UPDATE " + SchemaMapping.user_info +
                                              " SET " + UserInfoMapping.name + " = ?," + UserInfoMapping.surname + " = ?," +
                                                UserInfoMapping.patronymic +" = ?," + UserInfoMapping.photoAddress + " = ?," +
                                                UserInfoMapping.address +" = ?," + UserInfoMapping.passport + " = ? " +
                                              " WHERE " + UserInfoMapping.userId + " = ?";

    private static final String GET_BY_USERNAME = "SELECT * FROM " + SchemaMapping.user_info +
                                                 " WHERE " + UserInfoMapping.name + " = ?";

    @Override
    public List<UserInfo> getAll() throws DAOException {
        return super.getAll(TABLE_NAME);
    }

    @Override
    public Optional<UserInfo> getByID(long id) throws DAOException {
        String idFieldName = UserInfoMapping.userId;
        return super.getByID(TABLE_NAME,idFieldName,id);
    }

    @Override
    public void insertInto(UserInfo object) throws DAOException {
        executor.executeInsertQuery(INSERT_INTO,object.getId(),object.getName(),
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
        executor.executeUpdateQuery(UPDATE_NOTE,note.getName(),note.getSurname(),note.getPatronymic(),
                        note.getPhotoPath(),note.getAddress(),note.getPassport(),note.getId());
    }

    @Override
    public void deleteRowByID(long id) throws DAOException {

    }

    @Override
    public Optional<UserInfo> getByUserName(String userName) throws DAOException {
        return executor.executeSingleResultQuery(GET_BY_USERNAME,userName);
    }
}
