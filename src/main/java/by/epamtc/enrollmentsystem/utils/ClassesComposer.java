package by.epamtc.enrollmentsystem.utils;

import by.epamtc.enrollmentsystem.model.*;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassesComposer {
    public static EducationForm composeEducationForm(ResultSet rs) throws SQLException {
        EducationForm educationForm = new EducationForm();
        while (rs.next()){
            educationForm.setId(rs.getInt(1));
            educationForm.setName(rs.getString(2));
        }
        return educationForm;
    }
    public static EnrollmentStatus composeEnrollmentStatus(ResultSet rs){
        return null;
    }
    public static Facility composeFacility(ResultSet rs){
        return null;
    }
    public static Faculty composeFaculty(ResultSet rs) throws SQLException {//TODO Exceptions Hierarchy
        Faculty faculty = new Faculty();
        faculty.setName(rs.getString(2));
        faculty.setBudgetAdmissionPlan(rs.getInt(3));
        faculty.setPaidAdmissionPlan(rs.getInt(4));
        faculty.setDescription(rs.getString(5));
        return faculty;
    }
    public static Role composeRole(ResultSet rs){
        return null;
    }
    public static Subject composeSubject(ResultSet rs){
        return null;
    }
    public static SystemInformation composeSystemInformation(ResultSet rs){
        return null;
    }
    public static User composeUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(1));
        user.setLogin(rs.getString(2));
        user.setPassword(rs.getString(3).getBytes(StandardCharsets.UTF_8));//TODO логика в том, чтобы String существовало как можно меньше в памяти?
        user.setEmail(rs.getString(4));
        user.setRoleId(rs.getInt(5));
        return user;
    }
    public static UserInfo composeUserInfo(ResultSet rs) throws SQLException{
        UserInfo ui = new UserInfo();
        ui.setId(rs.getInt(1));
        ui.setName(rs.getString(2));
        ui.setSurname(rs.getString(3));
        ui.setPatronymic(rs.getString(4));
        ui.setPhotoPath(rs.getString(5));
        ui.setAddress(rs.getString(6));
        ui.setPassport(rs.getString(7));
        return ui;
    }
}
