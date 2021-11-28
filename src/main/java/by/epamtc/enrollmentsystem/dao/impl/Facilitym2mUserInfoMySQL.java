package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.connectionpool.PoolException;
import by.epamtc.enrollmentsystem.dao.mapping.SchemaMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.FacilityMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.Facilitym2mUserInfoMapping;
import by.epamtc.enrollmentsystem.dao.template.Facilitym2mUserInfoDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Facilitym2mUserInfo;
import by.epamtc.enrollmentsystem.dao.composer.builders.Facilitym2mUserInfoBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class Facilitym2mUserInfoMySQL extends AbstractDAO<Facilitym2mUserInfo> implements Facilitym2mUserInfoDAO {

    private static final String tableName = SchemaMapping.facility_m2m_user_info;

    private static final String INSERT_INTO = "INSERT INTO " + SchemaMapping.facility_m2m_user_info + " VALUES(?,?)";

    private static final String GET_USER_FACILITIES_NAMES = "SELECT " + FacilityMapping.name + " FROM " + SchemaMapping.facility +
                                                           " JOIN " + SchemaMapping.facility_m2m_user_info +
                                                           " ON " +  FacilityMapping.id + "=" + Facilitym2mUserInfoMapping.facilityId +
                                                           " WHERE " + Facilitym2mUserInfoMapping.userInfoUserId + " = ?";

    private static final String GET_BY_USER_ID = "SELECT * FROM " + SchemaMapping.facility_m2m_user_info +
                                                " WHERE " + Facilitym2mUserInfoMapping.userInfoUserId + " = ?";


    @Override
    public List<Facilitym2mUserInfo> getAll() throws DAOException {
        return super.getAll(tableName,new Facilitym2mUserInfoBuilder());
    }

    @Override
    public void insertInto(Facilitym2mUserInfo object) throws DAOException {
        executeInsertQuery(INSERT_INTO,object.getFacilityId(),object.getUserInfoUserId());
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public int getNumberOfRecords() throws DAOException {
        return 0;
    }


    @Override
    public List<Facilitym2mUserInfo> getEntitiesRange(int from, int offset) throws DAOException {
        return null;
    }

    @Override
    public List<String> getUserFacilitiesNames(long userId) throws DAOException {//TODO починить + сервис
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> existingFacilitiesNames = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            conn.prepareStatement(GET_USER_FACILITIES_NAMES);
            stmt.setLong(1,userId);
            rs = stmt.executeQuery();
            existingFacilitiesNames = new ArrayList<>();
            while(rs.next()) {
                existingFacilitiesNames.add(rs.getString(FacilityMapping.name));
            }
        }
        catch (SQLException | PoolException e){
            throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,stmt);
        }
        return existingFacilitiesNames;
    }

    @Override
    public List<Facilitym2mUserInfo> getByUserId(long userId) throws DAOException {
        return executeSelectQuery(GET_BY_USER_ID,new Facilitym2mUserInfoBuilder(),userId);
    }

}
