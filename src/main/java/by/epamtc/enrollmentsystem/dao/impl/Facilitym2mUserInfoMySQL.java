package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.QueryExecutor;
import by.epamtc.enrollmentsystem.dao.composer.builders.StringBuilder;
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
import java.util.Optional;

public final class Facilitym2mUserInfoMySQL extends AbstractDAO<Facilitym2mUserInfo> implements Facilitym2mUserInfoDAO {

    public Facilitym2mUserInfoMySQL(QueryExecutor<Facilitym2mUserInfo> executor) {
        super(executor);
    }

    private static final String TABLE_NAME = SchemaMapping.facility_m2m_user_info;

    private static final String INSERT_INTO = "INSERT INTO " + SchemaMapping.facility_m2m_user_info + " VALUES(?,?)";

    private static final String GET_USER_FACILITIES_NAMES = "SELECT " + FacilityMapping.name + " FROM " + SchemaMapping.facility +
                                                           " JOIN " + SchemaMapping.facility_m2m_user_info +
                                                           " ON " +  FacilityMapping.id + "=" + Facilitym2mUserInfoMapping.facilityId +
                                                           " WHERE " + Facilitym2mUserInfoMapping.userInfoUserId + " = ?";

    private static final String GET_BY_USER_ID = "SELECT * FROM " + SchemaMapping.facility_m2m_user_info +
                                                " WHERE " + Facilitym2mUserInfoMapping.userInfoUserId + " = ?";


    @Override
    public List<Facilitym2mUserInfo> getAll() throws DAOException {
        return super.getAll(TABLE_NAME);
    }

    @Override
    public void insertInto(Facilitym2mUserInfo object) throws DAOException {
        executor.executeInsertQuery(INSERT_INTO,object.getFacilityId(),object.getUserInfoUserId());
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
    public List<String> getUserFacilitiesNames(long userId) throws DAOException {
        QueryExecutor<String> executorLocal = new QueryExecutor<String>(new StringBuilder());
        return executorLocal.executeSelectQuery(GET_USER_FACILITIES_NAMES,userId);
    }

    @Override
    public List<Facilitym2mUserInfo> getByUserId(long userId) throws DAOException {
        return executor.executeSelectQuery(GET_BY_USER_ID,userId);
    }

}
