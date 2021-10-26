package by.epamtc.enrollmentsystem.dao.template;

import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.UserInfo;

import java.util.Optional;

public interface UserInfoDAO extends DAOTemplate<UserInfo>, Identifiable<UserInfo> {

    Optional<UserInfo> getByUserName(String userName) throws DAOException;
}
