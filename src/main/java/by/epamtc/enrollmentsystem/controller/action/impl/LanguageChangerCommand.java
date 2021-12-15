package by.epamtc.enrollmentsystem.controller.action.impl;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LanguageChangerCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(LanguageChangerCommand.class);
    private static final int SECS_IN_HOUR = 3600;
    private static final int HOURS = 24;
    private static final int DAYS = 30;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String locale = request.getParameter(RequestMapping.LOCALE);
        Cookie cookie = new Cookie(RequestMapping.LOCALE,locale);
        cookie.setMaxAge(SECS_IN_HOUR * HOURS * DAYS);
        response.addCookie(cookie);
        String requestURL = request.getRequestURL().toString();
        String reqParams = request.getQueryString();
        String fullURL = requestURL + "?" + reqParams;
        Router.redirectToLastPage(request,response);
    }
}
