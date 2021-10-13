package by.epamtc.enrollmentsystem.model.dto;

import by.epamtc.enrollmentsystem.model.EducationForm;

import java.util.List;
public class FacultyForms {
    private String facultyName;
    private List<String> educationForms;

    public FacultyForms(String facultyName, List<String> educationForms) {
        this.facultyName = facultyName;
        this.educationForms = educationForms;
    }

    public FacultyForms() {
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public List<String> getEducationForms() {
        return educationForms;
    }

    public void setEducationForms(List<String> educationForms) {
        this.educationForms = educationForms;
    }
}
