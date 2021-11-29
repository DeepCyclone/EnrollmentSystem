package by.epamtc.enrollmentsystem.dao.composer.builders;

import by.epamtc.enrollmentsystem.dao.mapping.fields.ResultMapping;
import by.epamtc.enrollmentsystem.model.Result;
import by.epamtc.enrollmentsystem.dao.composer.AbstractComposer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ResultBuilder extends AbstractComposer<Result> implements EntityBuilder<Result> {


    @Override
    public Result singleObjectBuilder(ResultSet rs) throws SQLException {
       return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<Result> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public Result composeObject(ResultSet rs) throws SQLException {
        Result result = new Result();
        result.setSubjectId(rs.getLong(ResultMapping.subjectId));
        result.setUserInfoUserId(rs.getLong(ResultMapping.userInfoUserId));
        result.setResultValue(rs.getInt(ResultMapping.resultValue));
        return result;
    }
}
