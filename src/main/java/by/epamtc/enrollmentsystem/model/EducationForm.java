package by.epamtc.enrollmentsystem.model;

public class EducationForm {

    private long id;
    private String name;

    public EducationForm() {
    }

    public EducationForm(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
