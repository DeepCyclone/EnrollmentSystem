package by.epamtc.enrollmentsystem.dao.composer.builders;

import by.epamtc.enrollmentsystem.dao.composer.AbstractComposer;
import by.epamtc.enrollmentsystem.dao.mapping.fields.UserMapping;
import by.epamtc.enrollmentsystem.model.User;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class UserBuilder extends AbstractComposer<User> implements EntityBuilder<User> {


    @Override
    public User singleObjectBuilder(ResultSet rs) throws SQLException {
       return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<User> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public User composeObject(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(UserMapping.id));
        user.setLogin(rs.getString(UserMapping.login));
        user.setPassword(rs.getString(UserMapping.password).getBytes(StandardCharsets.UTF_8));
        user.setEmail(rs.getString(UserMapping.email));
        user.setRoleId(rs.getLong(UserMapping.roleId));
        return user;
    }
}
