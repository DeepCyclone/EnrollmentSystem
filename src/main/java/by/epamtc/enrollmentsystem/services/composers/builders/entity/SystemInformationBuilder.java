package by.epamtc.enrollmentsystem.services.composers.builders.entity;

import by.epamtc.enrollmentsystem.model.SystemInformation;
import by.epamtc.enrollmentsystem.services.composers.AbstractComposer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SystemInformationBuilder extends AbstractComposer<SystemInformation> implements EntityBuilder<SystemInformation> {

    //архитектура такая, чтобы не передавать 3 параметра
    @Override
    public SystemInformation singleObjectBuilder(ResultSet rs) throws SQLException {
       return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<SystemInformation> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public SystemInformation composeObject(ResultSet rs) throws SQLException {
        return null;
    }
}
