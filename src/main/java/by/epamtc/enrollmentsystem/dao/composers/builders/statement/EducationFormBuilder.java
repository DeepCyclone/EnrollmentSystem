package by.epamtc.enrollmentsystem.dao.composers.builders.statement;

import by.epamtc.enrollmentsystem.dao.tables.fields.EducationFormFields;
import by.epamtc.enrollmentsystem.model.EducationForm;
import by.epamtc.enrollmentsystem.dao.composers.AbstractComposer;
import by.epamtc.enrollmentsystem.dao.composers.builders.entity.EntityBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EducationFormBuilder extends AbstractComposer<EducationForm> implements EntityBuilder<EducationForm> {

    //архитектура такая, чтобы не передавать 3 параметра
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
        educationForm.setId(rs.getInt(EducationFormFields.id));
        educationForm.setName(rs.getString(EducationFormFields.name));
        return educationForm;
    }
}
