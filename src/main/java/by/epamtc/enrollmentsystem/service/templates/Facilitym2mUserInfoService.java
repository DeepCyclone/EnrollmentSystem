package by.epamtc.enrollmentsystem.service.templates;

import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Facilitym2mUserInfo;

import java.util.List;

public interface Facilitym2mUserInfoService {
    List<String> getUserFacilitiesNames(long userId) throws ServiceException;
    int insertInto(Facilitym2mUserInfo data) throws ServiceException;
}
