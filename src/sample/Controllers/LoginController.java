package sample.Controllers;

import DatabaseConnection.dbConnection;
import Helpers.LangEnum;
import Helpers.SecurityHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.utils.SessionManager;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private ChoiceBox lang_CB;
    @FXML private Label ADMINLOGIN;

    @FXML private Button login;
    @FXML private ImageView sunhotel;
    @FXML private TextField username;
    @FXML private PasswordField password;
    PreparedStatement pst;
    @FXML
    void login(ActionEvent event) throws Exception {
        SessionManager.setLang((LangEnum) lang_CB.getValue());
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
                   if(rs.next()){
                       SecurityHelper security= new SecurityHelper();

                       boolean password_check= security.checkPassword(pass,rs.getString("passwordd"));
                       if(password_check){
                           switch (rs.getString("position")){
                               case "Manager":
                                   //JOptionPane.showMessageDialog(null,"Jeni loguar si admin");
                                   URL url = new File("src/sample/Views/AdminDashboard.fxml").toURI().toURL();
                                   FXMLLoader loader = new FXMLLoader();
                                   loader.setLocation(url);
                                   Pane newScreen = loader.load();
                                   Scene scene=new Scene(newScreen);

                                   Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                                   stage.setScene(scene);
                                   stage.show();

                                   Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                                   stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
                                   stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);


                                   break;
                               case "Recepsionist":
                                  // JOptionPane.showMessageDialog(null,"Jeni loguar si staff");
                                  URL url1 = new File("src/sample/Views/main-manager.fxml").toURI().toURL();
                                   FXMLLoader loader1 = new FXMLLoader();
                                   loader1.setLocation(url1);
                                   Pane newScreen1 = loader1.load();
                                   MainManagingController mainController=loader1.getController();
                                   mainController.getUser(rs.getString("first_name"),rs.getString("last_name"));

                                   Scene scene1=new Scene(newScreen1);
                                   Stage stageMain=(Stage)((Node)event.getSource()).getScene().getWindow();
                                   stageMain.setScene(scene1);
                                   stageMain.show();

                                   Rectangle2D screenBoundsMain = Screen.getPrimary().getVisualBounds();
                                   stageMain.setX((screenBoundsMain.getWidth() - stageMain.getWidth()) / 2);
                                   stageMain.setY((screenBoundsMain.getHeight() - stageMain.getHeight()) / 2);
                                   break;
                           }
                       }
                       else{
                           JOptionPane.showMessageDialog(null,"Keni shenuar gabim username apo password");
                           username.setText("");
                           password.setText("");
                           username.requestFocus();
                       }

                   }
                   else{
                       JOptionPane.showMessageDialog(null,"Username nuk ekziston");
                       username.setText("");
                       password.setText("");
                       username.requestFocus();

                   }
               } catch (SQLException throwables) {
                   throwables.printStackTrace();
               }

        }

    }
    public LoginController() {
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        lang_CB.getItems().setAll(LangEnum.EN,LangEnum.AL);
        lang_CB.setValue(LangEnum.EN);
    }


}
