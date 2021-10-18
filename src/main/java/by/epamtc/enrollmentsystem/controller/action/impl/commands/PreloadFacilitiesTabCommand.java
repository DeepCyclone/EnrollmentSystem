package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.FacilityMySQL;
import by.epamtc.enrollmentsystem.dao.impl.Facilitym2mUserInfoMySQL;
import by.epamtc.enrollmentsystem.dao.impl.Subjectm2mFacultyMySQL;
import by.epamtc.enrollmentsystem.dao.templates.Facilitym2mUserInfoDAO;
import by.epamtc.enrollmentsystem.model.Facilitym2mUserInfo;
import by.epamtc.enrollmentsystem.service.Facilitym2mUserInfoService;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PreloadFacilitiesTabCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/plain; charset=UTF-8");
            HttpSession session = request.getSession(false);
            int userID = (int)session.getAttribute("id");
            Facilitym2mUserInfoService service = ServiceProvider.getInstance().getFacilitym2mUserInfoService();
            List<String> facilities = service.getUserFacilitiesNames(userID);
            Gson gson = new Gson();
            String json = gson.toJson(facilities);
            System.out.println(json);
            response.getWriter().write(json);
        }
        catch (Exception e){

        }
    }
}
