package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.action.impl.redirectors.URLHolder;
import by.epamtc.enrollmentsystem.model.UserType;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.impl.EnrollmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StartEnrollmentCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int roleId = (int)session.getAttribute("role");
        if(roleId!=UserType.ADMIN_ROLE){
            response.sendRedirect(request.getContextPath() + URLHolder.MAIN_PAGE);//TODO 404 page
        }
        ServiceProvider provider = ServiceProvider.getInstance();
        EnrollmentService service = provider.getEnrollmentService();
        try {
            service.makeEnrollment();
        }
        catch (Exception e){

        }
        response.sendRedirect(request.getContextPath() + URLHolder.ADMIN_PAGE_PRELOADER);
    }
}
