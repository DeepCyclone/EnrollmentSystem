package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.tables.fields.ApplicantEnrollmentFields;
import by.epamtc.enrollmentsystem.dao.templates.ApplicantEnrollmentDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.dao.composers.builders.ApplicantEnrollmentBuilder;

import java.util.*;

public final class ApplicantEnrollmentMySQL extends AbstractDAO<ApplicantEnrollment> implements ApplicantEnrollmentDAO {

    private static final String tableName = TablesNames.applicant_enrollment;

    private static final String INSERT_INTO = "INSERT INTO " + TablesNames.applicant_enrollment +
                                              " VALUES (?,?,?,?)";

    private static final String UPDATE_EDUCATION_FORM = "UPDATE " + TablesNames.applicant_enrollment +
                                                        " SET " + ApplicantEnrollmentFields.educationFormId + " = ?" +
                                                        " WHERE " + ApplicantEnrollmentFields.userId +  " = ?" +
                                                        " AND " + ApplicantEnrollmentFields.facultyId + " = ?";

    private static final String GET_BY_FACULTY_AND_USER_ID = "SELECT * FROM " + TablesNames.applicant_enrollment +
                                                            " WHERE " + ApplicantEnrollmentFields.userId + " = ?" +
                                                            " AND " + ApplicantEnrollmentFields.facultyId + " = ?";

    private static final String DELETE_FACULTIES_BY_USER_ID = "DELETE FROM " + TablesNames.applicant_enrollment +
                                                            " WHERE " + ApplicantEnrollmentFields.userId + " = ?";

    private static final String GET_TABLE_INFO_BY_USER_ID = "SELECT * FROM " + TablesNames.applicant_enrollment +
                                                           " WHERE " + ApplicantEnrollmentFields.userId + " = ?";

    private static final String UPDATE_ENROLLMENT_STATUS = "UPDATE " + TablesNames.applicant_enrollment +
                                                        " SET " + ApplicantEnrollmentFields.enrollmentStatusId + " = ?" +
                                                        " WHERE " + ApplicantEnrollmentFields.userId + " = ? " +
                                                        " AND " + ApplicantEnrollmentFields.facultyId + " = ?" +
                                                        " AND " + ApplicantEnrollmentFields.educationFormId + " = ?";


    @Override
    public List<ApplicantEnrollment> getAll() throws DAOException {
        return super.getAll(tableName,new ApplicantEnrollmentBuilder());
    }

    @Override
    public long getIdByName(String name){
        throw new UnsupportedOperationException();
    }

    @Override
    public String getNameById(long id){
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ApplicantEnrollment> getByID(long id){
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertInto(ApplicantEnrollment object) throws DAOException {
        executeInsertQuery(INSERT_INTO,object.getUserId(),object.getFacultyId(),
                        object.getEducationFormId(),object.getEnrollmentStatusId());
    }

    @Override
    public void updateRowByID(ApplicantEnrollment note) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<ApplicantEnrollment> getEntitiesRange(int from, int offset) throws DAOException {
        throw new UnsupportedOperationException();
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
                note.getFacultyId(),note.getEnrollmentStatusId());
    }

    @Override
    public Optional<ApplicantEnrollment> getByFacultyAndUserIds(long userId, long facultyId) throws DAOException {//TODO скорее всего это бизнес логика - перенести. В бизнес-логике просто чекнуть IsPresent
        return executeSingleResultQuery(GET_BY_FACULTY_AND_USER_ID,new ApplicantEnrollmentBuilder(),userId,facultyId);
    }

}
