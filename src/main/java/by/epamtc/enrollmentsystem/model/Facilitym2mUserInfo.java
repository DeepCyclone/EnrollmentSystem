package by.epamtc.enrollmentsystem.model;

public class Facilitym2mUserInfo {
    private long facilityId;
    private long userInfoUserId;

    public Facilitym2mUserInfo() {
    }

    public Facilitym2mUserInfo(int facilityId, int userInfoUserId) {
        this.facilityId = facilityId;
        this.userInfoUserId = userInfoUserId;
    }

    public long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(long facultyId) {
        this.facilityId = facultyId;
    }

    public long getUserInfoUserId() {
        return userInfoUserId;
    }

    public void setUserInfoUserId(long userInfoUserId) {
        this.userInfoUserId = userInfoUserId;
    }
}
