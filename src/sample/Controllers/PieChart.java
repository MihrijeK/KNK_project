package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.event.EventHandler.*;
import javafx.scene.text.FontWeight;
import javafx.scene.input.MouseEvent;
import javax.swing.*;
import java.awt.*;
//import java.awt.event.MouseEvent;
import java.awt.event.InputEvent;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;


public class PieChart implements Initializable {
    @FXML private PieChart pieChart;
    @FXML private Label status;
    ObservableList<PieChart.Data> pieChartData;
    ArrayList<Integer> price=new ArrayList<Integer>();
    ArrayList<String> service_name= new ArrayList<String>();

   public void loadData()
    {
        pieChartData= FXCollections.observableArrayList();
        String url="jdbc:mysql://localhost:3306/dbhotel?autoReconnect=true&useSSL=false";
        String userName = "root";
        String password = "riseandshine";
        try {
            Connection connection= DriverManager.getConnection(url, userName, password);
            //JOptionPane.showMessageDialog(null,"Connected");
           Statement myStmt = connection.createStatement();
            String sql = "select * from dbhotel.services_type ";
            ResultSet rs = myStmt.executeQuery(sql);
            while (rs.next())
                pieChartData.add(new PieChart.Data(rs.getString("service_name"),rs.getInt("price")));
            service_name.add(rs.getString("service_name"));
            price.add(rs.getInt("price"));
            //System.out.println(rs.getString("service_name"));
           //label=new Label();
            //pieChart.getData().stream().forEach(data -> {
              //  data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                //    label.setText(data.getName() + "" + data.getPieValue() + "%\n");
                //});
            //});
            for(final PieChart.Data data : pieChart.getData())
            {
                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        status.setText(String.valueOf(data.getPieValue())+ " %");
                    }
                });
            }
        } catch (SQLException throwables) {

            //JOptionPane.showMessageDialog(null,throwables);
            throwables.printStackTrace();
        }

   }
    
     public  int g1()
    {
        int x=0;
        Iterator itr= price.iterator();
        while(itr.hasNext())
        {
            x=(int)itr.next();
        }
        return x;
    }
    
    public  String g2()
    {
        String x="";
        Iterator serviceitr=service_name.iterator();
        while(serviceitr.hasNext())
        {
            x=(String) serviceitr.next();
        }
        return x;
    }
   
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        pieChart.setData(pieChartData);
    }
}
