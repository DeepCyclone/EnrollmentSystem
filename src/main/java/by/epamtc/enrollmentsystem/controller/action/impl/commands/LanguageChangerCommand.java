package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageChangerCommand implements Command {
    private static Logger logger = LogManager.getLogger(LanguageChangerCommand.class);
    private static final int SECS_IN_HOUR = 3600;
    private static final int HOURS = 24;
    private static final int DAYS = 30;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String locale = request.getParameter("locale");
        Cookie cookie = new Cookie("locale",locale);
        cookie.setMaxAge(SECS_IN_HOUR * HOURS * DAYS);
        response.addCookie(cookie);
        String requestURL = request.getRequestURL().toString();
        String reqParams = request.getQueryString();
        String fullURL = requestURL + "?" + reqParams;
        try {
            request.getRequestDispatcher(fullURL).forward(request, response);
        }
        catch (IOException | ServletException e){
            logger.log(Level.ERROR,e.getMessage());
        }
    }
}
