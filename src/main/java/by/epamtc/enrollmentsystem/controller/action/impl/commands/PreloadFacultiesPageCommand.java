package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.Subjectm2mFacultyMySQL;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.Subjectm2mFacultyService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PreloadFacultiesPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        DAOProvider daoProvider = DAOProvider.getInstance();
        Map<String, List<String>> facSub = null;
        Subjectm2mFacultyService service = null;
        try {
            service = ServiceProvider.getInstance().getSubjectm2mFacultyService();
            facSub = service.getFacultiesCorrespondingToSubjects();
        }
        catch (ServiceException exception){

        }
        Gson gson = new Gson();
        String json = gson.toJson(facSub);
        response.getWriter().write(json);
    }
}
