    package by.epamtc.enrollmentsystem.controller.action.impl;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.UserInfoMySQL;
import by.epamtc.enrollmentsystem.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateInfoCommand implements Command {
    public void execute(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("id");
        //TODO deny update flooding
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String photo = request.getParameter("patronymic");
        String address = request.getParameter("address");
        String passport = request.getParameter("passport");
        UserInfo ui = new UserInfo(id,name,surname,patronymic,photo,address,passport);
        try {
            DAOProvider daoProvider = DAOProvider.getInstance();
            UserInfoMySQL dao = daoProvider.getUserInfoDAO();
            if(dao.hasNoteWithId(id)) {
                dao.updateRowByID(ui,id);
            }
            else {
                dao.insertInto(ui);
            }
            response.sendRedirect("documents");
        }
        catch (Exception e){
            try {
                response.sendRedirect(request.getContextPath());
            }
            catch (Exception ex){

            }
        }
    }
}
