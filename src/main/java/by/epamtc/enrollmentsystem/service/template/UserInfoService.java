package by.epamtc.enrollmentsystem.service.template;

import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.UserInfo;

import java.util.Optional;

public interface UserInfoService extends Identifiable<UserInfo>{
    boolean hasNoteWithId(long id) throws ServiceException;

    void updateRowByID(UserInfo ui) throws ServiceException;

    void insertInto(UserInfo ui) throws ServiceException;

    Optional<UserInfo> getByUserName(String userName) throws ServiceException;
}
