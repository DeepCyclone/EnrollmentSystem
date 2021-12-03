package by.epamtc.enrollmentsystem.controller.action.impl;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.dto.UserStyledToAdminPanel;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.UserService;
import by.epamtc.enrollmentsystem.service.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PreloadAdminPanelCommand implements Command {

    private static Logger LOGGER = LogManager.getLogger(PreloadAdminPanelCommand.class);

    private static final int RECORDS_PER_PAGE = 3;
    private static final int DEFAULT_PAGE = 1;
    private static final int BUTTONS = 3;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        List<UserStyledToAdminPanel> userStyledToAdminPanelList;
        try {
            UserService userService = ServiceProvider.getInstance().getUserService();

            int usersNumber = 0;
            usersNumber = userService.getNumberOfRecords();
            String currentPage = request.getParameter(RequestMapping.PAGE);
            int totalPages = (usersNumber / RECORDS_PER_PAGE) + 1;//всего кнопок
            // задание параметров выборки из БД
            int usersRangeStart = DEFAULT_PAGE;
            int usersRangeEnd;
            int pageNum = DEFAULT_PAGE;
            int records = RECORDS_PER_PAGE;
            if (currentPage != null && NumberValidator.isInteger(currentPage)) {
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
            userStyledToAdminPanelList = userService.getStyledUserInfo(usersRangeStart, records);
            int endPageCounted = ((pageNum - 1)/BUTTONS + 1) * BUTTONS;
            int startPage = endPageCounted - (BUTTONS - 1);
            int endPage = Math.min(endPageCounted, totalPages);
            request.setAttribute(RequestMapping.END_PAGE, endPage);
            request.setAttribute(RequestMapping.CURRENT_PAGE,startPage);
            if(startPage + BUTTONS <= totalPages){
                request.setAttribute(RequestMapping.NEXT_PAGE,startPage + BUTTONS);
            }
            if(pageNum > BUTTONS){
                request.setAttribute(RequestMapping.PREV_PAGE,startPage - BUTTONS);
            }


            request.setAttribute(RequestMapping.USERS, userStyledToAdminPanelList);

            Router.forward(request,response, URLHolder.DOCUMENTS);
        }
        catch (ServiceException e){
            LOGGER.log(Level.ERROR,e.getMessage());
            Router.redirect(response, URLHolder.MAIN_PAGE);
        }
    }
}
