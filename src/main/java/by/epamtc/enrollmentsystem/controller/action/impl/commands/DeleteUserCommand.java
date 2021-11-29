package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DeleteUserCommand.class);
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserService service = ServiceProvider.getInstance().getUserService();
            long userPretendedToDelete = Long.parseLong(request.getParameter(RequestMapping.SELECTED_USER_ID));
            service.deleteRowByID(userPretendedToDelete);
            Router.redirect(response,request.getContextPath() + URLHolder.ADMIN_PAGE_PRELOADER);
        }
        catch (ServiceException e){
            LOGGER.log(Level.ERROR,e.getMessage());
            Router.redirect(response, URLHolder.MAIN_PAGE);
        }
    }
}
