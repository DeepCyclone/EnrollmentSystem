package by.epamtc.enrollmentsystem.controller.action.impl;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.mapping.SessionMapping;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.service.template.Facilitym2mUserInfoService;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import com.google.gson.Gson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class PreloadFacilitiesTabCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(PreloadFacilitiesTabCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            int userID = (int)session.getAttribute(SessionMapping.USER_ID);
            Facilitym2mUserInfoService service = ServiceProvider.getInstance().getFacilitym2mUserInfoService();
            List<String> facilities = service.getUserFacilitiesNames(userID);
            Gson gson = new Gson();
            String json = gson.toJson(facilities);
            response.getWriter().write(json);
        }
        catch (ServiceException | IOException e){
            LOGGER.log(Level.ERROR,e.getMessage());
            Router.redirect(response, URLHolder.MAIN_PAGE);
        }
    }
}
