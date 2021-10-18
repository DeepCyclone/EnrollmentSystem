package by.epamtc.enrollmentsystem.dao.composers.builders.entity;

import by.epamtc.enrollmentsystem.dao.composers.AbstractComposer;
import by.epamtc.enrollmentsystem.model.Faculty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FacultyBuilder extends AbstractComposer<Faculty> implements EntityBuilder<Faculty> {

    //архитектура такая, чтобы не передавать 3 параметра
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
            faculty.setId(rs.getLong(1));
            faculty.setName(rs.getString(2));
            faculty.setBudgetAdmissionPlan(rs.getInt(3));
            faculty.setPaidAdmissionPlan(rs.getInt(4));
            faculty.setDescription(rs.getString(5));
            return faculty;
    }
}
