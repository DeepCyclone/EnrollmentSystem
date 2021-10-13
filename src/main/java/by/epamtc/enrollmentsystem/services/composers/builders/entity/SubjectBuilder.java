package by.epamtc.enrollmentsystem.services.composers.builders.entity;

import by.epamtc.enrollmentsystem.model.Subject;
import by.epamtc.enrollmentsystem.services.composers.AbstractComposer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SubjectBuilder extends AbstractComposer<Subject> implements EntityBuilder<Subject> {

    //архитектура такая, чтобы не передавать 3 параметра
    @Override
    public Subject singleObjectBuilder(ResultSet rs) throws SQLException {
       return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<Subject> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public Subject composeObject(ResultSet rs) throws SQLException {
        return null;
    }
}
