package by.epamtc.enrollmentsystem.dao.composer.builders;

import by.epamtc.enrollmentsystem.dao.composer.AbstractComposer;
import by.epamtc.enrollmentsystem.dao.mapping.fields.ApplicantEnrollmentMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.ResultMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.SubjectMapping;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.dto.MarkValue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class MarkValueBuilder extends AbstractComposer<MarkValue> implements EntityBuilder<MarkValue> {

    @Override
    public MarkValue singleObjectBuilder(ResultSet rs) throws SQLException {
        return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<MarkValue> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public MarkValue composeObject(ResultSet rs) throws SQLException {
        int subjectId = rs.getInt(SubjectMapping.id);
        String subjectName = rs.getString(SubjectMapping.name);
        int resultValue = rs.getInt(ResultMapping.resultValue);
        return new MarkValue(subjectId,subjectName,resultValue);
    }
}
