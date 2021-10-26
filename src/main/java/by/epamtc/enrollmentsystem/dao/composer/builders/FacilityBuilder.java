package by.epamtc.enrollmentsystem.dao.composer.builders;

import by.epamtc.enrollmentsystem.dao.mapping.fields.FacilityMapping;
import by.epamtc.enrollmentsystem.model.Facility;
import by.epamtc.enrollmentsystem.dao.composer.AbstractComposer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FacilityBuilder extends AbstractComposer<Facility> implements EntityBuilder<Facility> {

    //архитектура такая, чтобы не передавать 3 параметра
    @Override
    public Facility singleObjectBuilder(ResultSet rs) throws SQLException {
       return super.singleObjectBuilder(rs,this);
    }

    @Override
    public List<Facility> buildObjectsList(ResultSet rs) throws SQLException {
        return super.buildObjectsList(rs,this);
    }

    @Override
    public Facility composeObject(ResultSet rs) throws SQLException {
        Facility facility = new Facility();
        facility.setId(rs.getLong(FacilityMapping.id));
        facility.setName(rs.getString(FacilityMapping.name));
        facility.setExtraordinary(rs.getBoolean(FacilityMapping.extraordinaryStatus));
        return facility;
    }
}
