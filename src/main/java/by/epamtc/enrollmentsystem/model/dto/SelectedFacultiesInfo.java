package by.epamtc.enrollmentsystem.model.dto;

import java.util.Map;

public class SelectedFacultiesInfo {

    private String facultyName;
    private Map<String,Integer> educationFormPriorityMap;

    public SelectedFacultiesInfo() {
    }

    public SelectedFacultiesInfo(String facultyName, Map<String, Integer> educationFormPriorityMap) {
        this.facultyName = facultyName;
        this.educationFormPriorityMap = educationFormPriorityMap;
    }

    public Map<String, Integer> getEducationFormPriorityMap() {
        return educationFormPriorityMap;
    }

    public void setEducationFormPriorityMap(Map<String, Integer> educationFormPriorityMap) {
        this.educationFormPriorityMap = educationFormPriorityMap;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}
