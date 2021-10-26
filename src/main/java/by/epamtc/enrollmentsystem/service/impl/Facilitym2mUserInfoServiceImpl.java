package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.template.FacilityDAO;
import by.epamtc.enrollmentsystem.dao.template.Facilitym2mUserInfoDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Facility;
import by.epamtc.enrollmentsystem.model.Facilitym2mUserInfo;
import by.epamtc.enrollmentsystem.service.template.Facilitym2mUserInfoService;

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
                userFacilitiesNames.add(facility.getName());
            }
            return facilitym2mUserInfoDAO.getUserFacilitiesNames(userId);
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public int insertInto(Facilitym2mUserInfo data) throws ServiceException {
        try{
            Facilitym2mUserInfoDAO dao = DAOProvider.getInstance().getFacilitym2mUserInfoMySQL();
            dao.insertInto(data);
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
        return 1;
    }
}
