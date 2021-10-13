package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.Subjectm2mFacultyMySQL;
import by.epamtc.enrollmentsystem.dao.impl.UserInfoMySQL;
import by.epamtc.enrollmentsystem.dao.impl.UserMySQL;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.model.UserInfo;
import by.epamtc.enrollmentsystem.model.dto.UserStyledToAdminPanel;
import by.epamtc.enrollmentsystem.services.AdminViewUsersListBuilder;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PreloadAdminPanelCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<UserStyledToAdminPanel> us = AdminViewUsersListBuilder.buildStyledList();
            request.setAttribute("users", us);
            request.getRequestDispatcher("/documents").forward(request, response);
        }
        catch (Exception e){

        }
    }
}