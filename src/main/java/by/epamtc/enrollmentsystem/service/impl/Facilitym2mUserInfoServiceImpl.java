package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.templates.Facilitym2mUserInfoDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.service.Facilitym2mUserInfoService;

import java.util.List;

public class Facilitym2mUserInfoServiceImpl implements Facilitym2mUserInfoService {
    @Override
    public List<String> getUserFacilitiesNames(int userId) throws ServiceException {
        try {
            Facilitym2mUserInfoDAO facilitym2mUserInfoDAO = DAOProvider.getInstance().getFacilitym2mUserInfoMySQL();
            return facilitym2mUserInfoDAO.getUserFacilitiesNames(userId);
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }
}
