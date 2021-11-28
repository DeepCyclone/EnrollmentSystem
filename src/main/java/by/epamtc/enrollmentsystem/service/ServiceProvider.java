package by.epamtc.enrollmentsystem.service;


import by.epamtc.enrollmentsystem.service.impl.*;
import by.epamtc.enrollmentsystem.service.template.*;
import by.epamtc.enrollmentsystem.service.util.EnrollmentService;
import by.epamtc.enrollmentsystem.service.util.StudyingInfoUpdater;

public class ServiceProvider {

    private final StudyingInfoUpdater studyingInfoUpdater = new StudyingInfoUpdater();
    private final UserService userService = new UserServiceImpl();
    private final Facilitym2mUserInfoService facilitym2mUserInfoService = new Facilitym2mUserInfoServiceImpl();
    private final Subjectm2mFacultyService subjectm2mFacultyService = new Subjectm2mFacultyServiceImpl();
    private final SystemInformationService systemInformationService = new SystemInformationServiceImpl();
    private final FacultyService facultyService = new FacultyServiceImpl();
    private final ApplicantEnrollmentService applicantEnrollmentService = new ApplicantEnrollmentServiceImpl();
    private final EnrollmentService enrollmentService = new EnrollmentService();
    private final UserInfoService userInfoService = new UserInfoServiceImpl();
    private final EducationFormService educationFormService = new EducationFormServiceImpl();
    private final EnrollmentStatusService enrollmentStatusService = new EnrollmentStatusServiceImpl();
    private final SubjectService subjectService = new SubjectServiceImpl();
    private final FacilityService facilityService = new FacilityServiceImpl();
    private final ResultService resultService = new ResultServiceImpl();

    private static volatile ServiceProvider instance;

    private ServiceProvider(){

    }
    public static ServiceProvider getInstance() {
        ServiceProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (ServiceProvider.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ServiceProvider();
                }
            }
        }
        return localInstance;
    }


    public StudyingInfoUpdater getStudyingInfoUpdater() {
        return studyingInfoUpdater;
    }

    public UserService getUserService() {
        return userService;
    }

    public Facilitym2mUserInfoService getFacilitym2mUserInfoService() {
        return facilitym2mUserInfoService;
    }

    public Subjectm2mFacultyService getSubjectm2mFacultyService() {
        return subjectm2mFacultyService;
    }

    public SystemInformationService getSystemInformationService() {
        return systemInformationService;
    }

    public FacultyService getFacultyService() {
        return facultyService;
    }

    public ApplicantEnrollmentService getApplicantEnrollmentService() {
        return applicantEnrollmentService;
    }

    public EnrollmentService getEnrollmentService() {
        return enrollmentService;
    }

    public UserInfoService getUserInfoService() {
        return userInfoService;
    }

    public EducationFormService getEducationFormService() {
        return educationFormService;
    }

    public EnrollmentStatusService getEnrollmentStatusService() {
        return enrollmentStatusService;
    }

    public FacilityService getFacilityService() {
        return facilityService;
    }

    public ResultService getResultService() {
        return resultService;
    }

    public SubjectService getSubjectService() {
        return subjectService;
    }
}
