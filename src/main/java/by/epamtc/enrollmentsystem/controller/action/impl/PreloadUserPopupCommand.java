package by.epamtc.enrollmentsystem.controller.action.impl;


import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.dto.MarkValue;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PreloadUserPopupCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(PreloadUserPopupCommand.class);
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceProvider provider = ServiceProvider.getInstance();
            long userID = Long.parseLong(request.getParameter(RequestMapping.SELECTED_USER_ID));
            List<MarkValue> marksValues = provider.getResultService().retrieveResultByUserId(userID);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(marksValues);
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().write(json);
        }
        catch (ServiceException | IOException e){
            LOGGER.log(Level.ERROR,e.getMessage());
            Router.redirect(response, URLHolder.MAIN_PAGE);
        }
    }
}
