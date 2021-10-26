package by.epamtc.enrollmentsystem.service.comparator;

import by.epamtc.enrollmentsystem.model.Result;

import java.util.Comparator;

public class ResultComparator implements Comparator<Result> {
    @Override
    public int compare(Result o1, Result o2) {
        return o1.getResultValue() - o2.getResultValue();
    }
}
