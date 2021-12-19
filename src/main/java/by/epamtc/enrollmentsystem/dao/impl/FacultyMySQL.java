package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.QueryExecutor;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.connectionpool.PoolException;
import by.epamtc.enrollmentsystem.dao.mapping.fields.EducationFormMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.EnrollmentStatusMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.FacultyMapping;
import by.epamtc.enrollmentsystem.dao.mapping.SchemaMapping;
import by.epamtc.enrollmentsystem.dao.template.FacultyDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.dao.composer.builders.FacultyBuilder;
import by.epamtc.enrollmentsystem.model.dto.StringifiedApplicantEnrollment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public final class FacultyMySQL extends AbstractDAO<Faculty> implements FacultyDAO {

    private static final String GET_BUDGET_ADMISSION_PLAN_OF_FACULTIES = "SELECT f_id,f_budg_admission_plan FROM faculty";

    private static final String GET_PAID_ADMISSION_PLAN_OF_FACULTIES = "SELECT f_id,f_paid_admission_plan FROM faculty";

    public FacultyMySQL(QueryExecutor<Faculty> executor) {
        super(executor);
    }

    private static final String TABLE_NAME = SchemaMapping.faculty;

    @Override
    public Optional<Faculty> getByID(long id) throws DAOException {
        String idFieldName = FacultyMapping.id;
        return super.getByID(TABLE_NAME,idFieldName,id);
    }

    @Override
    public void insertInto(Faculty object) throws DAOException {

    }

    @Override
    public void updateRowByID(Faculty note) throws DAOException {

    }

    @Override
    public void deleteRowByID(long id) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Faculty> getEntitiesRange(int from, int offset) throws DAOException {
        return super.getEntitiesRange(TABLE_NAME,from,offset);
    }

    @Override
    public List<Faculty> getAll() throws DAOException {
        return super.getAll(TABLE_NAME);
    }

    @Override
    public int getNumberOfRecords() throws DAOException {
     return super.getNumberOfRecords(TABLE_NAME);
    }

    @Override
    public Optional<Faculty> getByName(String name) throws DAOException {
        return super.getByName(TABLE_NAME,FacultyMapping.name,name);
    }

    @Override
    public Map<Long, Integer> getAvailableSpacesForFaculties(int educationFormID) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<Long, Integer> elements = new HashMap<>();
        String query;
        String mapping;
        if(educationFormID == 1){
            query = GET_BUDGET_ADMISSION_PLAN_OF_FACULTIES;
            mapping = FacultyMapping.budgetAdmissionPlan;
        }
        else{
            query = GET_PAID_ADMISSION_PLAN_OF_FACULTIES;
            mapping = FacultyMapping.paidAdmissionPlan;
        }
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()){
                elements.put(rs.getLong(FacultyMapping.id),rs.getInt(mapping));
            }
        }
        catch (SQLException | PoolException exception){
            throw new DAOException(exception.getMessage(),exception);
        }
        finally {
            if(conn!=null){
                ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
            }
        }
        return elements;
    }
}
