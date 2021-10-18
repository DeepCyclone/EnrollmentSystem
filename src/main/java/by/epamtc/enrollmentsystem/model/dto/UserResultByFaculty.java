package by.epamtc.enrollmentsystem.model.dto;

public class UserResultByFaculty {
    private long userId;
    private long facultyId;
    private int result;

    public UserResultByFaculty() {
    }

    public UserResultByFaculty(long userId, long facultyId, int result) {
        this.userId = userId;
        this.facultyId = facultyId;
        this.result = result;
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
}
