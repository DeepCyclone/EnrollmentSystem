package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageChangerCommand implements Command {
    private static final int SECS_IN_HOUR = 3600;
    private static final int HOURS = 24;
    private static final int DAYS = 30;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = request.getParameter("locale");
        Cookie cookie = new Cookie("locale",locale);
        cookie.setMaxAge(SECS_IN_HOUR * HOURS * DAYS);
        response.addCookie(cookie);
        request.getRequestDispatcher(request.getRequestURL().toString()).forward(request,response);
    }
}
