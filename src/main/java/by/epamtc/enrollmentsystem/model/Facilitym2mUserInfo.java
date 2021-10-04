package by.epamtc.enrollmentsystem.model;

public class Facilitym2mUserInfo {
    private int facultyId;
    private int userInfoUserId;

    public Facilitym2mUserInfo(int facultyId, int userInfoUserId) {
        this.facultyId = facultyId;
        this.userInfoUserId = userInfoUserId;
    }

    public Facilitym2mUserInfo() {
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getUserInfoUserId() {
        return userInfoUserId;
    }

    public void setUserInfoUserId(int userInfoUserId) {
        this.userInfoUserId = userInfoUserId;
    }
}
