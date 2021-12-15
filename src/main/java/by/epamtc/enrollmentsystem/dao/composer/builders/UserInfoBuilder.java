package by.epamtc.enrollmentsystem.dao.composer.builders;

import by.epamtc.enrollmentsystem.dao.composer.AbstractComposer;
import by.epamtc.enrollmentsystem.dao.mapping.fields.UserInfoMapping;
import by.epamtc.enrollmentsystem.model.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class UserInfoBuilder extends AbstractComposer<UserInfo> implements EntityBuilder<UserInfo> {


    @Override
    public UserInfo singleObjectBuilder(ResultSet rs) throws SQLException {
       return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<UserInfo> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public UserInfo composeObject(ResultSet rs) throws SQLException {
        UserInfo ui = new UserInfo();
        ui.setId(rs.getLong(UserInfoMapping.userId));
        ui.setName(rs.getString(UserInfoMapping.name));
        ui.setSurname(rs.getString(UserInfoMapping.surname));
        ui.setPatronymic(rs.getString(UserInfoMapping.patronymic));
        ui.setPhotoPath(rs.getString(UserInfoMapping.photoAddress));
        ui.setAddress(rs.getString(UserInfoMapping.address));
        ui.setPassport(rs.getString(UserInfoMapping.passport));
        return ui;
    }
}
