package by.epamtc.enrollmentsystem.model;

public class Facilitym2mUserInfo {
    private int facilityId;
    private int userInfoUserId;

    public Facilitym2mUserInfo(int facilityId, int userInfoUserId) {
        this.facilityId = facilityId;
        this.userInfoUserId = userInfoUserId;
    }

    public Facilitym2mUserInfo() {
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facultyId) {
        this.facilityId = facultyId;
    }

    public int getUserInfoUserId() {
        return userInfoUserId;
    }

    public void setUserInfoUserId(int userInfoUserId) {
        this.userInfoUserId = userInfoUserId;
    }
}
