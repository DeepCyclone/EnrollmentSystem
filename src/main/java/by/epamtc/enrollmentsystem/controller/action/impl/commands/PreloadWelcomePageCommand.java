package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.FacultyMySQL;
import by.epamtc.enrollmentsystem.dao.impl.SystemInformationMySQL;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Faculty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class PreloadWelcomePageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("facultiesList") == null && session.getAttribute("description") == null) {//TODO request
            DAOProvider daoProvider = DAOProvider.getInstance();
            SystemInformationMySQL systemInformationMySQL = daoProvider.getSystemInformationDAO();
            FacultyMySQL facultyMySQL = daoProvider.getFacultyDAO();
            String description = null;
            List<Faculty> faculties = null;
            try {
                description = systemInformationMySQL.getValueByName("welcome_page").getValue();//TODO rename getValueByName
                faculties = facultyMySQL.getAll();
            } catch (DAOException e) {
                throw new ServletException(e.getMessage(), e);//TODO ServiceException here
            }
            session.setAttribute("facultiesList", faculties);//TODO in session?
            session.setAttribute("description", description);
        }
        response.sendRedirect(request.getContextPath()+"/welcome");
    }
}
