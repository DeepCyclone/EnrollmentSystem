package by.epamtc.enrollmentsystem.dao.composer.builders;

import by.epamtc.enrollmentsystem.dao.mapping.fields.ApplicantEnrollmentMapping;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.dao.composer.AbstractComposer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class ApplicantEnrollmentBuilder extends AbstractComposer<ApplicantEnrollment> implements EntityBuilder<ApplicantEnrollment> {

    @Override
    public ApplicantEnrollment singleObjectBuilder(ResultSet rs) throws SQLException {
        return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<ApplicantEnrollment> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public ApplicantEnrollment composeObject(ResultSet rs) throws SQLException {
        ApplicantEnrollment applicantEnrollment = new ApplicantEnrollment();
        applicantEnrollment.setUserId(rs.getLong(ApplicantEnrollmentMapping.userId));
        applicantEnrollment.setFacultyId(rs.getLong(ApplicantEnrollmentMapping.facultyId));
        applicantEnrollment.setEducationFormId(rs.getLong(ApplicantEnrollmentMapping.educationFormId));
        applicantEnrollment.setEnrollmentStatusId(rs.getLong(ApplicantEnrollmentMapping.enrollmentStatusId));
        applicantEnrollment.setPriority(rs.getInt(ApplicantEnrollmentMapping.priority));
        return applicantEnrollment;
    }
}
