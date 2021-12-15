package by.epamtc.enrollmentsystem.dao.composer.builders;

import by.epamtc.enrollmentsystem.dao.composer.AbstractComposer;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class IntegerBuilder extends AbstractComposer<Integer> implements EntityBuilder<Integer> {

    @Override
    public Integer singleObjectBuilder(ResultSet rs) throws SQLException {
        return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<Integer> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public Integer composeObject(ResultSet rs) throws SQLException {
        return rs.getInt(1);
    }
}
