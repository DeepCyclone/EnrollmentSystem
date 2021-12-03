package by.epamtc.enrollmentsystem.controller.action.impl;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command {
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        httpSession.invalidate();
        Router.redirect(response,request.getContextPath() + URLHolder.MAIN_PAGE);
    }
}
