package by.epamtc.enrollmentsystem.controller;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.action.CommandProvider;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Controller extends HttpServlet {
    @Override
    public void init(){
        ConnectionPool.getInstance().initPoolData();
    }
    @Override
    public void destroy(){
        ConnectionPool.getInstance().dispose();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if(action != null){
            CommandProvider commandProvider = CommandProvider.getInstance();
            Command command = commandProvider.getCommand(action);
            command.execute(request, response);
        }
        else{
            response.sendRedirect("");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST");

        String action = request.getParameter("action");

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
