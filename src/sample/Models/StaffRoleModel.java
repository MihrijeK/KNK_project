package sample.Models;

public class StaffRoleModel {
    private String position;
    private double salary;

    public StaffRoleModel(String position,double salary){
        this.position = position;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }
}
