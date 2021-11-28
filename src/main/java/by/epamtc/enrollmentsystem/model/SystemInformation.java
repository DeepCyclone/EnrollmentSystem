package by.epamtc.enrollmentsystem.model;

import java.io.Serializable;
import java.util.Objects;

public class SystemInformation implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemInformation that = (SystemInformation) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) id;
        result = prime * result + (name == null?0:name.hashCode());
        result = prime * result + (value == null?0:value.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getClass().getSimpleName());
        result.append(" Fields:\n");
        result.append("ID:").append(id).append("\n");
        result.append("name").append(name).append("\n");
        result.append("value:").append(value).append("\n");
        return result.toString();
    }
}
