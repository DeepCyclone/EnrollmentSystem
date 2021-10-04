package by.epamtc.enrollmentsystem.model;

public class Result {

    public Result() {
    }

    public Result(int subjectId, int userInfoUserId, int resultValue) {
        this.subjectId = subjectId;
        this.userInfoUserId = userInfoUserId;
        this.resultValue = resultValue;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getUserInfoUserId() {
        return userInfoUserId;
    }

    public void setUserInfoUserId(int userInfoUserId) {
        this.userInfoUserId = userInfoUserId;
    }

    public int getResultValue() {
        return resultValue;
    }

    public void setResultValue(int resultValue) {
        this.resultValue = resultValue;
    }

    private int subjectId;
    private int userInfoUserId;
    private int resultValue;

}
