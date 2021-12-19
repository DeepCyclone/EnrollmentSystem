package by.epamtc.enrollmentsystem.model.dto;

public class UserTotalResultWithFaculty {
    private long userId;
    private long facultyId;
    private int result;
    private int priority;

    public UserTotalResultWithFaculty() {
    }

    public UserTotalResultWithFaculty(long userId, long facultyId, int result, int priority) {
        this.userId = userId;
        this.facultyId = facultyId;
        this.result = result;
        this.priority = priority;
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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
