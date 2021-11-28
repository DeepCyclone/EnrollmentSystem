package by.epamtc.enrollmentsystem.service.util;

import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.dto.StringifiedApplicantEnrollment;
import by.epamtc.enrollmentsystem.service.template.ApplicantEnrollmentService;
import by.epamtc.enrollmentsystem.service.ServiceProvider;

import java.util.*;

public class UserStudyingInfoBuilder {
    public static Set<StringifiedApplicantEnrollment> buildFacultiesAndEducationForms(long userId) throws ServiceException {//TODO вот ещё один повтор
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            ApplicantEnrollmentService applicantEnrollmentService = serviceProvider.getApplicantEnrollmentService();
            return applicantEnrollmentService.getStringifiedTable(userId);
    }
}
