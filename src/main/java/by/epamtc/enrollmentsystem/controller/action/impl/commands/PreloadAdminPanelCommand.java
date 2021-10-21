package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.UserInfoMySQL;
import by.epamtc.enrollmentsystem.dao.impl.UserMySQL;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.model.UserInfo;
import by.epamtc.enrollmentsystem.model.dto.UserStyledToAdminPanel;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PreloadAdminPanelCommand implements Command {

    private static final int RECORDS_PER_PAGE = 3;
    private static final int DEFAULT_PAGE = 1;
    private static final int BUTTONS = 3;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserStyledToAdminPanel> userStyledToAdminPanelList;
        try {
            UserService userService = ServiceProvider.getInstance().getUserService();

            int usersNumber = 0;
            usersNumber = userService.getNumberOfRecords();
            String currentPage = request.getParameter("page");
            int totalPages = (usersNumber / RECORDS_PER_PAGE) + 1;//всего кнопок
            // задание параметров выборки из БД
            int usersRangeStart = DEFAULT_PAGE;
            int usersRangeEnd;
            int pageNum = DEFAULT_PAGE;
            int records = RECORDS_PER_PAGE;
            if (currentPage != null) {
                pageNum = Integer.parseInt(currentPage);//TODO а если параметр не int
                if(pageNum > totalPages){
                    pageNum = totalPages;
                }
                else if (pageNum <= 0){
                    pageNum = DEFAULT_PAGE;
                }
                usersRangeEnd = pageNum * RECORDS_PER_PAGE;
                usersRangeStart = usersRangeEnd - RECORDS_PER_PAGE;
                if(usersNumber - usersRangeEnd < 0){
                    records = usersNumber - (usersRangeEnd - RECORDS_PER_PAGE);
                }
            }
            userStyledToAdminPanelList = userService.getStyledUserInfo(usersRangeStart, records);//выборка из БД
            int endPageCounted = ((pageNum - 1)/BUTTONS + 1) * BUTTONS;//вычисляемая последняя страница промежутка,кратного buttons
            int startPage = endPageCounted - 2;//первая страница промежутка кнопок переключения
            int endPage = Math.min(endPageCounted, totalPages);//отображаемая оконечная страница кнопок переключения
            request.setAttribute("endPage", endPage);
            request.setAttribute("currentPage",startPage);
            if(startPage + BUTTONS <= totalPages){
                request.setAttribute("nextPage",startPage + BUTTONS);//след группа кнопок
            }
            if(pageNum > BUTTONS){
                request.setAttribute("prevPage",startPage - BUTTONS);//обратно
            }


            request.setAttribute("users", userStyledToAdminPanelList);


            request.getRequestDispatcher("/documents").forward(request, response);
        }
        catch (Exception e){

        }
    }
}
