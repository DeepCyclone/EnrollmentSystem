package by.epamtc.enrollmentsystem.model;

public class Subjectm2mFaculty {

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
}
