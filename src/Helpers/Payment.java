package Helpers;

public class Payment {
    private int id;
    private int guest_id;
    private double price;
    private String payment_method;
    private boolean isPayed;

    public Payment(){}

    public Payment(int id,int guest_id,double price,String payment_method,boolean isPayed){
        this.id = id;
        this.guest_id = guest_id;
        this.price = price;
        this.payment_method = payment_method;
        this.isPayed = isPayed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }
}
