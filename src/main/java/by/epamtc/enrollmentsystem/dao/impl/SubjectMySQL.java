package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.composer.builders.SubjectBuilder;
import by.epamtc.enrollmentsystem.dao.mapping.SchemaMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.SubjectMapping;
import by.epamtc.enrollmentsystem.dao.template.SubjectDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Subject;

import java.util.List;
import java.util.Optional;

public final class SubjectMySQL extends AbstractDAO<Subject> implements SubjectDAO {

    String tableName = SchemaMapping.subject;
    private static final String SELECT_NAMES = "SELECT " + SubjectMapping.name + " FROM " + SchemaMapping.education_form;


    @Override
    public List<Subject> getAll() throws DAOException {
        return super.getAll(tableName,new SubjectBuilder());
    }

    @Override
    public Optional<Subject> getByID(long id) throws DAOException {
        return super.getByID(tableName,SubjectMapping.id,id,new SubjectBuilder());
    }

    @Override
    public void insertInto(Subject object) throws DAOException {

    }


    @Override
    public void deleteAll() {

    }

    @Override
    public int getNumberOfRecords() throws DAOException {
        return 0;
    }

    @Override
    public List<Subject> getEntitiesRange(int from, int offset) throws DAOException {
        return null;
    }

    @Override
    public void updateRowByID(Subject note) {

    }

    @Override
    public void deleteRowByID(long id) throws DAOException {

    }

//    @Override
//    public List<String> getSubjectsNames() throws DAOException {
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        List<String> names = new ArrayList<>();
//        try{
//            conn = ConnectionPool.getInstance().getConnection();
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery(SELECT_NAMES);
//            while(rs.next()) {
//                names.add(rs.getString(SubjectFields.name));
//            }
//        }
//        catch (SQLException exception){
//            throw new DAOException(exception.getMessage(),exception);
//        }
//        finally {
//            if(conn !=null){
//                ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
//            }
//        }
//        return names;
//    }

    @Override
    public Optional<Subject> getByName(String name) throws DAOException {
        return super.getByName(tableName,SubjectMapping.name,name,new SubjectBuilder());
    }
}
