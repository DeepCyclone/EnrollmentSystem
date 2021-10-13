package by.epamtc.enrollmentsystem.services;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.ApplicantEnrollmentMySQL;
import by.epamtc.enrollmentsystem.dao.impl.EducationFormMySQL;
import by.epamtc.enrollmentsystem.dao.impl.FacultyMySQL;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.dto.FacultyForms;

import java.util.*;

public class UserStudyingInfoBuilder {
    public static Set<FacultyForms> buildFacultiesAndEducationForms(int userId) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        ApplicantEnrollmentMySQL ae = daoProvider.getApplicantEnrollmentDAO();
        FacultyMySQL facultyMySQL = daoProvider.getFacultyDAO();
        EducationFormMySQL educationFormMySQL = daoProvider.getEducationFormDAO();
        Set<FacultyForms> facultyFormsSet = new HashSet<>();
        Map<Integer, List<Integer>> facultyForms = ae.getSelectedFacultiesByUserId(userId);
        try {
            for (Map.Entry<Integer, List<Integer>> entry : facultyForms.entrySet()) {
                int key = entry.getKey();
                List<Integer> values = entry.getValue();
                String facultyName = facultyMySQL.getByID(key).getName();//TODO не очень
                List<String> educationForms = new ArrayList<>();
                for (Integer value : values) {
                    String educationFormName = educationFormMySQL.getByID(value).getName();
                    educationForms.add(educationFormName);
                }
                FacultyForms facultyForm = new FacultyForms(facultyName, educationForms);
                facultyFormsSet.add(facultyForm);
            }
        }
        catch (Exception e){

        }
        return facultyFormsSet;

    }
}
