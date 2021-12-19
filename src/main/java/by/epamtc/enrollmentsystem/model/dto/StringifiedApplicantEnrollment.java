package by.epamtc.enrollmentsystem.model.dto;

import by.epamtc.enrollmentsystem.model.EducationForm;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class StringifiedApplicantEnrollment implements Serializable {

    private String facultyName;
    private Map<String,String> educationFormStatuses;

    public StringifiedApplicantEnrollment() {
    }

    public StringifiedApplicantEnrollment(String facultyName, Map<String, String> educationFormStatuses) {
        this.facultyName = facultyName;
        this.educationFormStatuses = educationFormStatuses;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public Map<String, String> getEducationFormStatuses() {
        return educationFormStatuses;
    }

    public void setEducationFormStatuses(Map<String, String> educationFormStatuses) {
        this.educationFormStatuses = educationFormStatuses;
    }
}