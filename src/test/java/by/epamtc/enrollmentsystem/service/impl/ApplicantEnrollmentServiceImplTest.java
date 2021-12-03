package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.ApplicantEnrollmentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ApplicantEnrollmentServiceImplTest {

    private final static ApplicantEnrollmentService service = ServiceProvider.getInstance().getApplicantEnrollmentService();

    public static final long USER_ID = 1L;

    @BeforeAll
    static void init() throws DAOException {
        ConnectionPool.getInstance().initPoolData();
    }

    @Test
    void getSelectedFacultiesByUserId() throws ServiceException {
        Map<Long, List<Long>> a = service.getSelectedFacultiesByUserId(USER_ID);
        List<Long> expectedEduForms = a.get(6L);
        assertEquals(1, (long) expectedEduForms.get(0));
    }

    @Test
    void userHasFaculty() throws ServiceException {
        assertTrue(service.userHasFaculty(USER_ID,6L));
    }

    @Test
    void userHasntFaculty() throws ServiceException {
        assertFalse(service.userHasFaculty(USER_ID,22L));
    }

    @Test
    void getUserRequestsAmountWithoutFaculty() throws ServiceException {
        assertEquals(0, service.getUserRequestsAmount(1L, 1L));
    }

    @Test
    void getUserRequestsAmountWithFaculty() throws ServiceException {
        assertEquals(1, service.getUserRequestsAmount(6L, 1L));
    }
}