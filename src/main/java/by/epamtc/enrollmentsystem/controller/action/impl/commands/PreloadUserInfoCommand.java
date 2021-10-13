package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.*;
import by.epamtc.enrollmentsystem.model.*;
import by.epamtc.enrollmentsystem.model.dto.MarkValue;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class PreloadUserInfoCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //TODO deny updateflooding
        try {
            HttpSession session = request.getSession(false);
            int id = (int) session.getAttribute("id");
            DAOProvider daoProvider = DAOProvider.getInstance();
            UserInfoMySQL userInfoDAO = daoProvider.getUserInfoDAO();
            ResultMySQL resultMySQL = daoProvider.getResultDAO();
            List<MarkValue> mv = resultMySQL.retrieveResultByUserId(id);
            FacilityMySQL facilityMySQL = daoProvider.getFacilityDAO();
            UserInfo ui = userInfoDAO.getByID(id);
            request.setAttribute("userinfo",ui);
            request.setAttribute("marksValues",mv);
            request.getRequestDispatcher( "/documents").forward(request,response);
        }
        catch (Exception e){
            response.sendRedirect(request.getContextPath());
        }
    }
}
