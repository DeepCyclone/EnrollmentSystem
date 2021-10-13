package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.tables.fields.UserInfoFields;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.templates.UserInfoTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.UserInfo;
import by.epamtc.enrollmentsystem.services.composers.builders.entity.UserInfoBuilder;

import java.sql.*;
import java.util.List;

public class UserInfoMySQL extends AbstractDAO<UserInfo> implements UserInfoTempl {
    private static final String tableName = TablesNames.user_info;
    private static final String INSERT_INTO = "INSERT INTO " + TablesNames.user_info +
                                "(" + UserInfoFields.userId + "," + UserInfoFields.name + "," + UserInfoFields.surname + "," +
                                      UserInfoFields.patronymic + "," + UserInfoFields.photoAddress + "," +
                                      UserInfoFields.address + "," + UserInfoFields.passport + ") " +
                                "VALUES (?,?,?,?,?,?,?)";
    private static final String GET_BY_ID = "SELECT * FROM " + TablesNames.user_info +
                                            " WHERE " + UserInfoFields.userId + " = ?";

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
    public int getIdByName(String name) throws DAOException {
        return 0;
    }

    @Override
    public UserInfo getByID(int id) throws DAOException {
        String idFieldName = UserInfoFields.userId;
        return super.getByID(tableName,idFieldName,id,new UserInfoBuilder());
    }

    @Override
    public void insertInto(UserInfo object) throws DAOException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(INSERT_INTO);
            preparedStatement.setInt(1,object.getId());
            preparedStatement.setString(2, object.getName());
            preparedStatement.setString(3, object.getSurname());
            preparedStatement.setString(4, object.getPatronymic());
            preparedStatement.setString(5, object.getPhotoPath());
            preparedStatement.setString(6, object.getAddress());
            preparedStatement.setString(7, object.getPassport());

//
//            for(int i = 1;i<=7;i++) {
//                preparedStatement.setObject(i, );
//            }

            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,preparedStatement);
        }
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void updateRowByID(UserInfo note, int id) throws DAOException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(UPDATE_NOTE);
            preparedStatement.setString(1, note.getName());
            preparedStatement.setString(2, note.getSurname());
            preparedStatement.setString(3, note.getPatronymic());
            preparedStatement.setString(4, note.getPhotoPath());
            preparedStatement.setString(5, note.getAddress());
            preparedStatement.setString(6, note.getPassport());
            preparedStatement.setInt(7,note.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,preparedStatement);
        }
    }

    @Override
    public boolean hasNoteWithId(int id) throws DAOException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            boolean result = false;
            if(rs.next()){
                result = true;
            }
            return result;
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,preparedStatement,rs);
        }
    }
}
