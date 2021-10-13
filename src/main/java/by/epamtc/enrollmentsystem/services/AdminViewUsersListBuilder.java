package by.epamtc.enrollmentsystem.services;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.UserInfoMySQL;
import by.epamtc.enrollmentsystem.dao.impl.UserMySQL;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.model.UserInfo;
import by.epamtc.enrollmentsystem.model.dto.UserStyledToAdminPanel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdminViewUsersListBuilder {
    public static List<UserStyledToAdminPanel> buildStyledList() throws DAOException {
        DAOProvider dao = DAOProvider.getInstance();
        UserMySQL ums = dao.getUserDAO();
        UserInfoMySQL ui = dao.getUserInfoDAO();
        List<UserStyledToAdminPanel> us = new LinkedList<>();
        List<User> users = ums.getAll();
        for (User usr : users) {
            UserStyledToAdminPanel userStyledToAdminPanel = new UserStyledToAdminPanel();
            userStyledToAdminPanel.setId(usr.getId());
            UserInfo info = ui.getByID(usr.getId());
            userStyledToAdminPanel.setUsername(usr.getLogin());
            userStyledToAdminPanel.setFacultyName(new ArrayList<>());
            if(info !=null) {
                userStyledToAdminPanel.setName(info.getName());
                userStyledToAdminPanel.setSurname(info.getSurname());
                userStyledToAdminPanel.setPatronymic(info.getPatronymic());
            }
            us.add(userStyledToAdminPanel);
        }
        return us;
    }
}
