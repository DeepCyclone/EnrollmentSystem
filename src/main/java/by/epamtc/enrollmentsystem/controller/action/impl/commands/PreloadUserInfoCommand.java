package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.template.ResultDAO;
import by.epamtc.enrollmentsystem.dao.template.UserInfoDAO;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.*;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.ResultService;
import by.epamtc.enrollmentsystem.service.template.UserInfoService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

public class PreloadUserInfoCommand implements Command {
    private static Logger logger = LogManager.getLogger(PreloadUserInfoCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpSession session = request.getSession(false);
            long id = (long) session.getAttribute("id");
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
            request.setAttribute("userinfo",userInfo);//mapping
            request.setAttribute("biologyMark",resultService.getResultValueBySubjectName("Biology",id));
            request.setAttribute("chemistryMark",resultService.getResultValueBySubjectName("Chemistry",id));
            request.setAttribute("mathematicsMark",resultService.getResultValueBySubjectName("Mathematics",id));
            request.setAttribute("physicsMark",resultService.getResultValueBySubjectName("Physics",id));
            request.setAttribute("englishMark",resultService.getResultValueBySubjectName("English",id));
            request.setAttribute("russianMark",resultService.getResultValueBySubjectName("Russian",id));
            request.setAttribute("geographyMark",resultService.getResultValueBySubjectName("Geography",id));
            request.setAttribute("schoolCertificate",resultService.getResultValueBySubjectName("schoolCertificate",id));

            request.getRequestDispatcher( "/documents").forward(request,response);
        }
        catch (ServletException | IOException | ServiceException e){
            logger.log(Level.ERROR,e.getMessage());
            response.sendRedirect(request.getContextPath());
        }
    }
}
