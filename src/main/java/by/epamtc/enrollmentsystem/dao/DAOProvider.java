package by.epamtc.enrollmentsystem.dao;

import by.epamtc.enrollmentsystem.controller.action.CommandProvider;
import by.epamtc.enrollmentsystem.dao.impl.*;

public final class DAOProvider {
    private EducationFormMySQL educationFormMySQL = new EducationFormMySQL();

    private EnrollmentStatusMySQL enrollmentStatusMySQL = new EnrollmentStatusMySQL();
    private FacultyMySQL facultyMySQL = new FacultyMySQL();
    private FacilityMySQL facilityMySQL = new FacilityMySQL();
    private RoleMySQL roleMySQL = new RoleMySQL();
    private SubjectMySQL subjectMySQL = new SubjectMySQL();
    private SystemInformationMySQL systemInformationMySQL = new SystemInformationMySQL();
    private UserMySQL userMySQL = new UserMySQL();
    private UserInfoMySQL userInfoMySQL = new UserInfoMySQL();
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

    private static volatile DAOProvider instance;

    public static DAOProvider getInstance() {
        DAOProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (CommandProvider.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DAOProvider();
                }
            }
        }
        return localInstance;
    }

}
