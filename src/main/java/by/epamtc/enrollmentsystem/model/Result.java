package by.epamtc.enrollmentsystem.model;

public class Result {

    private long subjectId;
    private long userInfoUserId;
    private int resultValue;

    public Result() {
    }

    public Result(long subjectId, long userInfoUserId, int resultValue) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return subjectId == result.subjectId &&
                userInfoUserId == result.userInfoUserId &&
                resultValue == result.resultValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) subjectId;
        result = prime * result + (int) userInfoUserId;
        result = prime * result + resultValue;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getClass().getSimpleName());
        result.append(" Fields:\n");
        result.append("subject ID:").append(subjectId).append("\n");
        result.append("userInfo ID:").append(userInfoUserId).append("\n");
        result.append("Value:").append(resultValue).append("\n");
        return result.toString();
    }
}
