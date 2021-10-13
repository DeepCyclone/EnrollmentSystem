package by.epamtc.enrollmentsystem.services.composers.builders.entity;

import by.epamtc.enrollmentsystem.model.Role;
import by.epamtc.enrollmentsystem.services.composers.AbstractComposer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleBuilder extends AbstractComposer<Role> implements EntityBuilder<Role> {

    //архитектура такая, чтобы не передавать 3 параметра
    @Override
    public Role singleObjectBuilder(ResultSet rs) throws SQLException {
       return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<Role> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public Role composeObject(ResultSet rs) throws SQLException {
        return null;
    }
}