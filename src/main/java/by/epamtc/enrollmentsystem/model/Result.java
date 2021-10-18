package by.epamtc.enrollmentsystem.model;

public class Result {

    private long subjectId;
    private long userInfoUserId;
    private int resultValue;

    public Result() {
    }

    public Result(int subjectId, int userInfoUserId, int resultValue) {
        this.subjectId = subjectId;
        this.userInfoUserId = userInfoUserId;
        this.resultValue = resultValue;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getUserInfoUserId() {
        return userInfoUserId;
    }

    public void setUserInfoUserId(long userInfoUserId) {
        this.userInfoUserId = userInfoUserId;
    }

    public int getResultValue() {
        return resultValue;
    }

    public void setResultValue(int resultValue) {
        this.resultValue = resultValue;
    }

}
