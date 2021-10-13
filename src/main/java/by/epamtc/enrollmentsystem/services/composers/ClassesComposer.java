package by.epamtc.enrollmentsystem.services.composers;

import by.epamtc.enrollmentsystem.services.composers.builders.entity.EntityBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ClassesComposer<T> {
    public T singleObjectBuilder(ResultSet rs, EntityBuilder<T> builder) throws SQLException;
    public List<T> buildObjectsList(ResultSet rs,EntityBuilder<T> builder) throws SQLException;

    public T singleObjectBuilder(ResultSet rs) throws SQLException;
    public List<T> buildObjectsList(ResultSet rs) throws SQLException;
}
