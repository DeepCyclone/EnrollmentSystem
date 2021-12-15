package by.epamtc.enrollmentsystem.controller.action.impl;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.controller.mapping.SessionMapping;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.*;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.ResultService;
import by.epamtc.enrollmentsystem.service.template.UserInfoService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.*;
import java.util.Optional;


public class PreloadUserInfoCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(PreloadUserInfoCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpSession session = request.getSession(false);
            long id = (long) session.getAttribute(SessionMapping.USER_ID);
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            UserInfoService userInfoService = serviceProvider.getUserInfoService();
            ResultService resultService = serviceProvider.getResultService();
            Optional<UserInfo> ui = userInfoService.getByID(id);
            UserInfo userInfo;
            if(!ui.isPresent()){
                userInfo = new UserInfo();
                userInfo.setId(id);
                userInfoService.insertInto(userInfo);
            }
            else {
                userInfo = ui.get();
            }
            request.setAttribute(RequestMapping.USER_INFO,
                    userInfo);
            request.setAttribute(RequestMapping.BIOLOGY_MARK,
                    resultService.getResultValueBySubjectName("Biology",id));
            request.setAttribute(RequestMapping.CHEMISTRY_MARK,
                    resultService.getResultValueBySubjectName("Chemistry",id));
            request.setAttribute(RequestMapping.MATHEMATICS_MARK,
                    resultService.getResultValueBySubjectName("Mathematics",id));
            request.setAttribute(RequestMapping.PHYSICS_MARK,
                    resultService.getResultValueBySubjectName("Physics",id));
            request.setAttribute(RequestMapping.ENGLISH_MARK,
                    resultService.getResultValueBySubjectName("English",id));
            request.setAttribute(RequestMapping.RUSSIAN_MARK,
                    resultService.getResultValueBySubjectName("Russian",id));
            request.setAttribute(RequestMapping.GEOGRAPHY_MARK,
                    resultService.getResultValueBySubjectName("Geography",id));
            request.setAttribute(RequestMapping.SCHOOL_CERTIFICATE,
                    resultService.getResultValueBySubjectName("schoolCertificate",id));

            Router.forwardWithSavingURL(request,response,URLHolder.DOCUMENTS);
        }
        catch (ServiceException e){
            LOGGER.log(Level.ERROR,e.getMessage());
            Router.redirect(response, URLHolder.MAIN_PAGE);
        }
    }
}
