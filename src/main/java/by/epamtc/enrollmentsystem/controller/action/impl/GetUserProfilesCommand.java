package by.epamtc.enrollmentsystem.controller.action.impl;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUserProfilesCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EnrollmentStatusUpdateCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Router.forward(request,response,URLHolder.DOCUMENTS);
    }
}
