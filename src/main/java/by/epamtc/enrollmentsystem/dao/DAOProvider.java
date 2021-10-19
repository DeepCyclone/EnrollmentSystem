package by.epamtc.enrollmentsystem.dao;

import by.epamtc.enrollmentsystem.controller.action.CommandProvider;
import by.epamtc.enrollmentsystem.dao.impl.*;

public final class DAOProvider {
    private final EducationFormMySQL educationFormMySQL = new EducationFormMySQL();
    private final EnrollmentStatusMySQL enrollmentStatusMySQL = new EnrollmentStatusMySQL();
    private final FacultyMySQL facultyMySQL = new FacultyMySQL();
    private final FacilityMySQL facilityMySQL = new FacilityMySQL();
    private final RoleMySQL roleMySQL = new RoleMySQL();
    private final SubjectMySQL subjectMySQL = new SubjectMySQL();
    private final SystemInformationMySQL systemInformationMySQL = new SystemInformationMySQL();
    private final UserMySQL userMySQL = new UserMySQL();
    private final UserInfoMySQL userInfoMySQL = new UserInfoMySQL();
    private final ResultMySQL resultMySQL = new ResultMySQL();
    private final Subjectm2mFacultyMySQL subjectm2mFacultyMySQL = new Subjectm2mFacultyMySQL();
    private final ApplicantEnrollmentMySQL applicantEnrollmentMySQL = new ApplicantEnrollmentMySQL();
    private final Facilitym2mUserInfoMySQL facilitym2mUserInfoMySQL = new Facilitym2mUserInfoMySQL();
    private DAOProvider(){

    }
    public EducationFormMySQL getEducationFormDAO() {
        return educationFormMySQL;
    }

    public EnrollmentStatusMySQL getEnrollmentStatusDAO() {
        return enrollmentStatusMySQL;
    }

    public FacultyMySQL getFacultyDAO() {
        return facultyMySQL;
    }

    public FacilityMySQL getFacilityDAO() {
        return facilityMySQL;
    }

    public RoleMySQL getRoleDAO() {
        return roleMySQL;
    }

    public SubjectMySQL getSubjectDAO() {
        return subjectMySQL;
    }

    public SystemInformationMySQL getSystemInformationDAO() {
        return systemInformationMySQL;
    }

    public UserMySQL getUserDAO() {
        return userMySQL;
    }

    public UserInfoMySQL getUserInfoDAO() {
        return userInfoMySQL;
    }

    public ResultMySQL getResultDAO() {
        return resultMySQL;
    }

    public ApplicantEnrollmentMySQL getApplicantEnrollmentDAO() {
        return applicantEnrollmentMySQL;
    }

    public Subjectm2mFacultyMySQL getSubjectm2mFacultyMySQL() {
        return subjectm2mFacultyMySQL;
    }

    public Facilitym2mUserInfoMySQL getFacilitym2mUserInfoMySQL() {
        return facilitym2mUserInfoMySQL;
    }

    private static volatile DAOProvider instance;

    public static DAOProvider getInstance() {
        DAOProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (DAOProvider.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DAOProvider();
                }
            }
        }
        return localInstance;
    }

}
