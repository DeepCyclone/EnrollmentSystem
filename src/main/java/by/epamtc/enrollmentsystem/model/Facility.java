package by.epamtc.enrollmentsystem.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facility facility = (Facility) o;
        return id == facility.id && isExtraordinary == facility.isExtraordinary && Objects.equals(name, facility.name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) id;
        result = prime * result + Boolean.hashCode(isExtraordinary);
        result = prime * result + (name==null?0:name.hashCode());;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getClass().getSimpleName());
        result.append(" Fields:\n");
        result.append("ID:").append(id).append("\n");
        result.append("name:").append(name).append("\n");
        result.append("is extraordinary:").append(isExtraordinary).append("\n");
        return result.toString();
    }
}
