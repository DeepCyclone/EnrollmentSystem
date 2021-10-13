package by.epamtc.enrollmentsystem.services;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.ApplicantEnrollmentMySQL;
import by.epamtc.enrollmentsystem.dao.impl.ResultMySQL;
import by.epamtc.enrollmentsystem.dao.impl.UserInfoMySQL;
import by.epamtc.enrollmentsystem.dao.templates.UserInfoTempl;
import by.epamtc.enrollmentsystem.model.Result;
import by.epamtc.enrollmentsystem.model.UserInfo;
import by.epamtc.enrollmentsystem.services.comparators.ResultComparator;

import java.util.LinkedList;
import java.util.List;

public class EnrollmentService {
    public static void makeEnrollment(){
        try {
            DAOProvider daoProvider = DAOProvider.getInstance();
            ApplicantEnrollmentMySQL apEnrMySQL = daoProvider.getApplicantEnrollmentDAO();
            ResultMySQL resultMySQL = daoProvider.getResultDAO();
            UserInfoMySQL userInfoMySQL = daoProvider.getUserInfoDAO();
            List<Result> results = new LinkedList<>();//sorting and adding
            List<UserInfo> infos = userInfoMySQL.getAll();
            for(UserInfo info:infos){
//                results.add(resultMySQL.getByID())
            }
            results.sort(new ResultComparator());//TODO закинуть comparable внутрь
//            for()
        }
        catch (Exception e){

        }
    }
}
