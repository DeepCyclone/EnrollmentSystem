package by.epamtc.enrollmentsystem.model;

public class Faculty {

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
}
