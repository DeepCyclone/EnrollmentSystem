package by.epamtc.enrollmentsystem.dao.composer.builders;

import by.epamtc.enrollmentsystem.dao.composer.AbstractComposer;
import by.epamtc.enrollmentsystem.dao.mapping.fields.ApplicantEnrollmentMapping;
import by.epamtc.enrollmentsystem.model.dto.UserTotalResultWithFaculty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class UserTotalResultWithFacultyBuilder extends AbstractComposer<UserTotalResultWithFaculty> implements EntityBuilder<UserTotalResultWithFaculty> {

    @Override
    public UserTotalResultWithFaculty singleObjectBuilder(ResultSet rs) throws SQLException {
        return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<UserTotalResultWithFaculty> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public UserTotalResultWithFaculty composeObject(ResultSet rs) throws SQLException {
        UserTotalResultWithFaculty result = new UserTotalResultWithFaculty();
        result.setUserId(rs.getInt(ApplicantEnrollmentMapping.userId));
        result.setFacultyId(rs.getInt(ApplicantEnrollmentMapping.facultyId));
        result.setResult(rs.getInt(3));
        result.setPriority(rs.getInt(ApplicantEnrollmentMapping.priority));
        return result;
    }
}
