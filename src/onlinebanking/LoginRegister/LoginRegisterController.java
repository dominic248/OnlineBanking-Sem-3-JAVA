/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.LoginRegister;

import javafx.scene.layout.Pane;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
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
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import onlinebanking.database.DBConnected;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author dms
 */
public class LoginRegisterController implements Initializable {

    LoginModel loginModel = new LoginModel();
    DBConnected dbConnected = new DBConnected();
    RegisterModel registerModel = new RegisterModel();

    @FXML
    private Tab RegisterTab;
    
    @FXML
    private Tab LoginTab;
    
    @FXML
    private Label LisConnected;

    @FXML
    private Pane LrootPane;

    @FXML
    private JFXButton Llogin_btn;

    @FXML
    private JFXPasswordField Lpassword;

    @FXML
    private JFXTextField Lusername;

    @FXML
    private JFXPasswordField Rpassword;

    @FXML
    private JFXTextField Raddress;

    @FXML
    private JFXTextField Rmobile;

    @FXML
    private Label RisConnected;

    @FXML
    private JFXButton Rregister_btn;

    @FXML
    private JFXTextField Remail;

    @FXML
    private JFXTextField Rusername;

    @FXML
    private Label RisUsername;

    @FXML
    private Label RisAddress;

    @FXML
    private Label RisPassword;

    @FXML
    private Label RisEmail;

    @FXML
    private Label RisMobile;

    @FXML
    private JFXTabPane LoginRegisterTab;

    @FXML
    private StackPane root;

    @FXML
    public void Login(ActionEvent event) throws SQLException, IOException {
        Stage stage;
        Parent loader;
        if (Lusername.getText().isEmpty() && Lpassword.getText().isEmpty()) {
            LisConnected.setText("Both are Empty");
            return;
        } else if (Lpassword.getText().isEmpty()) {
            LisConnected.setText("Password?");
            return;
        } else if (Lusername.getText().isEmpty()) {
            LisConnected.setText("Account?");
            return;
        } else {
            try {
                if (loginModel.isLogin(Lusername.getText(), Lpassword.getText())) {
                    LisConnected.setText("Username and password is correct");
                    stage = (Stage) Llogin_btn.getScene().getWindow();
                    loader = FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/DisplayPage.fxml"));
                    Scene scene = new Scene(loader);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    LisConnected.setText("Username and password is wrong");
                }
            } catch (SQLException e) {
                LisConnected.setText("Username and password is wrong");
                e.printStackTrace();
            }
        }
    }

    public void Register(ActionEvent event) throws SQLException {
        if (Rusername.getText().isEmpty()) {
            RisUsername.setText("Username cannot be blank!");
        } else if (registerModel.ifUsernameExists(Rusername.getText())) {
            RisUsername.setText("Username already exists!");
        } else {
            RisUsername.setText("");
        }
        if (Rpassword.getText().isEmpty()) {
            RisPassword.setText("Password cannot be blank!");
        } else {
            RisPassword.setText("");
        }
        if (Raddress.getText().isEmpty()) {
            RisAddress.setText("Address cannot be blank!");
        } else {
            RisAddress.setText("");
        }
        if (Remail.getText().isEmpty()) {
            RisEmail.setText("Email ID cannot be blank!");
        } else {
            RisEmail.setText("");
        }
        if (Rmobile.getText().isEmpty()) {
            RisMobile.setText("Mobile No. cannot be blank!");
        } else {
            RisMobile.setText("");
        }

        if (Rusername.getText().isEmpty() || Rpassword.getText().isEmpty()
                || Raddress.getText().isEmpty() || Remail.getText().isEmpty()
                || Rmobile.getText().isEmpty() || registerModel.ifUsernameExists(Rusername.getText())) {
            return;
        } else {
            if(registerModel.isRegister(Rusername.getText(), Rpassword.getText(), Raddress.getText(), Remail.getText(), Integer.parseInt(Rmobile.getText()))){
                System.out.println("Hello");
                LoginRegisterTab.getSelectionModel().select(0);
            }           
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoginRegisterTab.widthProperty().addListener((observable, oldValue, newValue)
                -> {
            LoginRegisterTab.setTabMinWidth((LoginRegisterTab.getWidth() - 10) / 2);
            LoginRegisterTab.setTabMaxWidth((LoginRegisterTab.getWidth() - 10) / 2);
        });

        if (dbConnected.isDbConnected()) {
            LisConnected.setText("Connected");
        } else {
            LisConnected.setText("Disconnected");
        }

    }
}
