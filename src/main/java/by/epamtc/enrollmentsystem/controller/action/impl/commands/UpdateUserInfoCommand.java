    package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.FacilityMySQL;
import by.epamtc.enrollmentsystem.dao.impl.Facilitym2mUserInfoMySQL;
import by.epamtc.enrollmentsystem.dao.impl.UserInfoMySQL;
import by.epamtc.enrollmentsystem.model.Facilitym2mUserInfo;
import by.epamtc.enrollmentsystem.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserInfoCommand implements Command {
    public void execute(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("id");
        //TODO deny update flooding
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String photo = request.getParameter("patronymic");//TODO сделать загрузку фото если успею
        String address = request.getParameter("address");
        String passport = request.getParameter("passport");
        String orphan = request.getParameter("orphanFacility");
        String goldMedal = request.getParameter("goldmedalFacility");
        UserInfo ui = new UserInfo(id,name,surname,patronymic,photo,address,passport);
        try {
            DAOProvider daoProvider = DAOProvider.getInstance();
            UserInfoMySQL dao = daoProvider.getUserInfoDAO();
            FacilityMySQL facilityMySQL = daoProvider.getFacilityDAO();
            Facilitym2mUserInfoMySQL d = daoProvider.getFacilitym2mUserInfoMySQL();
            if(dao.hasNoteWithId(id)) {
                dao.updateRowByID(ui,id);
            }
            else {
                dao.insertInto(ui);
            }

            Facilitym2mUserInfo facilitym2mUserInfo;
            if("on".equals(goldMedal)){
                facilitym2mUserInfo = new Facilitym2mUserInfo();
                int facilityId = facilityMySQL.getIdByName("Gold Medal");
                facilitym2mUserInfo.setFacilityId(facilityId);
                facilitym2mUserInfo.setUserInfoUserId(id);
                d.insertInto(facilitym2mUserInfo);
            }
            else{

            }
            if("on".equals(orphan)){
                facilitym2mUserInfo = new Facilitym2mUserInfo();
                int facilityId = d.getIdByName("Orphan");
                facilitym2mUserInfo.setFacilityId(facilityId);
                facilitym2mUserInfo.setUserInfoUserId(id);
                d.insertInto(facilitym2mUserInfo);
            }
            else{

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
