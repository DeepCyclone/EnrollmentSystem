package by.epamtc.enrollmentsystem.controller.routing;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*
* This class provides a way to execute redirect and forward operations with internal exception handling and logging
* Also you don't need to handle error with redirecting
* @author Flexus
*/

public class Router {
    private static final Logger LOG = LogManager.getLogger(Router.class);
    public static void redirect(HttpServletResponse response,String URL){
        try{
            response.sendRedirect(URL);
        }
        catch (IOException e){
            LOG.log(Level.ERROR,e.getMessage());
            redirect(response,URLHolder.MAIN_PAGE);
        }
    }
    public static void forward(HttpServletRequest request,HttpServletResponse response,String URL){
        try{
            request.getRequestDispatcher(URL).forward(request,response);
        }
        catch (IOException | ServletException e){
            LOG.log(Level.ERROR,e.getMessage());
            redirect(response,URLHolder.MAIN_PAGE);
        }
    }
}
