package by.epamtc.enrollmentsystem.controller.action.impl.commands;


import by.epamtc.enrollmentsystem.controller.action.Command;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CredentialsUpdaterCommand implements Command {
    private static Logger logger = LogManager.getLogger(CredentialsUpdaterCommand.class);
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
        }
        catch (Exception e){
            logger.log(Level.ERROR,e.getMessage());
        }
    }
}
