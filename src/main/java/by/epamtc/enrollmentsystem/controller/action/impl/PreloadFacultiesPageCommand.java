package by.epamtc.enrollmentsystem.controller.action.impl;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.Subjectm2mFacultyService;
import com.google.gson.Gson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PreloadFacultiesPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(PreloadFacultiesPageCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        Map<String, List<String>> facSub = null;
        Subjectm2mFacultyService service = serviceProvider.getSubjectm2mFacultyService();
        try {
            facSub = service.getFacultiesCorrespondingToSubjects();
            Gson gson = new Gson();
            String json = gson.toJson(facSub);
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().write(json);
        }
        catch (ServiceException | IOException exce0ption){
            LOGGER.log(Level.ERROR,exception.getMessage());
            Router.redirect(response, URLHolder.MAIN_PAGE);
        }
    }
}
