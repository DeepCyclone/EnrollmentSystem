package by.epamtc.enrollmentsystem.services.composers.builders.entity;

import by.epamtc.enrollmentsystem.model.EnrollmentStatus;
import by.epamtc.enrollmentsystem.services.composers.AbstractComposer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EnrollmentStatusBuilder extends AbstractComposer<EnrollmentStatus> implements EntityBuilder<EnrollmentStatus> {

    //архитектура такая, чтобы не передавать 3 параметра
    @Override
    public EnrollmentStatus singleObjectBuilder(ResultSet rs) throws SQLException {
       return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<EnrollmentStatus> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public EnrollmentStatus composeObject(ResultSet rs) throws SQLException {
        return null;
    }
}