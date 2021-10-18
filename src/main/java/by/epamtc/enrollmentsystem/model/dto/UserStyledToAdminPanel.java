package by.epamtc.enrollmentsystem.model.dto;

import java.util.Set;

public class UserStyledToAdminPanel {
    public UserStyledToAdminPanel(int id, String username, String name, String surname, String patronymic, Set<StringifiedApplicantEnrollment> enrollmentInfo) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.enrollmentInfo = enrollmentInfo;
    }

    public UserStyledToAdminPanel() {
    }

    private long id;
    private String username;
    private String name;
    private String surname;
    private String patronymic;
    private Set<StringifiedApplicantEnrollment> enrollmentInfo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Set<StringifiedApplicantEnrollment> getEnrollmentInfo() {
        return enrollmentInfo;
    }

    public void setEnrollmentInfo(Set<StringifiedApplicantEnrollment> enrollmentInfo) {
        this.enrollmentInfo = enrollmentInfo;
    }
}
