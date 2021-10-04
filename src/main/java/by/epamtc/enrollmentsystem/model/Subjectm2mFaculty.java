package by.epamtc.enrollmentsystem.model;

public class Subjectm2mFaculty {

    public Subjectm2mFaculty(int subjectId, int facultyId, int subjectUrgency) {
        this.subjectId = subjectId;
        this.facultyId = facultyId;
        this.subjectUrgency = subjectUrgency;
    }

    public Subjectm2mFaculty() {
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getSubjectUrgency() {
        return subjectUrgency;
    }

    public void setSubjectUrgency(int subjectUrgency) {
        this.subjectUrgency = subjectUrgency;
    }

    private int subjectId;
    private int facultyId;
    private int subjectUrgency;
}
