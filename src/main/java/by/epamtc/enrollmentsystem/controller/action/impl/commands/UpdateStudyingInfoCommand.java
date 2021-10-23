package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.action.impl.redirectors.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.impl.MarksAndFacultiesUpdater;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UpdateStudyingInfoCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //TODO deny updateflooding
        try {
            request.setCharacterEncoding("UTF-8");
            Map<String, String[]> a = request.getParameterMap();
            MarksAndFacultiesUpdater.update(a, request);//лучше бы факультеты прописал
            response.sendRedirect(request.getContextPath() + URLHolder.USER_INFO_PAGE_PRELOADER);
        }
        catch (ServiceException exception){
            //logger
            //redirect
        }
    }
}
