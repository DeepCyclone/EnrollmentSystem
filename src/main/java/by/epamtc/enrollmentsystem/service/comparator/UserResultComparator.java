package by.epamtc.enrollmentsystem.service.comparator;

import by.epamtc.enrollmentsystem.model.dto.UserTotalResultWithFaculty;

import java.util.Comparator;

public class UserResultComparator implements Comparator<UserTotalResultWithFaculty> {
    @Override
    public int compare(UserTotalResultWithFaculty o1, UserTotalResultWithFaculty o2) {
        return Integer.compare(o1.getPriority(), o2.getPriority());
    }
}
