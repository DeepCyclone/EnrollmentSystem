package by.epamtc.enrollmentsystem.dao.composer.builders;

import by.epamtc.enrollmentsystem.dao.mapping.fields.SystemInformationMapping;
import by.epamtc.enrollmentsystem.model.SystemInformation;
import by.epamtc.enrollmentsystem.dao.composer.AbstractComposer;

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
        SystemInformation systemInformation = new SystemInformation();
        systemInformation.setId(rs.getLong(SystemInformationMapping.id));
        systemInformation.setName(rs.getString(SystemInformationMapping.name));
        systemInformation.setValue(rs.getString(SystemInformationMapping.value));
        return systemInformation;
    }
}
