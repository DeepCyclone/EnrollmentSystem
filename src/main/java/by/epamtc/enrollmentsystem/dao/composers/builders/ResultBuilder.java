package by.epamtc.enrollmentsystem.dao.composers.builders;

import by.epamtc.enrollmentsystem.dao.tables.fields.ResultFields;
import by.epamtc.enrollmentsystem.model.Result;
import by.epamtc.enrollmentsystem.dao.composers.AbstractComposer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ResultBuilder extends AbstractComposer<Result> implements EntityBuilder<Result> {

    //архитектура такая, чтобы не передавать 3 параметра
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
        result.setSubjectId(rs.getLong(ResultFields.subjectId));
        result.setUserInfoUserId(rs.getLong(ResultFields.userInfoUserId));
        result.setResultValue(rs.getInt(ResultFields.resultValue));
        return result;
    }
}
