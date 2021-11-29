package by.epamtc.enrollmentsystem.service.util;

import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.ApplicantEnrollmentMySQL;
import by.epamtc.enrollmentsystem.dao.impl.EducationFormMySQL;
import by.epamtc.enrollmentsystem.dao.impl.FacultyMySQL;
import by.epamtc.enrollmentsystem.dao.impl.ResultMySQL;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.EducationForm;
import by.epamtc.enrollmentsystem.model.Result;
import by.epamtc.enrollmentsystem.model.Subject;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.SubjectService;
import by.epamtc.enrollmentsystem.service.template.Subjectm2mFacultyService;
import by.epamtc.enrollmentsystem.service.validator.RegexHolder;
import by.epamtc.enrollmentsystem.service.validator.RegexValidator;
import by.epamtc.enrollmentsystem.service.validator.ResultValidator;
import javafx.util.Pair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/*
 * An utility, that parses,checks and update user's marks and selected faculties
 */

public class MarksAndFacultiesUpdater {

    private static final int STATUS_IN_PROGRESS = 1;
    private static final String SPLIT_REGEXP = ":";

    public static void update(Map<String,String[]> values,long userID) throws ServiceException {//TODO rename
        try {
            List<Result> results = createResults(values,userID);
            Map<String,List<String>> facultiesEducationForms = new HashMap<>();
            Map<String,List<String>> rowMap = createFacultiesEducationForms(values);
            for(Map.Entry<String,List<String>> entry:rowMap.entrySet()){
                if(checkCorrespondingMarksAvailability(results,entry.getKey())){
                    facultiesEducationForms.put(entry.getKey(),entry.getValue());
                }
            }
            if (!results.isEmpty()) {
                updateDatabaseMarks(results,userID);
            }
            updateDatabaseFacultiesEducationForms(facultiesEducationForms, userID);
        }
        catch (DAOException e){
            throw new ServiceException(e.getMessage(),e);
        }

    }

    private static void updateDatabaseMarks(List<Result> marks,long userID) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        ResultMySQL resultMySQL = daoProvider.getResultDAO();
        resultMySQL.deleteByUserID(userID);
        for(Result res:marks){
            resultMySQL.insertInto(res);
        }
    }

    private static void updateDatabaseFacultiesEducationForms(Map<String,List<String>> facultiesEducationForms,long userId) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        FacultyMySQL facultyMySQL = daoProvider.getFacultyDAO();
        EducationFormMySQL educationFormMySQL = daoProvider.getEducationFormDAO();
        ApplicantEnrollmentMySQL applicantEnrollmentMySQL = daoProvider.getApplicantEnrollmentDAO();
        applicantEnrollmentMySQL.deleteFacultiesByUserId(userId);
        for(Map.Entry<String,List<String>> entry:facultiesEducationForms.entrySet()){
            if(entry.getValue() != null) {
                String faculty = entry.getKey();
                long facultyId = facultyMySQL.getByName(faculty).get().getId();
                for (String eduForm : entry.getValue()) {
                    long eduFormId = 0L;
                    Optional<EducationForm> educationForm = educationFormMySQL.getByName(eduForm);
                    if (educationForm.isPresent()) {
                        eduFormId = educationForm.get().getId();
                    }
                    ApplicantEnrollment ae = new ApplicantEnrollment(userId, facultyId, eduFormId, STATUS_IN_PROGRESS);
                    applicantEnrollmentMySQL.insertInto(ae);
                }
            }
        }
    }
    private static boolean checkCorrespondingMarksAvailability(List<Result> marks,String subjectName) throws ServiceException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        Subjectm2mFacultyService service = serviceProvider.getSubjectm2mFacultyService();
        Map<String,List<String>> facCorr = service.getFacultiesCorrespondingToSubjects();
        SubjectService subjectService = serviceProvider.getSubjectService();
        List<String> subjects = facCorr.get(subjectName);
        for(Result res:marks){
            String subName = subjectService.getByID(res.getSubjectId()).get().getName();
            if(subjects.contains(subName)){
                subjects.remove(subName);
            }
        }
        return subjects.size() == 0;
    }

    private static List<Result> createResults(Map<String,String[]> values,long userID) throws DAOException {
        List<Result> marks = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : values.entrySet()) {

            String[] keyParts = entry.getKey().split(SPLIT_REGEXP);
            String[] value = entry.getValue();
            String field = keyParts[0];

            if (field.equals(RequestMapping.MARK_FIELD)) {
                String markName = keyParts[1];
                String markValue = value[0];
                Optional<Subject> subject = DAOProvider.getInstance().getSubjectDAO().getByName(markName);
                if (subject.isPresent()) {
                    long markId = subject.get().getId();
                    if (!"".equals(markValue) &&
                            (ResultValidator.validateCertificate(markName, markValue) ||
                            ResultValidator.validateTestingMark(markName, markValue))) {
                        int mark = Integer.parseInt(markValue);
                        Result result = new Result(markId, userID, mark);
                        marks.add(result);
                    }
                }
            }
        }
        return marks;
    }

    private static Map<String, List<String>> createFacultiesEducationForms(Map<String,String[]> values){
        Map<String, List<String>> facultiesEducationForms = new HashMap<>();
        for (Map.Entry<String, String[]> entry : values.entrySet()) {
            String[] keyParts = entry.getKey().split(SPLIT_REGEXP);
            String[] value = entry.getValue();
            String field = keyParts[0];

            if (field.equals(RequestMapping.FACULTY_FIELD) && !facultiesEducationForms.containsKey(keyParts[1])) {
                    facultiesEducationForms.put(keyParts[1], new ArrayList<>());
            }
            else if (field.equals(RequestMapping.EDUCATION_FORM_FIELD)) {
                for (String val : value) {
                    if (facultiesEducationForms.containsKey(keyParts[1])) {
                        facultiesEducationForms.get(keyParts[1]).add(val);
                    }
                }
            }
        }
        return facultiesEducationForms;
    }
}
