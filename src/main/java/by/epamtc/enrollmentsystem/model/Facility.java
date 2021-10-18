package by.epamtc.enrollmentsystem.model;

public class Facility {

    private long id;
    private String name;
    private boolean isExtraordinary;

    public Facility() {
    }

    public Facility(int id, String name, boolean isExtraordinary) {
        this.id = id;
        this.name = name;
        this.isExtraordinary = isExtraordinary;
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

    public boolean isExtraordinary() {
        return isExtraordinary;
    }

    public void setExtraordinary(boolean extraordinary) {
        isExtraordinary = extraordinary;
    }
}
