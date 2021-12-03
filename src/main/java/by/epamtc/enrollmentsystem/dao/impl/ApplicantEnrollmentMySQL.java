package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.connectionpool.PoolException;
import by.epamtc.enrollmentsystem.dao.mapping.SchemaMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.ApplicantEnrollmentMapping;
import by.epamtc.enrollmentsystem.dao.template.ApplicantEnrollmentDAO;
import by.epamtc.enrollmentsystem.dao.mapping.fields.EducationFormMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.EnrollmentStatusMapping;
import by.epamtc.enrollmentsystem.dao.mapping.fields.FacultyMapping;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.dao.composer.builders.ApplicantEnrollmentBuilder;
import by.epamtc.enrollmentsystem.model.dto.StringifiedApplicantEnrollment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public final class ApplicantEnrollmentMySQL extends AbstractDAO<ApplicantEnrollment> implements ApplicantEnrollmentDAO {

    private static final String tableName = SchemaMapping.applicant_enrollment;

    private static final String INSERT_INTO = "INSERT INTO " + SchemaMapping.applicant_enrollment +
                                              " VALUES (?,?,?,?)";

    private static final String UPDATE_EDUCATION_FORM = "UPDATE " + SchemaMapping.applicant_enrollment +
                                                        " SET " + ApplicantEnrollmentMapping.educationFormId + " = ?" +
                                                        " WHERE " + ApplicantEnrollmentMapping.userId +  " = ?" +
                                                        " AND " + ApplicantEnrollmentMapping.facultyId + " = ?";

    private static final String GET_BY_FACULTY_AND_USER_ID = "SELECT * FROM " + SchemaMapping.applicant_enrollment +
                                                            " WHERE " + ApplicantEnrollmentMapping.userId + " = ?" +
                                                            " AND " + ApplicantEnrollmentMapping.facultyId + " = ?";

    private static final String DELETE_FACULTIES_BY_USER_ID = "DELETE FROM " + SchemaMapping.applicant_enrollment +
                                                            " WHERE " + ApplicantEnrollmentMapping.userId + " = ?";

    private static final String GET_TABLE_INFO_BY_USER_ID = "SELECT * FROM " + SchemaMapping.applicant_enrollment +
                                                           " WHERE " + ApplicantEnrollmentMapping.userId + " = ?";

    private static final String UPDATE_ENROLLMENT_STATUS = "UPDATE " + SchemaMapping.applicant_enrollment +
                                                        " SET " + ApplicantEnrollmentMapping.enrollmentStatusId + " = ?" +
                                                        " WHERE " + ApplicantEnrollmentMapping.userId + " = ? " +
                                                        " AND " + ApplicantEnrollmentMapping.facultyId + " = ?" +
                                                        " AND " + ApplicantEnrollmentMapping.educationFormId + " = ?";

    private static final String GET_STRINGIFIED_USER_RESULT = "SELECT f_name,e_form_name,es_name FROM applicant_enrollment " +
                                                             " JOIN education_form ef on ef.e_form_id = applicant_enrollment.ae_ef_id" +
                                                             " JOIN enrollment_status es on es.es_id = applicant_enrollment.ae_es_id" +
                                                             " JOIN faculty f on applicant_enrollment.ae_f_id = f.f_id" +
                                                             " JOIN user u on applicant_enrollment.ae_u_id = u.u_id" +
                                                             " WHERE ae_u_id = ?";


    private static final String GET_USER_REQUESTS_AMOUNT = "SELECT COUNT(*) FROM " + SchemaMapping.applicant_enrollment +
                                                          " WHERE " + ApplicantEnrollmentMapping.educationFormId + " = ?" +
                                                          " AND " + ApplicantEnrollmentMapping.facultyId + " = ?";

    @Override
    public List<ApplicantEnrollment> getAll() throws DAOException {
        return super.getAll(tableName,new ApplicantEnrollmentBuilder());
    }

    @Override
    public void insertInto(ApplicantEnrollment object) throws DAOException {
        executeInsertQuery(INSERT_INTO,object.getUserId(),object.getFacultyId(),
                        object.getEducationFormId(),object.getEnrollmentStatusId());
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public int getNumberOfRecords() throws DAOException {
        return 0;
    }

    @Override
    public List<ApplicantEnrollment> getEntitiesRange(int from, int offset) throws DAOException {
        return super.getEntitiesRange(tableName,from,offset,new ApplicantEnrollmentBuilder());
    }

    @Override
    public void updateEducationForm(long userId, long facultyId,long educationFormId) throws DAOException {
        executeUpdateQuery(UPDATE_EDUCATION_FORM,educationFormId,userId,facultyId);
    }

    @Override
    public void deleteFacultiesByUserId(long userId) throws DAOException {
        executeUpdateQuery(DELETE_FACULTIES_BY_USER_ID,userId);
    }

    @Override
    public List<ApplicantEnrollment> getByUserId(long userId) throws DAOException {
        return executeSelectQuery(GET_TABLE_INFO_BY_USER_ID,new ApplicantEnrollmentBuilder(),userId);
    }

    @Override
    public void updateEnrollmentStatusByUserId(ApplicantEnrollment note) throws DAOException {
        executeUpdateQuery(UPDATE_ENROLLMENT_STATUS,note.getEnrollmentStatusId(),note.getUserId(),
                note.getFacultyId(),note.getEducationFormId());
    }

    @Override
    public Set<StringifiedApplicantEnrollment> getStringifiedTableByUserId(long userId) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Set<StringifiedApplicantEnrollment> elements = new HashSet<>();
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(GET_STRINGIFIED_USER_RESULT);
            stmt.setLong(1,userId);
            rs = stmt.executeQuery();
            StringifiedApplicantEnrollment enrollment = new StringifiedApplicantEnrollment();
            Map<String,String> formsStatuses = new HashMap<>();
            while (rs.next()){
                String facultyName = rs.getString(FacultyMapping.name);
                if(!Objects.equals(enrollment.getFacultyName(),facultyName)){
                    enrollment = new StringifiedApplicantEnrollment();
                    enrollment.setFacultyName(facultyName);
                    formsStatuses = new HashMap<>();
                    enrollment.setEducationFormStatuses(formsStatuses);
                    elements.add(enrollment);
                }
                enrollment.getEducationFormStatuses().put(rs.getString(EducationFormMapping.name), rs.getString(EnrollmentStatusMapping.name));
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

    @Override
    public Optional<ApplicantEnrollment> getByFacultyAndUserIds(long userId, long facultyId) throws DAOException {
        return executeSingleResultQuery(GET_BY_FACULTY_AND_USER_ID,new ApplicantEnrollmentBuilder(),userId,facultyId);
    }

    @Override
    public int getUserRequestsAmount(long facultyID,long educationFormID) throws DAOException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int requests = 0;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(GET_USER_REQUESTS_AMOUNT);
            stmt.setLong(1,educationFormID);
            stmt.setLong(2,facultyID);
            rs = stmt.executeQuery();
            rs.next();
            requests = rs.getInt(1);

        } catch (SQLException | PoolException exception) {

        }
        finally {
            if(conn!=null){
                ConnectionPool.getInstance().closeConnection(conn,stmt,rs);
            }
        }
        return requests;
    }
}
