package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.FacilityMySQL;
import by.epamtc.enrollmentsystem.dao.impl.Facilitym2mUserInfoMySQL;
import by.epamtc.enrollmentsystem.dao.impl.UserInfoMySQL;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Facilitym2mUserInfo;
import by.epamtc.enrollmentsystem.model.UserInfo;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.UserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserInfoCommand implements Command {

    private static String GOLD_MEDAL = "Gold Medal";
    private static String ORPHAN = "Orphan";

    public void execute(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("id");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String photo = request.getParameter("patronymic");//TODO сделать загрузку фото если успею
        String address = request.getParameter("address");
        String passport = request.getParameter("passport");
        String orphan = request.getParameter("orphanFacility");
        String goldMedal = request.getParameter("goldmedalFacility");
        UserInfo ui = new UserInfo(id,name,surname,patronymic,photo,address,passport);//TODO nulls?
        try {
            DAOProvider daoProvider = DAOProvider.getInstance();
            UserInfoService userInfoService = ServiceProvider.getInstance().getUserInfoService();
            FacilityMySQL facilityMySQL = daoProvider.getFacilityDAO();
            Facilitym2mUserInfoMySQL facilitym2mUserInfoMySQL = daoProvider.getFacilitym2mUserInfoMySQL();
            if(userInfoService.hasNoteWithId(id)) {
                userInfoService.updateRowByID(ui);
            }
            else {
                userInfoService.insertInto(ui);
            }
            long facilityId = 0;
            Facilitym2mUserInfo facilitym2mUserInfo = new Facilitym2mUserInfo();
            if("on".equals(goldMedal)){
                facilityId = facilityMySQL.getIdByName(GOLD_MEDAL);
            }
            if("on".equals(orphan)){
                facilityId = facilityMySQL.getIdByName(ORPHAN);
            }

            facilitym2mUserInfo.setFacilityId(facilityId);
            facilitym2mUserInfo.setUserInfoUserId(id);
            facilitym2mUserInfoMySQL.insertInto(facilitym2mUserInfo);

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
