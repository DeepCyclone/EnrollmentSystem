package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.template.*;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.*;
import by.epamtc.enrollmentsystem.model.dto.StringifiedApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.dto.UserCredential;
import by.epamtc.enrollmentsystem.model.dto.UserStyledToAdminPanel;
import by.epamtc.enrollmentsystem.service.template.ApplicantEnrollmentService;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.UserService;
import by.epamtc.enrollmentsystem.service.validator.RegexHolders;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static by.epamtc.enrollmentsystem.service.validator.RegexValidator.validateFieldWithRegex;

public class UserServiceImpl implements UserService {

    @Override
    public Optional<User> getByLogin(String login) throws ServiceException {
        try {
                UserDAO dao = DAOProvider.getInstance().getUserDAO();
                return dao.getByLogin(login);
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public UserCredential getCredentials(String login) throws ServiceException {
        try {
            UserCredential credentials = null;
            UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
            Optional<User> user = userDAO.getByLogin(login);
            if(user.isPresent()){
                credentials = new UserCredential();
                credentials.setPassword(new String(user.get().getPassword(), StandardCharsets.UTF_8));
                credentials.setLogin(user.get().getLogin());
            }
            return credentials;//TODO empty fields
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
            return userDAO.getNumberOfRecords();
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public void insertInto(User data) throws ServiceException {
        try {
            UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
            userDAO.insertInto(data);
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public Optional<User> getByID(long id) throws ServiceException {
        try {
            UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
            return userDAO.getByID(id);
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public void updateRowByID(User note) throws ServiceException {
        try {
            UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
            userDAO.updateRowByID(note);
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }
}
