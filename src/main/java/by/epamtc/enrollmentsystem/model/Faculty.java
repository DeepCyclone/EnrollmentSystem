package by.epamtc.enrollmentsystem.model;

import java.io.Serializable;
import java.util.Objects;

public class Faculty implements Serializable {

    private long id;
    private String name;
    private int budgetAdmissionPlan;
    private int paidAdmissionPlan;
    private String description;

    public Faculty() {
    }

    public Faculty(int id,String name, int budgetAdmissionPlan, int paidAdmissionPlan, String description) {
        this.id = id;
        this.name = name;
        this.budgetAdmissionPlan = budgetAdmissionPlan;
        this.paidAdmissionPlan = paidAdmissionPlan;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBudgetAdmissionPlan() {
        return budgetAdmissionPlan;
    }

    public void setBudgetAdmissionPlan(int budgetAdmissionPlan) {
        this.budgetAdmissionPlan = budgetAdmissionPlan;
    }

    public int getPaidAdmissionPlan() {
        return paidAdmissionPlan;
    }

    public void setPaidAdmissionPlan(int paidAdmissionPlan) {
        this.paidAdmissionPlan = paidAdmissionPlan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id == faculty.id && budgetAdmissionPlan == faculty.budgetAdmissionPlan &&
                paidAdmissionPlan == faculty.paidAdmissionPlan && Objects.equals(name, faculty.name) &&
                Objects.equals(description, faculty.description);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) id;
        result = prime * result + budgetAdmissionPlan;
        result = prime * result + paidAdmissionPlan;
        result = prime * result + (name == null?0:name.hashCode());
        result = prime * result + (description == null?0:description.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getClass().getSimpleName());
        result.append(" Fields:\n");
        result.append("ID:").append(id);
        result.append("budget admission plan:").append(budgetAdmissionPlan).append("\n");
        result.append("paid admission plan:").append(paidAdmissionPlan).append("\n");
        result.append("name:").append(name).append("\n");
        result.append("description:").append(description).append("\n");
        return result.toString();
    }
}
