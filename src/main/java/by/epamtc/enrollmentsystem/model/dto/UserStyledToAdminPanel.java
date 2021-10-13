package by.epamtc.enrollmentsystem.model.dto;

import java.util.List;

public class UserStyledToAdminPanel {
    public UserStyledToAdminPanel(int id, String username, String name, String surname, String patronymic, List<String> faculties, String educationFormName, String enrollmentStatus) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.faculties = faculties;
        this.educationFormName = educationFormName;
        this.enrollmentStatus = enrollmentStatus;
    }

    public UserStyledToAdminPanel() {
    }

    private int id;
    private String username;
    private String name;
    private String surname;
    private String patronymic;
    private List<String> faculties;
    private String educationFormName;
    private String enrollmentStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public List<String> getFaculties() {
        return faculties;
    }

    public void setFacultyName(List<String>  faculties) {
        this.faculties = faculties;
    }

    public String getEducationFormName() {
        return educationFormName;
    }

    public void setEducationFormName(String educationFormName) {
        this.educationFormName = educationFormName;
    }

    public String getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }
}
