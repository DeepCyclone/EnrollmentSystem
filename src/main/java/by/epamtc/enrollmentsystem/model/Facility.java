package by.epamtc.enrollmentsystem.model;

public class Facility {
    public Facility() {
    }

    public Facility(int id, String name, boolean isExtraordinary) {
        this.id = id;
        this.name = name;
        this.isExtraordinary = isExtraordinary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    private int id;
    private String name;
    private boolean isExtraordinary;
}
