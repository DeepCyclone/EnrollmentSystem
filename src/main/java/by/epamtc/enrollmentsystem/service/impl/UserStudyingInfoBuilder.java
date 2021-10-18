package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.templates.ApplicantEnrollmentDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.EducationForm;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.model.dto.StringifiedApplicantEnrollment;
import by.epamtc.enrollmentsystem.service.ApplicantEnrollmentService;
import by.epamtc.enrollmentsystem.service.ServiceProvider;

import java.util.*;

public class UserStudyingInfoBuilder {
    public static Set<StringifiedApplicantEnrollment> buildFacultiesAndEducationForms(int userId) throws ServiceException {//TODO вот ещё один повтор
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            ApplicantEnrollmentService applicantEnrollmentService = serviceProvider.getApplicantEnrollmentService();
            return applicantEnrollmentService.getStringifiedTable(userId);
    }
}
