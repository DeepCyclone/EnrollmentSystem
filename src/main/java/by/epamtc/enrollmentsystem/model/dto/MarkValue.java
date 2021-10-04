package by.epamtc.enrollmentsystem.model.dto;

public class MarkValue {
    public MarkValue(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public MarkValue() {
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

    private String name;
    private int value;
}
