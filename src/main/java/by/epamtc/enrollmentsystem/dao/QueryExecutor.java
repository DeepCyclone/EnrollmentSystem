package by.epamtc.enrollmentsystem.dao;

import by.epamtc.enrollmentsystem.dao.composers.builders.entity.EntityBuilder;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class QueryExecutor<T> {

    protected List<T> executeSelectQuery(String query, EntityBuilder<T> builder ,Object... params) throws DAOException {
        List<T> entities;
        try (PreparedStatement statement = createPreparedStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
             entities = builder.buildObjectsList(resultSet,builder);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return entities;
    }

    protected Optional<T> executeSingleResultQuery(String query,EntityBuilder<T> builder , Object... params) throws DAOException {
        List<T> entities = executeSelectQuery(query,builder, params);

        if (entities.size() <= 1) {
            return Optional.empty();
        }

        return Optional.of(entities.get(0));
    }

    protected long executeInsertQuery(String query, Object... params) throws DAOException {
        long result = 0;
        try (PreparedStatement statement = createPreparedStatement(query, params)) {
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                result = generatedKey.getLong(1);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return result;
    }

    protected void executeUpdateQuery(String query, Object... params) throws DAOException {
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
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            ConnectionPool.getInstance().returnConnection(connection);
            return preparedStatement;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

}