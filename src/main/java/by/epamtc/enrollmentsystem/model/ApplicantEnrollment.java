package by.epamtc.enrollmentsystem.model;

public class ApplicantEnrollment {
    public ApplicantEnrollment(int userId, int facultyId, int educationFormId, int enrollmentStatusId) {
        this.userId = userId;
        this.facultyId = facultyId;
        this.educationFormId = educationFormId;
        this.enrollmentStatusId = enrollmentStatusId;
    }

    public ApplicantEnrollment() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getEducationFormId() {
        return educationFormId;
    }

    public void setEducationFormId(int educationFormId) {
        this.educationFormId = educationFormId;
    }

    public int getEnrollmentStatusId() {
        return enrollmentStatusId;
    }

    public void setEnrollmentStatusId(int enrollmentStatusId) {
        this.enrollmentStatusId = enrollmentStatusId;
    }

    private int userId;
    private int facultyId;
    private int educationFormId;
    private int enrollmentStatusId;
}
