package by.epamtc.enrollmentsystem.controller.routing;

import javax.servlet.http.HttpServletRequest;

public final class URLHolder {
    private URLHolder(){

    }

    public static final String MAIN_PAGE = "/";
    public static final String LOGIN_PAGE = "/login";
    public static final String DOCUMENTS = "/documents";
    public static final String FACULTIES = "faculties";
    public static final String ABOUT_US = "aboutUs";
    public static final String USER_INFO_PAGE_PRELOADER = "/controller?action=PRELOAD_USERINFO_PAGE";
    public static final String ADMIN_PAGE_PRELOADER = "/controller?action=PRELOAD_ADMIN_PAGE";
    public static final String WELCOME_PAGE_PRELOADER = "/controller?action=PRELOAD_WELCOMEPAGE";
    public static final String WELCOME_PAGE = "welcome";
    public static final String NOT_FOUND_PAGE = "/notFound";


    public static String getFullURL(HttpServletRequest request) {
        StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
        String queryString = request.getQueryString();

        if (queryString != null) {
            requestURL.append('?').append(queryString);
        }
        return requestURL.toString();
    }
}
