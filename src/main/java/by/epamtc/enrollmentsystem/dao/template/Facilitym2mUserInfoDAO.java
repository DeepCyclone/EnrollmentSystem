package by.epamtc.enrollmentsystem.dao.template;

import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Facilitym2mUserInfo;

import java.util.List;

public interface Facilitym2mUserInfoDAO extends DAOTemplate<Facilitym2mUserInfo> {
    List<String> getUserFacilitiesNames(long userId) throws DAOException;
    List<Facilitym2mUserInfo> getByUserId(long userId) throws DAOException;
}
