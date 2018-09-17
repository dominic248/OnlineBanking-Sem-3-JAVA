/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.register;

/**
 *
 * @author dms
 */
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import database.DBConnected;
import database.SqliteConnection;
import java.sql.Connection;
import java.sql.Statement;
import onlinebanking.login.LoginModel;
import database.SqliteConnection;

public class RegisterController implements Initializable {

    Connection conn;


    RegisterModel registerModel = new RegisterModel();
    DBConnected dbConnected = new DBConnected();

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField mobile;

    @FXML
    private Label isConnected;

    @FXML
    private JFXButton register_btn;

    @FXML
    private JFXButton login_btn;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField username;

    @FXML
    private Label isUsername;

    @FXML
    private Label isAddress;

    @FXML
    private Label isPassword;

    @FXML
    private Label isEmail;

    @FXML
    private Label isMobile;

    @FXML
    public void LoginPage(ActionEvent event) throws IOException {
        Stage stage;
        Parent loader;
        if (event.getSource() == login_btn) {
            stage = (Stage) login_btn.getScene().getWindow();
            loader = FXMLLoader.load(getClass().getResource("/onlinebanking/login/LoginPage.fxml"));

            Scene scene = new Scene(loader);
            stage.setScene(scene);
            stage.show();

        }
    }

    public void Register(ActionEvent event) throws SQLException {
        if (username.getText().isEmpty()) {
            isUsername.setText("Username cannot be blank!");
        } else if (registerModel.ifUsernameExists(username.getText())) {
            isUsername.setText("Username already exists!");
        } else {
            isUsername.setText("");
        }
        if (password.getText().isEmpty()) {
            isPassword.setText("Password cannot be blank!");
        } else {
            isPassword.setText("");
        }
        if (address.getText().isEmpty()) {
            isAddress.setText("Address cannot be blank!");
        } else {
            isAddress.setText("");
        }
        if (email.getText().isEmpty()) {
            isEmail.setText("Email ID cannot be blank!");
        } else {
            isEmail.setText("");
        }
        if (mobile.getText().isEmpty()) {
            isMobile.setText("Mobile No. cannot be blank!");
        } else {
            isMobile.setText("");
        }

        if (username.getText().isEmpty() || password.getText().isEmpty()
                || address.getText().isEmpty() || email.getText().isEmpty()
                || mobile.getText().isEmpty()||registerModel.ifUsernameExists(username.getText())) {
            return;
        }
        else{
            registerModel.isRegister(username.getText(), password.getText(),address.getText(),email.getText(),Integer.parseInt(mobile.getText()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (dbConnected.isDbConnected()) {
            isConnected.setText("Connected");
        } else {
            isConnected.setText("Disconnected");
        }

    }

}
