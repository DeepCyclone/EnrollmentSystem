package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.DAOTemplate;
import by.epamtc.enrollmentsystem.dao.impl.ApplicantEnrollmentMySQL;
import by.epamtc.enrollmentsystem.dao.impl.EducationFormMySQL;
import by.epamtc.enrollmentsystem.dao.impl.FacultyMySQL;
import by.epamtc.enrollmentsystem.dao.impl.ResultMySQL;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class MarksAndFacultiesUpdater {
    private static final String MARK_FIELD = "mark";
    private static final String FACULTY_FIELD = "Faculty";
    private static final String EDUCATION_FORM_FIELD = "e_form";
    private static final int STATUS_IN_PROGRESS = 1;

    public static void update(Map<String,String[]> pair, HttpServletRequest request) throws ServiceException {//TODO rename
        HttpSession session = request.getSession(false);
        int id = (int)session.getAttribute("id");
        List<Result> marks = new ArrayList<>();
        Map<String,List<String>> facultiesEducationForms = new HashMap<>();
        for (Map.Entry<String,String[]> entry : pair.entrySet()) {

            String[] value = entry.getValue();
            String[] keyParts = entry.getKey().split(":");
            try {
                if(keyParts[0].equals(MARK_FIELD)) {
                    int subId = Character.getNumericValue(keyParts[1].charAt(0));//TODO Exception
                    int mark = Integer.parseInt(value[0]);
                    Result result = new Result(subId,id,mark);
                    marks.add(result);
                }
                else if(keyParts[0].equals(FACULTY_FIELD)){
                    if(!facultiesEducationForms.containsKey(keyParts[1])){
                        facultiesEducationForms.put(keyParts[1],new ArrayList<>());
                    }
                }
                else if(keyParts[0].equals(EDUCATION_FORM_FIELD)){
                    for(String val:value){
                        facultiesEducationForms.get(keyParts[1]).add(val);
                    }
                }
                dalUpdater(marks,facultiesEducationForms,id);
            }
            catch (DAOException exception){
                throw new ServiceException(exception.getMessage(),exception);
            }
        }
    }
    private static void dalUpdater(List<Result> marks,Map<String,List<String>> facultiesEducationForms,int userId) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        ResultMySQL resultMySQL= daoProvider.getResultDAO();
        FacultyMySQL facultyMySQL = daoProvider.getFacultyDAO();
        EducationFormMySQL educationFormMySQL = daoProvider.getEducationFormDAO();
        ApplicantEnrollmentMySQL applicantEnrollmentMySQL = daoProvider.getApplicantEnrollmentDAO();
        for(Result res:marks){
            if(resultMySQL.userHasMarkOnSubject(res.getUserInfoUserId(),res.getSubjectId())) {
                resultMySQL.updateUserResult(res);
            }
            else {
                resultMySQL.insertInto(res);
            }
        }
        applicantEnrollmentMySQL.deleteFacultiesByUserId(userId);
        for(Map.Entry<String,List<String>> entry:facultiesEducationForms.entrySet()){
            String faculty = entry.getKey();
            int facultyId = facultyMySQL.getIdByName(faculty);
            int enrollmentStatus = STATUS_IN_PROGRESS;
            for(String eduForm:entry.getValue()){
                int eduFormId = educationFormMySQL.getIdByName(eduForm);
                ApplicantEnrollment ae = new ApplicantEnrollment(userId,facultyId,eduFormId,enrollmentStatus);
                applicantEnrollmentMySQL.insertInto(ae);
            }
        }
    }
}
