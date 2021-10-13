package by.epamtc.enrollmentsystem.dao.templates;

import by.epamtc.enrollmentsystem.dao.DAOTemplate;
import by.epamtc.enrollmentsystem.model.Subjectm2mFaculty;

import java.util.List;
import java.util.Map;

public interface Subjectm2mFacultyTempl {
    public Map<String, List<String>> getFacultiesCorrespondingToSubjects();
}
