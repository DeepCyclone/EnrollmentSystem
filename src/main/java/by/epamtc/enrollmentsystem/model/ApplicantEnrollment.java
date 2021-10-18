package by.epamtc.enrollmentsystem.model;


public class ApplicantEnrollment {

    private long userId;
    private long facultyId;
    private long educationFormId;
    private long enrollmentStatusId;

    public ApplicantEnrollment(int userId, int facultyId, int educationFormId, int enrollmentStatusId) {
        this.userId = userId;
        this.facultyId = facultyId;
        this.educationFormId = educationFormId;
        this.enrollmentStatusId = enrollmentStatusId;
    }

    public ApplicantEnrollment() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public long getEducationFormId() {
        return educationFormId;
    }

    public void setEducationFormId(long educationFormId) {
        this.educationFormId = educationFormId;
    }

    public long getEnrollmentStatusId() {
        return enrollmentStatusId;
    }

    public void setEnrollmentStatusId(long enrollmentStatusId) {
        this.enrollmentStatusId = enrollmentStatusId;
    }
}
