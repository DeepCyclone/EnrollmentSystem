package by.epamtc.enrollmentsystem.controller.action.impl.commands;


import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.model.dto.MarkValue;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.UserService;
import by.epamtc.enrollmentsystem.service.validator.CredentialsValidator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PreloadUserPopupCommand implements Command {
    private static Logger logger = LogManager.getLogger(PreloadUserPopupCommand.class);
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceProvider provider = ServiceProvider.getInstance();
            long userID = Long.parseLong(request.getParameter("userID"));
            List<MarkValue> marksValues = provider.getResultService().retrieveResultByUserId(userID);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(marksValues);
//            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(json);
        }
        catch (ServiceException | IOException e){
            logger.log(Level.ERROR,e.getMessage());
        }
    }
}
