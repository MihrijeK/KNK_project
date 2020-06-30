package sample.Models.View.ChartsViewModels;

public class StaffChartModel {
    private String position;
    private int totalPosition;

    public StaffChartModel(){
    }

    public StaffChartModel(String position,int totalPosition){
        this.position = position;
        this.totalPosition = totalPosition;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getTotalPosition() {
        return totalPosition;
    }

    public void setTotalPosition(int totalPosition) {
        this.totalPosition = totalPosition;
    }
}
