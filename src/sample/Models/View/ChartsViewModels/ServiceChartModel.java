package sample.Models.View.ChartsViewModels;

public class ServiceChartModel {
    private String name;
    private int total;

    public ServiceChartModel(){}

    public ServiceChartModel(String name,int total){
        this.name = name;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public int getTotal() {
        return total;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
