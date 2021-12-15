package by.epamtc.enrollmentsystem.dao.composer.builders;

import by.epamtc.enrollmentsystem.dao.composer.AbstractComposer;
import by.epamtc.enrollmentsystem.dao.mapping.fields.FacultyMapping;
import by.epamtc.enrollmentsystem.model.Faculty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class FacultyBuilder extends AbstractComposer<Faculty> implements EntityBuilder<Faculty> {


    @Override
    public Faculty singleObjectBuilder(ResultSet rs) throws SQLException {
       return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<Faculty> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public Faculty composeObject(ResultSet rs) throws SQLException {
            Faculty faculty = new Faculty();
            faculty.setId(rs.getLong(FacultyMapping.id));
            faculty.setName(rs.getString(FacultyMapping.name));
            faculty.setBudgetAdmissionPlan(rs.getInt(FacultyMapping.budgetAdmissionPlan));
            faculty.setPaidAdmissionPlan(rs.getInt(FacultyMapping.paidAdmissionPlan));
            faculty.setDescription(rs.getString(FacultyMapping.description));
            return faculty;
    }
}
