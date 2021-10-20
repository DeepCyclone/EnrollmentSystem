package by.epamtc.enrollmentsystem.dao.composers;

import by.epamtc.enrollmentsystem.dao.composers.builders.EntityBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractComposer<T> implements ClassesComposer<T> {

    public abstract T singleObjectBuilder(ResultSet rs) throws SQLException;
    public abstract List<T> buildObjectsList(ResultSet rs) throws SQLException;

    @Override
    public T singleObjectBuilder(ResultSet rs, EntityBuilder<T> builder) throws SQLException {
        T entity = null;
        while (rs.next()) {
            entity = builder.composeObject(rs);
        }
        return entity;
    }

    @Override
    public List<T> buildObjectsList(ResultSet rs,EntityBuilder<T> builder) throws SQLException {
        List<T> entities = new ArrayList<>();
        while (rs.next()){
            entities.add(builder.composeObject(rs));
        }
        return entities;
    }
}
