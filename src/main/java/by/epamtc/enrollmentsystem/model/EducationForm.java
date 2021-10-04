package by.epamtc.enrollmentsystem.model;

public class EducationForm {
    public EducationForm(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public EducationForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String name;
}
