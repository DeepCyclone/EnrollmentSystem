package by.epamtc.enrollmentsystem.dao.composers.builders;

import by.epamtc.enrollmentsystem.dao.tables.fields.Facilitym2mUserInfoFields;
import by.epamtc.enrollmentsystem.model.Facilitym2mUserInfo;
import by.epamtc.enrollmentsystem.dao.composers.AbstractComposer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Facilitym2mUserInfoBuilder extends AbstractComposer<Facilitym2mUserInfo> implements EntityBuilder<Facilitym2mUserInfo> {

    //архитектура такая, чтобы не передавать 3 параметра
    @Override
    public Facilitym2mUserInfo singleObjectBuilder(ResultSet rs) throws SQLException {
       return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<Facilitym2mUserInfo> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public Facilitym2mUserInfo composeObject(ResultSet rs) throws SQLException {
        Facilitym2mUserInfo facilitym2mUserInfo = new Facilitym2mUserInfo();
        facilitym2mUserInfo.setFacilityId(rs.getLong(Facilitym2mUserInfoFields.facilityId));
        facilitym2mUserInfo.setUserInfoUserId(rs.getLong(Facilitym2mUserInfoFields.userInfoUserId));
        return facilitym2mUserInfo;
    }
}
