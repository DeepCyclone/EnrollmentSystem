package by.epamtc.enrollmentsystem.controller.action.impl.redirectors;

import by.epamtc.enrollmentsystem.controller.action.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPageRedirect implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + URLHolder.LOGIN_PAGE);
    }
}
