package Helpers;

public class GuestPayment {
    private int id;
    private String firstname;
    private String lastname;
    private String date;
    private double price;

    public GuestPayment(int id,String firstname,String lastname,String date,double price){
        this.id=id;
        this.firstname=firstname;
        this.lastname=lastname;
        this.date=date;
        this.price=price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
