package by.epamtc.enrollmentsystem.controller.action.impl;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.FacultyMySQL;
import by.epamtc.enrollmentsystem.dao.impl.UserInfoMySQL;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.model.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class UploadUserInfoCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //TODO deny updateflooding
        try {
            HttpSession session = request.getSession(false);
            int id = (int) session.getAttribute("id");
            DAOProvider daoProvider = DAOProvider.getInstance();
            UserInfoMySQL userInfoDAO = daoProvider.getUserInfoDAO();
            FacultyMySQL facultyDAO = daoProvider.getFacultyDAO();
            List<Faculty> faculties = facultyDAO.getAll();
            UserInfo ui = userInfoDAO.getByID(id);
            request.setAttribute("userinfo",ui);
            request.getRequestDispatcher( "/documents").forward(request,response);
        }
        catch (Exception e){
            response.sendRedirect(request.getContextPath());
        }
    }
}
