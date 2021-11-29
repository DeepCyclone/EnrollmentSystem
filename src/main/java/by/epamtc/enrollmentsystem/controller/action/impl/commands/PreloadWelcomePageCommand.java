package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.model.SystemInformation;
import by.epamtc.enrollmentsystem.service.template.ApplicantEnrollmentService;
import by.epamtc.enrollmentsystem.service.template.FacultyService;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.SystemInformationService;
import by.epamtc.enrollmentsystem.service.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PreloadWelcomePageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(PreloadWelcomePageCommand.class);

    private static final int DEFAULT_PAGE = 1;
    private static final int RECORDS_PER_PAGE = 5;
    private static final int BUTTONS = 3;
    private static final String WELCOME_PAGE_INFO = "welcome_page";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            SystemInformationService systemInformationService = serviceProvider.getSystemInformationService();
            FacultyService facultyService = serviceProvider.getFacultyService();
            ApplicantEnrollmentService applicantEnrollmentService = serviceProvider.getApplicantEnrollmentService();
            Map<String,Integer> totalRequestsBudg = new HashMap<>();
            Map<String,Integer> totalRequestsPaid = new HashMap<>();
            String description = null;
            List<Faculty> faculties = null;
            try {
                int facultiesNumber = 0;
                Optional<SystemInformation> welcomePageInfo = systemInformationService.getByName(WELCOME_PAGE_INFO);
                if(welcomePageInfo.isPresent()) {
                    description = welcomePageInfo.get().getValue();
                }
                facultiesNumber = facultyService.getFacultiesNumber();
                String currentPage = request.getParameter(RequestMapping.PAGE);
                int totalPages = (facultiesNumber / RECORDS_PER_PAGE) + 1;

                int facultiesRangeStart = DEFAULT_PAGE;
                int facultiesRangeEnd;
                int pageNum = DEFAULT_PAGE;
                int records = RECORDS_PER_PAGE;
                if (currentPage != null && NumberValidator.isInteger(currentPage)) {
                    pageNum = Integer.parseInt(currentPage);
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
                faculties = facultyService.getFacultiesRange(facultiesRangeStart, records);
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
                totalRequestsBudg = applicantEnrollmentService.buildRequestAmountDtos(1,facultiesRangeStart,records);
                totalRequestsPaid = applicantEnrollmentService.buildRequestAmountDtos(2,facultiesRangeStart,records);
                request.setAttribute(RequestMapping.FACULTIES_LIST, faculties);
                request.setAttribute(RequestMapping.TOTAL_REQUESTS_BUDGET,totalRequestsBudg);
                request.setAttribute(RequestMapping.TOTAL_REQUESTS_PAID,totalRequestsPaid);
                request.setAttribute(RequestMapping.DESCRIPTION, description);
                request.getRequestDispatcher("welcome").forward(request,response);
            }
            catch (ServiceException | IOException | ServletException exception){
                LOGGER.log(Level.ERROR,exception.getMessage());
                Router.redirect(response, request.getContextPath() + URLHolder.MAIN_PAGE);
            }

    }
}
