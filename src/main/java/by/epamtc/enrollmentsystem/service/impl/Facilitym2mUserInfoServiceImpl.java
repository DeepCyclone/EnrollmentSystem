package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.templates.FacilityDAO;
import by.epamtc.enrollmentsystem.dao.templates.Facilitym2mUserInfoDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Facility;
import by.epamtc.enrollmentsystem.model.Facilitym2mUserInfo;
import by.epamtc.enrollmentsystem.service.Facilitym2mUserInfoService;

import java.util.ArrayList;
import java.util.List;

public class Facilitym2mUserInfoServiceImpl implements Facilitym2mUserInfoService {
    @Override
    public List<String> getUserFacilitiesNames(long userId) throws ServiceException {
        try {
            DAOProvider daoProvider = DAOProvider.getInstance();
            Facilitym2mUserInfoDAO facilitym2mUserInfoDAO = daoProvider.getFacilitym2mUserInfoMySQL();
            FacilityDAO facilityDAO = daoProvider.getFacilityDAO();
            List<Facilitym2mUserInfo> userFacilities = facilitym2mUserInfoDAO.getByUserId(userId);
            List<String> userFacilitiesNames = new ArrayList<>();
            List<Facility> facilities = facilityDAO.getAll();
            for(Facility facility:facilities){
            }
            return facilitym2mUserInfoDAO.getUserFacilitiesNames(userId);
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }
}
