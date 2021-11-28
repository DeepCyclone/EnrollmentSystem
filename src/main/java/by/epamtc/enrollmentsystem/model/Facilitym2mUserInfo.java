package by.epamtc.enrollmentsystem.model;

import java.io.Serializable;

public class Facilitym2mUserInfo implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facilitym2mUserInfo that = (Facilitym2mUserInfo) o;
        return facilityId == that.facilityId && userInfoUserId == that.userInfoUserId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) facilityId;
        result = prime * result + (int) userInfoUserId;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getClass().getSimpleName());
        result.append(" Fields:\n");
        result.append("facility ID:").append(facilityId).append("\n");
        result.append("userInfo ID:").append(userInfoUserId).append("\n");
        return result.toString();
    }
}
