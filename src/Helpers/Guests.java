package Helpers;

import java.util.Date;

public class Guests extends Person {
    private Date registeredDate;

    public Guests(){}

    public Guests(int id,String firstName, String lastName, int personalNumber, String phoneNumber,String gender, Date birthdate,Date registeredDate){
        super(id,firstName,lastName,personalNumber,phoneNumber,gender,birthdate);
        this.registeredDate = registeredDate;
    }
    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }
}
