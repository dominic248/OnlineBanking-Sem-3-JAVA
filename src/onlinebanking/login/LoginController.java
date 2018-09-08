/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


/**
 *
 * @author dms
 */
public class LoginController implements Initializable {

    public LoginModel loginModel = new LoginModel();

    @FXML
    private Label isConnected;

    @FXML
    private Pane rootPane;

    @FXML
    private JFXButton login_btn;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private Label welcome;

    @FXML
    private JFXTextField acc_no;
    
    @FXML
    private JFXButton createAcc_btn;

    
    @FXML
    public void CreateAccount(ActionEvent event) throws IOException {
        Stage stage;
        Parent loader;
        if(event.getSource()==createAcc_btn){
            stage = (Stage) createAcc_btn.getScene().getWindow();
            loader=FXMLLoader.load(getClass().getResource("/onlinebanking/register/RegisterPage.fxml"));

            Scene scene = new Scene(loader);
            stage.setScene(scene);
            stage.show();

        }
    }
    @FXML
    
    public void Login(ActionEvent event) throws SQLException {
        if (acc_no.getText().isEmpty() && pass.getText().isEmpty()) {
            isConnected.setText("Both are Empty");
            return;
        } else if (pass.getText().isEmpty()) {
            isConnected.setText("Password?");
            return;
        } else if (acc_no.getText().isEmpty()) {
            isConnected.setText("Account?");
            return;
        } else {
            try {
                if (loginModel.isLogin(acc_no.getText(), pass.getText())) {
                    isConnected.setText("Username and password is correct");
                } else {
                    isConnected.setText("Username and password is wrong");
                }
            } catch (SQLException e) {
                isConnected.setText("Username and password is wrong");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
        if (loginModel.isDbConnected()) {
            isConnected.setText("Connected");
        } else {
            isConnected.setText("Disconnected");
        }
    }

}
