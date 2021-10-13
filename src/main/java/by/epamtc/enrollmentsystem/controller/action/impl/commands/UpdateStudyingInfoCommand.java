package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.action.impl.redirectors.URLHolder;
import by.epamtc.enrollmentsystem.services.MarksAndFacultiesParser;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class UpdateStudyingInfoCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //TODO deny updateflooding
        try {
            Map<String,String[]> a = request.getParameterMap();//потому что динамически в foreach подгружаются и конкретные названия не могу взять
            MarksAndFacultiesParser.parse(a,request);//лучше бы факультеты прописал
            response.sendRedirect(request.getContextPath() + URLHolder.USER_INFO_PAGE_PRELOADER);
        }
        catch (Exception e){
        }
    }
}
