package by.epamtc.enrollmentsystem.dao;

import by.epamtc.enrollmentsystem.controller.action.CommandProvider;
import by.epamtc.enrollmentsystem.dao.impl.*;
import by.epamtc.enrollmentsystem.dao.template.*;

/*
 * This class is a provider of different data sources
 * Also singleton pattern was applied to this class
 */

public final class DAOProvider {
    private EducationFormDAO educationFormInstance = new EducationFormMySQL();//TODO non-final + setter + interface ref
    private EnrollmentStatusDAO enrollmentStatusInstance = new EnrollmentStatusMySQL();
    private FacultyDAO facultyInstance = new FacultyMySQL();
    private FacilityDAO facilityInstance = new FacilityMySQL();
    private RoleDAO roleInstance = new RoleMySQL();
    private SubjectDAO subjectInstance = new SubjectMySQL();
    private SystemInformationDAO systemInformationInstance = new SystemInformationMySQL();
    private UserDAO userInstance = new UserMySQL();
    private UserInfoDAO userInfoInstance = new UserInfoMySQL();
    private ResultDAO resultInstance = new ResultMySQL();
    private Subjectm2mFacultyDAO subjectm2mFacultyInstance = new Subjectm2mFacultyMySQL();
    private ApplicantEnrollmentDAO applicantEnrollmentInstance = new ApplicantEnrollmentMySQL();
    private Facilitym2mUserInfoDAO facilitym2mUserInfoMyInstance = new Facilitym2mUserInfoMySQL();

    public EducationFormDAO getEducationFormDAO() {
        return educationFormInstance;
    }

    public void setEducationFormDAO(EducationFormDAO educationFormInstance) {
        this.educationFormInstance = educationFormInstance;
    }

    public EnrollmentStatusDAO getEnrollmentStatusDAO() {
        return enrollmentStatusInstance;
    }

    public void setEnrollmentStatusDAO(EnrollmentStatusDAO enrollmentStatusInstance) {
        this.enrollmentStatusInstance = enrollmentStatusInstance;
    }

    public FacultyDAO getFacultyDAO() {
        return facultyInstance;
    }

    public void setFacultyDAO(FacultyDAO facultyInstance) {
        this.facultyInstance = facultyInstance;
    }

    public FacilityDAO getFacilityDAO() {
        return facilityInstance;
    }

    public void setFacilityDAO(FacilityDAO facilityInstance) {
        this.facilityInstance = facilityInstance;
    }

    public RoleDAO getRoleDAO() {
        return roleInstance;
    }

    public void setRoleDAO(RoleDAO roleInstance) {
        this.roleInstance = roleInstance;
    }

    public SubjectDAO getSubjectDAO() {
        return subjectInstance;
    }

    public void setSubjectDAO(SubjectDAO subjectInstance) {
        this.subjectInstance = subjectInstance;
    }

    public SystemInformationDAO getSystemInformationDAO() {
        return systemInformationInstance;
    }

    public void setSystemInformationDAO(SystemInformationDAO systemInformationInstance) {
        this.systemInformationInstance = systemInformationInstance;
    }

    public UserDAO getUserDAO() {
        return userInstance;
    }

    public void setUserDAO(UserDAO userInstance) {
        this.userInstance = userInstance;
    }

    public UserInfoDAO getUserInfoDAO() {
        return userInfoInstance;
    }

    public void setUserInfoDAO(UserInfoDAO userInfoInstance) {
        this.userInfoInstance = userInfoInstance;
    }

    public ResultDAO getResultDAO() {
        return resultInstance;
    }

    public void setResultDAO(ResultDAO resultInstance) {
        this.resultInstance = resultInstance;
    }

    public Subjectm2mFacultyDAO getSubjectm2mFacultyDAO() {
        return subjectm2mFacultyInstance;
    }

    public void setSubjectm2mFacultyDAO(Subjectm2mFacultyDAO subjectm2mFacultyInstance) {
        this.subjectm2mFacultyInstance = subjectm2mFacultyInstance;
    }

    public ApplicantEnrollmentDAO getApplicantEnrollmentDAO() {
        return applicantEnrollmentInstance;
    }

    public void setApplicantEnrollmentDAO(ApplicantEnrollmentDAO applicantEnrollmentInstance) {
        this.applicantEnrollmentInstance = applicantEnrollmentInstance;
    }

    public Facilitym2mUserInfoDAO getFacilitym2mUserInfoMyDAO() {
        return facilitym2mUserInfoMyInstance;
    }

    public void setFacilitym2mUserInfoMyDAO(Facilitym2mUserInfoDAO facilitym2mUserInfoMyInstance) {
        this.facilitym2mUserInfoMyInstance = facilitym2mUserInfoMyInstance;
    }

    public static void setInstance(DAOProvider instance) {
        DAOProvider.instance = instance;
    }

    private DAOProvider(){

    }


    private static volatile DAOProvider instance = new DAOProvider();

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
