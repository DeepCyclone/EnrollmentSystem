package by.epamtc.enrollmentsystem.dao.composers.builders;

import by.epamtc.enrollmentsystem.dao.composers.AbstractComposer;
import by.epamtc.enrollmentsystem.model.User;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserBuilder extends AbstractComposer<User> implements EntityBuilder<User> {

    //архитектура такая, чтобы не передавать 3 параметра
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
        user.setId(rs.getLong(1));
        user.setLogin(rs.getString(2));
        user.setPassword(rs.getString(3).getBytes(StandardCharsets.UTF_8));
        user.setEmail(rs.getString(4));
        user.setRoleId(rs.getLong(5));
        return user;
    }
}
