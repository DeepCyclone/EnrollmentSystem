package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.ApplicantEnrollmentMySQL;
import by.epamtc.enrollmentsystem.dao.impl.EducationFormMySQL;
import by.epamtc.enrollmentsystem.dao.impl.FacultyMySQL;
import by.epamtc.enrollmentsystem.dao.impl.ResultMySQL;
import by.epamtc.enrollmentsystem.dao.template.ApplicantEnrollmentDAO;
import by.epamtc.enrollmentsystem.dao.template.EducationFormDAO;
import by.epamtc.enrollmentsystem.dao.template.FacultyDAO;
import by.epamtc.enrollmentsystem.dao.template.ResultDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.EducationForm;
import by.epamtc.enrollmentsystem.model.Result;
import by.epamtc.enrollmentsystem.model.Subject;
import by.epamtc.enrollmentsystem.model.dto.SelectedFacultiesInfo;
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

    public static void update(Map<String,String[]> values,long userID) throws ServiceException {
        try {
            List<Result> results = createResults(values,userID);
            List<SelectedFacultiesInfo> facultiesEducationForms = new ArrayList<>();
            List<SelectedFacultiesInfo> rowMap = createSelectedFacultiesInfo(values);
            for(SelectedFacultiesInfo entry:rowMap){
                if(checkCorrespondingMarksAvailability(results,entry.getFacultyName())){
                    facultiesEducationForms.add(entry);
                }
            }
            if (!results.isEmpty()) {
                updateResults(results,userID);
            }
            updateSelectedFacultiesInfo(facultiesEducationForms, userID);
        }
        catch (DAOException e){
            throw new ServiceException(e.getMessage(),e);
        }

        }

    private static void updateResults(List<Result> marks,long userID) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        ResultDAO resultMySQL = daoProvider.getResultDAO();
        resultMySQL.deleteByUserID(userID);
        for(Result res:marks){
            resultMySQL.insertInto(res);
        }
    }

    private static void updateSelectedFacultiesInfo(List<SelectedFacultiesInfo> infoList,long userId) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        FacultyDAO facultyMySQL = daoProvider.getFacultyDAO();
        EducationFormDAO educationFormMySQL = daoProvider.getEducationFormDAO();
        ApplicantEnrollmentDAO applicantEnrollmentMySQL = daoProvider.getApplicantEnrollmentDAO();
        applicantEnrollmentMySQL.deleteFacultiesByUserId(userId);
        for(SelectedFacultiesInfo entry:infoList){
            if(entry != null) {
                String faculty = entry.getFacultyName();
                long facultyId = facultyMySQL.getByName(faculty).get().getId();
                for (Map.Entry<String,Integer> m : entry.getEducationFormPriorityMap().entrySet()) {
                    long eduFormId = 0L;
                    Optional<EducationForm> educationForm = educationFormMySQL.getByName(m.getKey());
                    if (educationForm.isPresent()) {
                        eduFormId = educationForm.get().getId();
                    }
                    int priority = m.getValue();
                    ApplicantEnrollment ae = new ApplicantEnrollment(userId, facultyId, eduFormId, STATUS_IN_PROGRESS,priority);
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

    private static List<SelectedFacultiesInfo> createSelectedFacultiesInfo(Map<String,String[]> values){
        List<SelectedFacultiesInfo> infoList = new ArrayList<>();
        SelectedFacultiesInfo infoEntity = null;
        for (Map.Entry<String, String[]> entry : values.entrySet()) {
            String[] keyParts = entry.getKey().split(SPLIT_REGEXP);
            String[] value = entry.getValue();
            String field = keyParts[0];

            if (field.equals(RequestMapping.FACULTY_FIELD)) {
                if(infoEntity == null || !infoEntity.getFacultyName().equals(keyParts[1])) {
                    infoEntity = new SelectedFacultiesInfo();
                    infoEntity.setFacultyName(keyParts[1]);
                    infoEntity.setEducationFormPriorityMap(new HashMap<>());
                    infoList.add(infoEntity);
                }
            }
            else if (field.equals(RequestMapping.EDUCATION_FORM_FIELD)) {
                    infoEntity.getEducationFormPriorityMap().put(value[0],0);
            }
            else if(field.equals(RequestMapping.PRIORITY_FIELD)){
                if(infoEntity.getEducationFormPriorityMap().containsKey(keyParts[1]) &&
                        RegexValidator.validateFieldWithRegex(value[0],RegexHolder.PRIORITY_REGEX)){
                    int val = Integer.parseInt(value[0]);
                    infoEntity.getEducationFormPriorityMap().replace(keyParts[1], val);
                }
            }
        }
        return infoList;
    }
}
