package by.epamtc.enrollmentsystem.service;

import by.epamtc.enrollmentsystem.exception.ServiceException;

import java.util.List;

public interface Facilitym2mUserInfoService {
    List<String> getUserFacilitiesNames(long userId) throws ServiceException;
}
