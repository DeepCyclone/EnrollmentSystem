package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageChangerCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = request.getParameter("locale");
        Cookie cookie = new Cookie("locale",locale);//todo проверка есть ли уже кука
        cookie.setMaxAge(3600*24*30);//TODO magic nums
        response.addCookie(cookie);
        request.getRequestDispatcher(request.getRequestURL().toString()).forward(request,response);
    }
}
