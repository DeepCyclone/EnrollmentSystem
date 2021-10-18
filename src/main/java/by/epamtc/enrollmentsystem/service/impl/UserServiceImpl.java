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

import java.util.*;

public class UserServiceImpl implements UserService {
    @Override
    public int getIdByLogin(String login) throws ServiceException {
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
    public int getRoleByLogin(String login) throws ServiceException {
        try {
            UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
            return userDAO.getRoleByLogin(login);
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public List<UserStyledToAdminPanel> getStyledUserInfo() throws ServiceException {//TODO куда это?
        try {
            DAOProvider daoProvider = DAOProvider.getInstance();
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            UserDAO userMySQL = daoProvider.getUserDAO();
            UserInfoDAO userInfoMySQL = daoProvider.getUserInfoDAO();
            ApplicantEnrollmentService applicantEnrollmentService = serviceProvider.getApplicantEnrollmentService();
            List<UserStyledToAdminPanel> us = new LinkedList<>();
            List<User> users = userMySQL.getAll();
            for (User usr : users) {
                UserStyledToAdminPanel userStyledToAdminPanel = new UserStyledToAdminPanel();
                Set<StringifiedApplicantEnrollment> stringifiedApplicantEnrollmentSet = applicantEnrollmentService.getStringifiedTable(usr.getId());
                userStyledToAdminPanel.setId(usr.getId());
                UserInfo info = userInfoMySQL.getByID(usr.getId());
                userStyledToAdminPanel.setUsername(usr.getLogin());
                userStyledToAdminPanel.setEnrollmentInfo(stringifiedApplicantEnrollmentSet);
                if (info != null) {
                    userStyledToAdminPanel.setName(info.getName());
                    userStyledToAdminPanel.setSurname(info.getSurname());
                    userStyledToAdminPanel.setPatronymic(info.getPatronymic());
                }
                us.add(userStyledToAdminPanel);
            }
            return us;
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }
}
