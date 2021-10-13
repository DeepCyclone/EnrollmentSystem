package by.epamtc.enrollmentsystem.services.composers.builders.entity;

import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.services.composers.AbstractComposer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ApplicantEnrollmentBuilder extends AbstractComposer<ApplicantEnrollment> implements EntityBuilder<ApplicantEnrollment> {

    //архитектура такая, чтобы не передавать 3 параметра
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
        applicantEnrollment.setUserId(rs.getInt(1));
        applicantEnrollment.setFacultyId(rs.getInt(2));
        applicantEnrollment.setEducationFormId(rs.getInt(3));
        applicantEnrollment.setEnrollmentStatusId(rs.getInt(4));
        return applicantEnrollment;
    }
    }
