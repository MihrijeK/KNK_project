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


public class PieChart {
    @FXML private PieChart pieChart;
    @FXML private Label status;
    ObservableList<PieChart.Data> pieChartData;
    ArrayList<Integer> price=new ArrayList<Integer>();
    ArrayList<String> service_name= new ArrayList<String>();
  
  
}
