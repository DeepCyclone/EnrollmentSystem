package by.epamtc.enrollmentsystem.dao.composers.builders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface EntityBuilder<T> {
    T composeObject(ResultSet rs) throws SQLException;
    public T singleObjectBuilder(ResultSet rs,EntityBuilder<T> builder) throws SQLException;
    public List<T> buildObjectsList(ResultSet rs,EntityBuilder<T> builder) throws SQLException;
}