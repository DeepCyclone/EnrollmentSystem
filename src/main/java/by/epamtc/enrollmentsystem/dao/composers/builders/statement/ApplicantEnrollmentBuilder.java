package by.epamtc.enrollmentsystem.dao.composers.builders.statement;

import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ApplicantEnrollmentBuilder implements StatementBuilder<ApplicantEnrollment> {

    @Override
    public void buildPreparedStatement(ApplicantEnrollment object,PreparedStatement stmt) throws ServiceException {
        try {
            stmt.setLong(1, object.getUserId());
            stmt.setLong(2, object.getFacultyId());
            stmt.setLong(3, object.getEducationFormId());
            stmt.setLong(4, object.getEnrollmentStatusId());
        }
        catch (SQLException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }
}
