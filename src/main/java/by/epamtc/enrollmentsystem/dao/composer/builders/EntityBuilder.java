package by.epamtc.enrollmentsystem.dao.composer.builders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/*
 * @author Flexus
 */
public interface EntityBuilder<T> {
    /*
     * A certain implementation of this method composes an object of some type from result set
     */
    T composeObject(ResultSet rs) throws SQLException;
    /*
     * Returns a single object, composed by @see composeObject
     */
    public T singleObjectBuilder(ResultSet rs) throws SQLException;
    /*
     * Returns a list of objects, composed by @see composeObject
     */
    public List<T> buildObjectsList(ResultSet rs) throws SQLException;
}
