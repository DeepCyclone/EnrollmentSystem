package by.epamtc.enrollmentsystem.model.dto;

import by.epamtc.enrollmentsystem.model.EducationForm;

import java.util.List;
import java.util.Map;

public class StringifiedApplicantEnrollment {

    private String facultyName;
    private Map<String,Integer> educationFormPriorityMap;
    private Map<String,String> educationStatusesMap;

    public StringifiedApplicantEnrollment() {
    }

    public StringifiedApplicantEnrollment(String facultyName,Map<String,Map<String,Integer>> facultyInfo) {
        this.facultyName = facultyName;
        this.facultyInfo = facultyInfo;
    }


    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public Map<String,Map<String,Integer>> getFacultyInfo() {
        return facultyInfo;
    }

    public void setFacultyInfo(Map<String,Map<String,Integer>> facultyInfo) {
        this.facultyInfo = facultyInfo;
    }
}
