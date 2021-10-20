package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.tables.fields.FacilityFields;
import by.epamtc.enrollmentsystem.dao.tables.fields.Facilitym2mUserInfoFields;
import by.epamtc.enrollmentsystem.dao.templates.Facilitym2mUserInfoDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Facilitym2mUserInfo;
import by.epamtc.enrollmentsystem.dao.composers.builders.Facilitym2mUserInfoBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Facilitym2mUserInfoMySQL extends AbstractDAO<Facilitym2mUserInfo> implements Facilitym2mUserInfoDAO {

    private static final String INSERT_INTO = "INSERT INTO " + TablesNames.facility_m2m_user_info + " VALUES(?,?)";
    private static final String GET_USER_FACILITIES_NAMES = "SELECT " + FacilityFields.name + " FROM " + TablesNames.facility +
                                                           " JOIN " + TablesNames.facility_m2m_user_info +
                                                           " ON " +  FacilityFields.id + "=" + Facilitym2mUserInfoFields.facilityId +
                                                           " WHERE " + Facilitym2mUserInfoFields.userInfoUserId + " = ?";
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
    public Optional<Facilitym2mUserInfo> getByID(long id) throws DAOException {
        return null;
    }

    @Override
    public void insertInto(Facilitym2mUserInfo object) throws DAOException {
        executeInsertQuery(INSERT_INTO,object.getFacilityId(),object.getUserInfoUserId());
    }

    @Override
    public void updateRowByID(Facilitym2mUserInfo note) throws DAOException {

    }

    @Override
    public List<String> getUserFacilitiesNames(long userId) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> existingFacilitiesNames = null;
        try{
            stmt.setLong(1,userId);
            rs = stmt.executeQuery();
            existingFacilitiesNames = new ArrayList<>();
            while(rs.next()) {
                existingFacilitiesNames.add(rs.getString(FacilityFields.name));
            }
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,stmt);
        }
        return existingFacilitiesNames;
    }

    @Override
    public String getNameById(long id) {
        throw new UnsupportedOperationException();
    }
}
