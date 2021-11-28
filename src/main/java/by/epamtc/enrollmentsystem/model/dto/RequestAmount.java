package by.epamtc.enrollmentsystem.model.dto;

public class RequestAmount {
    private long userId;
    private String facultyName;
    private long totalRequests;

    public RequestAmount(long userId, String facultyName, long totalRequests) {
        this.userId = userId;
        this.facultyName = facultyName;
        this.totalRequests = totalRequests;
    }

    public RequestAmount() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public long getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(long totalRequests) {
        this.totalRequests = totalRequests;
    }
}
