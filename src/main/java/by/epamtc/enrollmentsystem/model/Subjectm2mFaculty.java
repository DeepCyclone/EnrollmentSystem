package by.epamtc.enrollmentsystem.model;

import java.io.Serializable;

public class Subjectm2mFaculty implements Serializable {

    private long subjectId;
    private long facultyId;
    private int subjectUrgency;

    public Subjectm2mFaculty() {
    }

    public Subjectm2mFaculty(int subjectId, int facultyId, int subjectUrgency) {
        this.subjectId = subjectId;
        this.facultyId = facultyId;
        this.subjectUrgency = subjectUrgency;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public int getSubjectUrgency() {
        return subjectUrgency;
    }

    public void setSubjectUrgency(int subjectUrgency) {
        this.subjectUrgency = subjectUrgency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subjectm2mFaculty that = (Subjectm2mFaculty) o;
        return subjectId == that.subjectId &&
                facultyId == that.facultyId &&
                subjectUrgency == that.subjectUrgency;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) subjectId;
        result = prime * result + (int) facultyId;
        result = prime * result + subjectUrgency;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getClass().getSimpleName());
        result.append(" Fields:\n");
        result.append("subject ID:").append(subjectId).append("\n");
        result.append("faculty ID:").append(facultyId).append("\n");
        result.append("subject urgency:").append(subjectUrgency).append("\n");
        return result.toString();
    }
}
