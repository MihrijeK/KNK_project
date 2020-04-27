package Helpers;

import java.util.Date;

public class Staff extends Person {
    private String position;
    private double salary;
    private String password;

    public Staff(){}

    public Staff(int id,String firstName, String lastName, int personalNumber, String phoneNumber, Date birthdate,String position,double salary,String password){
        super(id,firstName,lastName,personalNumber,phoneNumber,birthdate);
        this.position = position;
        this.salary = salary;
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
