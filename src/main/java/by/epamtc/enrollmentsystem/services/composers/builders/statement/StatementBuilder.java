package by.epamtc.enrollmentsystem.services.composers.builders.statement;

import java.sql.PreparedStatement;

public interface StatementBuilder<T> {
    public PreparedStatement buildPreparedStatement(T object);
}
