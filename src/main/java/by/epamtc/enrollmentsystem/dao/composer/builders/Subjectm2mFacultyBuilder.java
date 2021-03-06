package by.epamtc.enrollmentsystem.dao.composer.builders;

import by.epamtc.enrollmentsystem.model.Subjectm2mFaculty;
import by.epamtc.enrollmentsystem.dao.composer.AbstractComposer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class Subjectm2mFacultyBuilder extends AbstractComposer<Subjectm2mFaculty> implements EntityBuilder<Subjectm2mFaculty> {


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
