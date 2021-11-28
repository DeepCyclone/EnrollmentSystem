package by.epamtc.enrollmentsystem.model.dto;

import java.io.Serializable;

public class MarkValue implements Serializable {

    private long id;
    private String name;
    private int value;

    public MarkValue() {
    }

    public MarkValue(int id,String name, int value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MarkValue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
