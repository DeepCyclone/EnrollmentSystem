package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.RequestParams;
import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.model.Facilitym2mUserInfo;
import by.epamtc.enrollmentsystem.model.UserInfo;
import by.epamtc.enrollmentsystem.service.templates.FacilityService;
import by.epamtc.enrollmentsystem.service.templates.Facilitym2mUserInfoService;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.templates.UserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserInfoCommand implements Command {

    private static String GOLD_MEDAL = "Gold Medal";
    private static String ORPHAN = "Orphan";

    public void execute(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute(RequestParams.USER_ID);
        String name = request.getParameter(RequestParams.USER_NAME);
        String surname = request.getParameter(RequestParams.USER_SURNAME);
        String patronymic = request.getParameter(RequestParams.USER_PATRONYMIC);
        String photo = request.getParameter(RequestParams.USER_PHOTO);//TODO сделать загрузку фото если успею
        String address = request.getParameter(RequestParams.USER_ADDRESS);
        String passport = request.getParameter(RequestParams.USER_PASSPORT);
        String orphan = request.getParameter(RequestParams.FACILITY_ORPHAN);
        String goldMedal = request.getParameter(RequestParams.FACILITY_GOLDMEDAL);

        UserInfo ui = new UserInfo(id,name,surname,patronymic,photo,address,passport);//TODO nulls?

        try {
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            UserInfoService userInfoService = serviceProvider.getUserInfoService();
            FacilityService facilityService = serviceProvider.getFacilityService();
            Facilitym2mUserInfoService facilitym2mUserInfoService = serviceProvider.getFacilitym2mUserInfoService();
            if(userInfoService.hasNoteWithId(id)) {
                userInfoService.updateRowByID(ui);
            }
            else {
                userInfoService.insertInto(ui);
            }


            long facilityId = 0;
            Facilitym2mUserInfo facilitym2mUserInfo = new Facilitym2mUserInfo();
            if("on".equals(goldMedal)){
                facilityId = facilityService.getByName(GOLD_MEDAL).get().getId();
            }
            if("on".equals(orphan)){
                facilityId = facilityService.getByName(ORPHAN).get().getId();
            }

            facilitym2mUserInfo.setFacilityId(facilityId);
            facilitym2mUserInfo.setUserInfoUserId(id);
            facilitym2mUserInfoService.insertInto(facilitym2mUserInfo);

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
