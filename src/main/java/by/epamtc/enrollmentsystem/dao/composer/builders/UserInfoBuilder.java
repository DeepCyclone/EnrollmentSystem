package by.epamtc.enrollmentsystem.dao.composer.builders;

import by.epamtc.enrollmentsystem.dao.composer.AbstractComposer;
import by.epamtc.enrollmentsystem.model.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserInfoBuilder extends AbstractComposer<UserInfo> implements EntityBuilder<UserInfo> {

    //архитектура такая, чтобы не передавать 3 параметра
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
        ui.setId(rs.getLong(1));
        ui.setName(rs.getString(2));
        ui.setSurname(rs.getString(3));
        ui.setPatronymic(rs.getString(4));
        ui.setPhotoPath(rs.getString(5));
        ui.setAddress(rs.getString(6));
        ui.setPassport(rs.getString(7));
        return ui;
    }
}
