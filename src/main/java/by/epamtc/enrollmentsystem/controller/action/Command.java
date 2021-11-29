package by.epamtc.enrollmentsystem.controller.action;

import by.epamtc.enrollmentsystem.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    A basic interface to implement Command pattern
    @see Command
 */

public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response);
}
