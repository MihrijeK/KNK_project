package Helpers;

public class Service_Type {
    private int id;
    private String service_name;
    private double price;
    private int quantity;

    public Service_Type(){}

    public Service_Type(int id,String service_name,double price,int quantity){
        this.id = id;
        this.service_name = service_name;
        this.price = price;
        this.quantity = quantity;
    }

    public Service_Type(String service_name, double price) {
        this.service_name = service_name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
