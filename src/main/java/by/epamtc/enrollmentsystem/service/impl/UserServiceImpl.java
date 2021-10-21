package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.templates.*;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.*;
import by.epamtc.enrollmentsystem.model.dto.StringifiedApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.dto.UserCredentials;
import by.epamtc.enrollmentsystem.model.dto.UserStyledToAdminPanel;
import by.epamtc.enrollmentsystem.service.ApplicantEnrollmentService;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.UserService;
import by.epamtc.enrollmentsystem.service.validators.RegexHolders;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {
    @Override
    public long getIdByLogin(String login) throws ServiceException {
        try {
            UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
            return userDAO.getIdByLogin(login);
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public UserCredentials getCredentials(String login) throws ServiceException {
        try {
            UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
            return userDAO.getCredentials(login);
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public long getRoleByLogin(String login) throws ServiceException {
        try {
            UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
            return userDAO.getRoleByLogin(login);
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public List<UserStyledToAdminPanel> getStyledUserInfo(int from,int offset) throws ServiceException {//TODO куда это?
        try {
            DAOProvider daoProvider = DAOProvider.getInstance();
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            UserDAO userMySQL = daoProvider.getUserDAO();
            UserInfoDAO userInfoMySQL = daoProvider.getUserInfoDAO();
            ApplicantEnrollmentService applicantEnrollmentService = serviceProvider.getApplicantEnrollmentService();
            List<UserStyledToAdminPanel> us = new LinkedList<>();
            List<User> users = userMySQL.getEntitiesRange(from,offset);
            for (User usr : users) {
                UserStyledToAdminPanel userStyledToAdminPanel = new UserStyledToAdminPanel();
                Set<StringifiedApplicantEnrollment> stringifiedApplicantEnrollmentSet = applicantEnrollmentService.getStringifiedTable(usr.getId());
                userStyledToAdminPanel.setId(usr.getId());
                Optional<UserInfo> info = userInfoMySQL.getByID(usr.getId());
                userStyledToAdminPanel.setUsername(usr.getLogin());
                userStyledToAdminPanel.setEnrollmentInfo(stringifiedApplicantEnrollmentSet);
                if (info.isPresent()) {
                    userStyledToAdminPanel.setName(info.get().getName());
                    userStyledToAdminPanel.setSurname(info.get().getSurname());
                    userStyledToAdminPanel.setPatronymic(info.get().getPatronymic());
                }
                us.add(userStyledToAdminPanel);
            }
            return us;
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public boolean isValidData(String login, String email, String password) {
        return  validateFieldWithRegex(login,RegexHolders.USERNAME_REGEX) &&
                validateFieldWithRegex(email,RegexHolders.EMAIL_REGEX) &&
                validateFieldWithRegex(password,RegexHolders.PASSWORD_REGEX);
    }

    @Override
    public int getNumberOfRecords() throws ServiceException {
        try {
            UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
            return userDAO.getRecordsNumber();
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    private boolean validateFieldWithRegex(String field,String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(field);
        return matcher.matches();
    }
}
