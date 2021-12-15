package by.epamtc.enrollmentsystem.dao;

import by.epamtc.enrollmentsystem.dao.composer.builders.EntityBuilder;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.connectionpool.PoolException;
import by.epamtc.enrollmentsystem.exception.DAOException;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/*
 * A generic class created to reduce duplicating code and create a unified way to build entities
 * @author Flexus
 */

public class QueryExecutor<T> {

    private EntityBuilder<T> builder;
    public QueryExecutor(EntityBuilder<T> builder){
        this.builder = builder;
    }

    public List<T> executeSelectQuery(String query, Object... params) throws DAOException {
        List<T> entities;
        try (PreparedStatement statement = createPreparedStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
             entities = builder.buildObjectsList(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return entities;
    }

    public Optional<T> executeSingleResultQuery(String query,Object... params) throws DAOException {
        List<T> entities = executeSelectQuery(query, params);
        if(entities.isEmpty() || entities.size() < 1){
            return Optional.empty();
        }
        return Optional.ofNullable(entities.get(0));
    }

    public void executeInsertQuery(String query, Object... params) throws DAOException {
        executeUpdateQuery(query,params);
    }

    public void executeUpdateQuery(String query, Object... params) throws DAOException {
        try (PreparedStatement statement = createPreparedStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    private PreparedStatement createPreparedStatement(String query, Object... params) throws DAOException {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; ++i) {
                preparedStatement.setObject(i+1, params[i]);
            }
            ConnectionPool.getInstance().returnConnection(connection);
            return preparedStatement;
        } catch (SQLException | PoolException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }



}
