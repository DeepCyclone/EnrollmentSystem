package by.epamtc.enrollmentsystem.model;

import java.io.Serializable;
import java.util.Objects;

public class Subject implements Serializable {

    private long id;
    private String name;

    public Subject(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Subject() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id &&
                Objects.equals(name, subject.name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) id;
        result = prime * result + (name == null?0:name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getClass().getSimpleName());
        result.append(" Fields:\n");
        result.append("ID:").append(id).append("\n");
        result.append("name:").append(name).append("\n");
        return result.toString();
    }
}
