package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.model.SystemInformation;
import by.epamtc.enrollmentsystem.service.template.FacultyService;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.SystemInformationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PreloadWelcomePageCommand implements Command {
    private static final int DEFAULT_PAGE = 1;//первая страница
    private static final int RECORDS_PER_PAGE = 5;//количество факультетов в одной странице
    private static final int BUTTONS = 3;//количество кнопок в блоке
    private static final String WELCOME_PAGE_INFO = "welcome_page";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO проверка на правильность page и перенос в сервис
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            SystemInformationService systemInformationService = serviceProvider.getSystemInformationService();
            FacultyService facultyService = serviceProvider.getFacultyService();
            String description = null;
            List<Faculty> faculties = null;
            try {
                int facultiesNumber = 0;
                Optional<SystemInformation> welcomePageInfo = systemInformationService.getByName(WELCOME_PAGE_INFO);
                if(welcomePageInfo.isPresent()) {
                    description = welcomePageInfo.get().getValue();
                }
                facultiesNumber = facultyService.getFacultiesNumber();
                String currentPage = request.getParameter("page");
                int totalPages = (facultiesNumber / RECORDS_PER_PAGE) + 1;//всего кнопок
                // задание параметров выборки из БД
                int facultiesRangeStart = DEFAULT_PAGE;
                int facultiesRangeEnd;
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
                    facultiesRangeEnd = pageNum * RECORDS_PER_PAGE;
                    facultiesRangeStart = facultiesRangeEnd - RECORDS_PER_PAGE;
                    if(facultiesNumber - facultiesRangeEnd < 0){
                        records = facultiesNumber - (facultiesRangeEnd - RECORDS_PER_PAGE);
                    }
                }
                faculties = facultyService.getFacultiesRange(facultiesRangeStart, records);//выборка из БД
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
            }
            catch (ServiceException exception){

            }
            request.setAttribute("facultiesList", faculties);
            request.setAttribute("description", description);//TODO как-то закешировать инфу, т.к. можно зажать f5 на welcomepage

//        }
        request.getRequestDispatcher("welcome").forward(request,response);
    }
}
