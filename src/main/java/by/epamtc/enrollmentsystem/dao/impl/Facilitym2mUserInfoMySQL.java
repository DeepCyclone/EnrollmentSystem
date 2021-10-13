package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.templates.Facilitym2mUserInfoTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Facilitym2mUserInfo;
import by.epamtc.enrollmentsystem.services.composers.builders.entity.Facilitym2mUserInfoBuilder;
import by.epamtc.enrollmentsystem.utils.SQLGenerator;

import java.sql.*;
import java.util.List;

public class Facilitym2mUserInfoMySQL extends AbstractDAO<Facilitym2mUserInfo> implements Facilitym2mUserInfoTempl {

    private static final String INSERT_INTO = "INSERT INTO " + TablesNames.facility_m2m_user_info + " VALUES(?,?)";
    @Override
    public List<Facilitym2mUserInfo> getAll() throws DAOException {
        String tableName = TablesNames.facility_m2m_user_info;
        return super.getAll(tableName,new Facilitym2mUserInfoBuilder());
    }

    @Override
    public int getIdByName(String name) throws DAOException {
        return 0;
    }

    @Override
    public Facilitym2mUserInfo getByID(int id) throws DAOException {
        return null;
    }

    @Override
    public void insertInto(Facilitym2mUserInfo object){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(INSERT_INTO);
            stmt.setInt(1,object.getFacilityId());
            stmt.setInt(2,object.getUserInfoUserId());
            stmt.executeUpdate();
        }
        catch (SQLException e){
//            throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,stmt);
        }
    }
}
