package by.epamtc.enrollmentsystem.dao.templates;

import by.epamtc.enrollmentsystem.dao.DAOTemplate;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;

import java.util.List;
import java.util.Map;

public interface ApplicantEnrollmentTempl {
    Map<Integer, List<Integer>> getSelectedFacultiesByUserId(int userId);
    void updateEducationForm(int userId,int facultyId,int educationFormId);
    boolean userHasFaculty(int userId,int facultyId);
    void deleteFacultiesByUserId(int userId);
}
