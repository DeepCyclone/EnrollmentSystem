package by.epamtc.enrollmentsystem.dao.composers.builders.statement;

import by.epamtc.enrollmentsystem.exception.ServiceException;

import java.sql.PreparedStatement;

public interface StatementBuilder<T> {
    public void buildPreparedStatement(T object,PreparedStatement stmt) throws ServiceException;
}
