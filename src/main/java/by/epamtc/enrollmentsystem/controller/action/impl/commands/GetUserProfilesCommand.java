package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetUserProfilesCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EnrollmentStatusUpdateCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Router.forward(request,response,URLHolder.DOCUMENTS);
    }
}
