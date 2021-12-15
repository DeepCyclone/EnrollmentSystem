package by.epamtc.enrollmentsystem.dao.composer.builders;

import by.epamtc.enrollmentsystem.dao.mapping.fields.EnrollmentStatusMapping;
import by.epamtc.enrollmentsystem.model.EnrollmentStatus;
import by.epamtc.enrollmentsystem.dao.composer.AbstractComposer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class EnrollmentStatusBuilder extends AbstractComposer<EnrollmentStatus> implements EntityBuilder<EnrollmentStatus> {


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
        EnrollmentStatus status = new EnrollmentStatus();
        status.setId(rs.getLong(EnrollmentStatusMapping.id));
        status.setName(rs.getString(EnrollmentStatusMapping.name));
        return status;
    }
}
