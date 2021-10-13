package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.FacilityMySQL;
import by.epamtc.enrollmentsystem.dao.impl.Facilitym2mUserInfoMySQL;
import by.epamtc.enrollmentsystem.dao.impl.Subjectm2mFacultyMySQL;
import by.epamtc.enrollmentsystem.model.Facilitym2mUserInfo;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PreloadFacilitiesTabCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/plain; charset=UTF-8");
            DAOProvider daoProvider = DAOProvider.getInstance();
            Facilitym2mUserInfoMySQL facm2mUi = daoProvider.getFacilitym2mUserInfoMySQL();
            FacilityMySQL facilityMySQL = daoProvider.getFacilityDAO();
            List<Facilitym2mUserInfo> facilityList = facm2mUi.getAll();
            List<String> facilities = new ArrayList<>();
            for (Facilitym2mUserInfo facilitym2mUserInfo : facilityList) {
                facilities.add(facilityMySQL.getByID(facilitym2mUserInfo.getUserInfoUserId()).getName());//TODO fix
            }
            Gson gson = new Gson();
            String json = gson.toJson(facilities);
            System.out.println(json);
            response.getWriter().write(json);
        }
        catch (Exception e){

        }
    }
}
