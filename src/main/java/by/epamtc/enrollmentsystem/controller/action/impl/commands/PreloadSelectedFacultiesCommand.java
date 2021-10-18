package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.model.dto.StringifiedApplicantEnrollment;
import by.epamtc.enrollmentsystem.service.impl.UserStudyingInfoBuilder;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class PreloadSelectedFacultiesCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain; charset=UTF-8");
        HttpSession session = request.getSession(false);
        int id = (int)session.getAttribute("id");
        try{
            Set<StringifiedApplicantEnrollment> stringifiedApplicantEnrollmentSet = UserStudyingInfoBuilder.buildFacultiesAndEducationForms(id);
            Gson gson = new Gson();
            String json = gson.toJson(stringifiedApplicantEnrollmentSet);
            response.getWriter().write(json);
        }
        catch (Exception e){

        }
    }
}
