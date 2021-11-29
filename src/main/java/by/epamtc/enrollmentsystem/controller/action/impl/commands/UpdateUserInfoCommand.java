package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Facilitym2mUserInfo;
import by.epamtc.enrollmentsystem.model.UserInfo;
import by.epamtc.enrollmentsystem.service.template.FacilityService;
import by.epamtc.enrollmentsystem.service.template.Facilitym2mUserInfoService;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.UserInfoService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserInfoCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(UpdateUserInfoCommand.class);

    private static String GOLD_MEDAL = "Gold Medal";
    private static String ORPHAN = "Orphan";

    public void execute(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute(RequestMapping.USER_ID);
        String name = request.getParameter(RequestMapping.USER_NAME);
        String surname = request.getParameter(RequestMapping.USER_SURNAME);
        String patronymic = request.getParameter(RequestMapping.USER_PATRONYMIC);
        String photo = request.getParameter(RequestMapping.USER_PHOTO);
        String address = request.getParameter(RequestMapping.USER_ADDRESS);
        String passport = request.getParameter(RequestMapping.USER_PASSPORT);
        String orphan = request.getParameter(RequestMapping.FACILITY_ORPHAN);
        String goldMedal = request.getParameter(RequestMapping.FACILITY_GOLDMEDAL);

        UserInfo ui = new UserInfo(id,name,surname,patronymic,photo,address,passport);

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
            Router.redirect(response,URLHolder.DOCUMENTS);
        }
        catch (ServiceException e){
            LOGGER.log(Level.ERROR,e.getMessage());
            Router.redirect(response, URLHolder.MAIN_PAGE);
        }
    }
}
