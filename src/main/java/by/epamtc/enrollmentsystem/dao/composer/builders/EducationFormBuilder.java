package by.epamtc.enrollmentsystem.dao.composer.builders;

import by.epamtc.enrollmentsystem.dao.composer.AbstractComposer;
import by.epamtc.enrollmentsystem.dao.mapping.fields.EducationFormMapping;
import by.epamtc.enrollmentsystem.model.EducationForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EducationFormBuilder extends AbstractComposer<EducationForm> implements EntityBuilder<EducationForm> {

    @Override
    public EducationForm singleObjectBuilder(ResultSet rs) throws SQLException {
       return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<EducationForm> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public EducationForm composeObject(ResultSet rs) throws SQLException {
        EducationForm educationForm = new EducationForm();
        educationForm.setId(rs.getLong(EducationFormMapping.id));
        educationForm.setName(rs.getString(EducationFormMapping.name));
        return educationForm;
    }
}
