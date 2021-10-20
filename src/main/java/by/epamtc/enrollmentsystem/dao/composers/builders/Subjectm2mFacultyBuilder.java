package by.epamtc.enrollmentsystem.dao.composers.builders;

import by.epamtc.enrollmentsystem.model.Subjectm2mFaculty;
import by.epamtc.enrollmentsystem.dao.composers.AbstractComposer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Subjectm2mFacultyBuilder extends AbstractComposer<Subjectm2mFaculty> implements EntityBuilder<Subjectm2mFaculty> {

    //архитектура такая, чтобы не передавать 3 параметра
    @Override
    public Subjectm2mFaculty singleObjectBuilder(ResultSet rs) throws SQLException {
       return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<Subjectm2mFaculty> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public Subjectm2mFaculty composeObject(ResultSet rs) throws SQLException {
        return null;
    }
}
