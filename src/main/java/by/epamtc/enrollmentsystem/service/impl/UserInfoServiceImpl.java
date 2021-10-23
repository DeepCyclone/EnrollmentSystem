package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.interfaces.SystemInformationDAO;
import by.epamtc.enrollmentsystem.dao.interfaces.UserInfoDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.SystemInformation;
import by.epamtc.enrollmentsystem.model.UserInfo;
import by.epamtc.enrollmentsystem.service.templates.UserInfoService;

import java.util.Optional;

public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public boolean hasNoteWithId(int id) throws ServiceException {
        try {
            UserInfoDAO dao = DAOProvider.getInstance().getUserInfoDAO();
            return dao.getByID(id).isPresent();
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public Optional<UserInfo> getByID(long id) throws ServiceException {
        try {
            UserInfoDAO dao = DAOProvider.getInstance().getUserInfoDAO();
            return dao.getByID(id);
        }
        catch (DAOException exception){
            throw new ServiceException();
        }
    }

    @Override
    public void updateRowByID(UserInfo ui) throws ServiceException {
        try {
            if(ui != null) {
                UserInfoDAO dao = DAOProvider.getInstance().getUserInfoDAO();
                dao.updateRowByID(ui);
            }
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public void insertInto(UserInfo ui) throws ServiceException {
        try {
            if(ui != null) {
                UserInfoDAO dao = DAOProvider.getInstance().getUserInfoDAO();
                dao.insertInto(ui);
            }
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public Optional<UserInfo> getByUserName(String userName) throws ServiceException {
        try {
                UserInfoDAO dao = DAOProvider.getInstance().getUserInfoDAO();
                return dao.getByUserName(userName);
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }
}
