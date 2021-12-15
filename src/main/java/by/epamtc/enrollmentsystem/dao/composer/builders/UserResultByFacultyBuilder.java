package by.epamtc.enrollmentsystem.dao.composer.builders;

import by.epamtc.enrollmentsystem.dao.composer.AbstractComposer;
import by.epamtc.enrollmentsystem.dao.mapping.fields.ApplicantEnrollmentMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.ResultMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.SubjectMapping;
import by.epamtc.enrollmentsystem.model.dto.MarkValue;
import by.epamtc.enrollmentsystem.model.dto.UserResultByFaculty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class UserResultByFacultyBuilder extends AbstractComposer<UserResultByFaculty> implements EntityBuilder<UserResultByFaculty> {

    @Override
    public UserResultByFaculty singleObjectBuilder(ResultSet rs) throws SQLException {
        return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<UserResultByFaculty> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public UserResultByFaculty composeObject(ResultSet rs) throws SQLException {
        UserResultByFaculty result = new UserResultByFaculty();
        result.setUserId(rs.getInt(ApplicantEnrollmentMapping.userId));
        result.setFacultyId(rs.getInt(ApplicantEnrollmentMapping.facultyId));
        result.setResult(rs.getInt(3));//TODO отметить сумму в запросе через AS
        return result;
    }
}
