package by.epamtc.enrollmentsystem.controller;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.action.CommandProvider;
import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.exception.DAOException;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/*
 * The only one servlet, which processes GET and POST requests
 * @author Flexus
 */
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String action = request.getParameter(RequestMapping.ACTION);

        if(action != null){
            CommandProvider commandProvider = CommandProvider.getInstance();
            Command command = commandProvider.getCommand(action);
            command.execute(request, response);
        }
        else{
            response.sendRedirect("");
        }
    }
}
