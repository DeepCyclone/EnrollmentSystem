package by.epamtc.enrollmentsystem.controller.listener;

import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.exception.DAOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ControllerListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (DAOException e) {
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        try {
            ConnectionPool.getInstance().dispose();
        } catch (DAOException e) {

        }
    }
}
