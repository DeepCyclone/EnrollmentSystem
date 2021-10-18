package by.epamtc.enrollmentsystem.model;

public class SystemInformation {

    private long id;
    private String name;
    private String value;

    public SystemInformation() {
    }

    public SystemInformation(int id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
