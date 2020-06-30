package sample.Models.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import sample.Models.View.ChartsViewModels.RoomChartModel;
import sample.Models.View.ChartsViewModels.ServiceChartModel;
import sample.Models.View.ChartsViewModels.StaffChartModel;
import sample.Repositories.RoomRespository;
import sample.Repositories.ServicesTypeRepository;
import sample.Repositories.StaffRepository;

import java.util.List;

public class ChartsView {

    public static PieChart serviceChart(PieChart pieChart, Label status) throws Exception {
        List<ServiceChartModel> service_typeList= ServicesTypeRepository.selectAllChart();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        int totali = 0;

        for (ServiceChartModel sc : service_typeList) {
            totali += sc.getTotal();
        }

        for (ServiceChartModel sc : service_typeList) {
            pieChartData.addAll(new PieChart.Data(sc.getName(),calculatePercentage(sc.getTotal(),totali)));
        }

        pieChart = new PieChart();
        pieChart.setData(pieChartData);
        pieChart.setTitle("Services By Price");
        pieChart.setLegendSide(Side.BOTTOM );
        pieChart.setLabelsVisible(true);

        for(final PieChart.Data data : pieChart.getData())
        {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    status.setText(String.valueOf(data.getPieValue())+ " %");
                }
            });
        }

        return pieChart;
    }

    public static PieChart staffChart(PieChart pieChart, Label status) throws Exception {
        List<StaffChartModel> staff_list = StaffRepository.selectAllOrdeByPosiotion();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        int total = 0;

        for (StaffChartModel sc : staff_list) {
            total += sc.getTotalPosition();
        }

        for (StaffChartModel sc : staff_list) {
            pieChartData.addAll(new PieChart.Data(sc.getPosition(),calculatePercentage(sc.getTotalPosition(),total)));
        }

        pieChart = new PieChart();
        pieChart.setData(pieChartData);
        pieChart.setTitle("Staff Position");
        pieChart.setLegendSide(Side.BOTTOM );
        pieChart.setLabelsVisible(true);

        for(final PieChart.Data data : pieChart.getData())
        {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    status.setText(String.valueOf(data.getPieValue())+ " %");
                }
            });
        }

        return pieChart;
    }

    public static PieChart roomsChart(PieChart pieChart, Label statusi) throws Exception {
        List<RoomChartModel> rooms_list = RoomRespository.selectAllOrdeByRoomType();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        int total = 0;

        for (RoomChartModel sc : rooms_list) {
            total += sc.getTotal();
        }

        for (RoomChartModel sc : rooms_list) {
            pieChartData.addAll(new PieChart.Data(sc.getRoom_type(),calculatePercentage(sc.getTotal(),total)));
        }

        pieChart = new PieChart();
        pieChart.setData(pieChartData);
        pieChart.setTitle("Rooms Type Chart");
        pieChart.setLegendSide(Side.BOTTOM );
        pieChart.setLabelsVisible(true);

        for(final PieChart.Data data : pieChart.getData())
        {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    statusi.setText(Double.toString(data.getPieValue())+" %");
                }
            });
        }

        return pieChart;
    }

    public static PieChart roomFloorChart(PieChart pieChart, Label status) throws Exception {
        List<RoomChartModel> room_list = RoomRespository.selectAllGroupByFloorNum();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        int totali = 0;

        for (RoomChartModel sc : room_list) {
            totali += sc.getTotal();
        }

        for (RoomChartModel sc : room_list) {
            pieChartData.addAll(new PieChart.Data(Integer.toString(sc.getFloor_number()),calculatePercentage(sc.getTotal(),totali)));
        }

        pieChart = new PieChart();
        pieChart.setData(pieChartData);
        pieChart.setTitle("Reservations by floor");
        pieChart.setLegendSide(Side.BOTTOM );
        pieChart.setLabelsVisible(true);

        for(final PieChart.Data data : pieChart.getData())
        {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    status.setText(String.valueOf(data.getPieValue())+ " %");
                }
            });
        }

        return pieChart;
    }

    public static double calculatePercentage(double obtained, double total) {
        return Math.round(obtained * 100 / total);
    }
}
