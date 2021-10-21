package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.*;
import by.epamtc.enrollmentsystem.dao.templates.ResultDAO;
import by.epamtc.enrollmentsystem.dao.templates.UserInfoDAO;
import by.epamtc.enrollmentsystem.model.*;
import by.epamtc.enrollmentsystem.model.dto.MarkValue;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PreloadUserInfoCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //TODO deny updateflooding
        try {
            HttpSession session = request.getSession(false);
            long id = (long) session.getAttribute("id");
            DAOProvider daoProvider = DAOProvider.getInstance();
            UserInfoDAO userInfoMySQL = daoProvider.getUserInfoDAO();
            ResultDAO resultMySQL = daoProvider.getResultDAO();
            Optional<UserInfo> ui = userInfoMySQL.getByID(id);
            UserInfo userInfo = null;
            if(!ui.isPresent()){
                userInfo = new UserInfo();
                userInfo.setId(id);
                userInfoMySQL.insertInto(userInfo);
            }
            else {
                userInfo = ui.get();
            }
            request.setAttribute("userinfo",userInfo);
            request.setAttribute("biologyMark",resultMySQL.getResultValueBySubjectName("Biology",id));
            request.setAttribute("chemistryMark",resultMySQL.getResultValueBySubjectName("Chemistry",id));
            request.setAttribute("mathematicsMark",resultMySQL.getResultValueBySubjectName("Mathematics",id));
            request.setAttribute("physicsMark",resultMySQL.getResultValueBySubjectName("Physics",id));
            request.setAttribute("englishMark",resultMySQL.getResultValueBySubjectName("English",id));
            request.setAttribute("russianMark",resultMySQL.getResultValueBySubjectName("Russian",id));
            request.setAttribute("geographyMark",resultMySQL.getResultValueBySubjectName("Geography",id));

            request.getRequestDispatcher( "/documents").forward(request,response);
        }
        catch (Exception e){
            response.sendRedirect(request.getContextPath());
        }
    }
}
