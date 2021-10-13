package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.Subjectm2mFacultyMySQL;
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
        Subjectm2mFacultyMySQL smfDAO = daoProvider.getSubjectm2mFacultyMySQL();
        Map<String, List<String>> facSub = smfDAO.getFacultiesCorrespondingToSubjects();
        Gson gson = new Gson();
        String json = gson.toJson(facSub);
        response.getWriter().write(json);
    }
}
