package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.Subjectm2mFacultyService;
import com.google.gson.Gson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PreloadFacultiesPageCommand implements Command {
    private static Logger logger = LogManager.getLogger(PreloadFacultiesPageCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        Map<String, List<String>> facSub = null;
        Subjectm2mFacultyService service = serviceProvider.getSubjectm2mFacultyService();
        try {
            facSub = service.getFacultiesCorrespondingToSubjects();
        }
        catch (ServiceException exception){
            logger.log(Level.ERROR,exception.getMessage());
        }
        Gson gson = new Gson();
        String json = gson.toJson(facSub);
        response.getWriter().write(json);
    }
}
