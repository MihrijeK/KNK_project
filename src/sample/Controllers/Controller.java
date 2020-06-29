package sample;

import DatabaseConnection.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class Controller implements Initializable {
    @FXML private Label ADMINLOGIN;

    @FXML private Button login;
    @FXML private ImageView sunhotel;
    @FXML private TextField username;
    @FXML private PasswordField password;
     PreparedStatement pst;
    @FXML
     void login(ActionEvent event) throws Exception {
        String uname=username.getText();
        String pass=password.getText();
 Connection con= dbConnection.getConnection();
        ResultSet rs ;
         if(uname.equals("") || pass.equals("")){
            JOptionPane.showMessageDialog(null,"Duhet te shenoni username dhe password");
        }
 else{
               try {
                   pst=con.prepareStatement("select * from staff where personal_number=?");
                   pst.setString(1,uname);
                   rs= pst.executeQuery();