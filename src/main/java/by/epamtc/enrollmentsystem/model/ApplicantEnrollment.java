package by.epamtc.enrollmentsystem.model;


import java.io.Serializable;

public class ApplicantEnrollment implements Serializable {

    private long userId;
    private long facultyId;
    private long educationFormId;
    private long enrollmentStatusId;
    private int priority;

    public ApplicantEnrollment(long userId, long facultyId, long educationFormId, long enrollmentStatusId,int priority) {
        this.userId = userId;
        this.facultyId = facultyId;
        this.educationFormId = educationFormId;
        this.enrollmentStatusId = enrollmentStatusId;
        this.priority = priority;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicantEnrollment that = (ApplicantEnrollment) o;
        return userId == that.userId && facultyId == that.facultyId &&
                educationFormId == that.educationFormId &&
                enrollmentStatusId == that.enrollmentStatusId &&
                priority == that.priority;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) userId;
        result = prime * result + (int) facultyId;
        result = prime * result + (int) educationFormId;
        result = prime * result + (int) enrollmentStatusId;
        result = prime * result + priority;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getClass().getSimpleName());
        result.append(" Fields:\n");
        result.append("user ID:").append(userId).append("\n");
        result.append("faculty ID:").append(facultyId).append("\n");
        result.append("education form ID:").append(educationFormId).append("\n");
        result.append("enrollment status ID:").append(enrollmentStatusId).append("\n");
        result.append("priority:").append(priority).append("\n");
        return result.toString();
    }
}
