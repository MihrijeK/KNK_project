package sample.Models.View.ChartsViewModels;

public class RoomChartModel {
    private String room_type;
    private int total;
    private int floor_number;

    public RoomChartModel(){}

    public RoomChartModel(String room_type, int total){
        this.room_type = room_type;
        this.total = total;
    }

    public RoomChartModel(int floor_number, int total){
        this.floor_number = floor_number;
        this.total = total;
    }

    public String getRoom_type() {
        return room_type;
    }


    public int getTotal() {
        return total;
    }


    public int getFloor_number() {
        return floor_number;
    }

}
