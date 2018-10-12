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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import onlinebanking.database.DBConnected;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author dms
 */
public class LoginRegisterController implements Initializable {

    Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    Pattern usernamePattern = Pattern.compile("^[a-z0-9_-]{6,14}$");
    LoginModel loginModel = new LoginModel();
    DBConnected dbConnected = new DBConnected();
    RegisterModel registerModel = new RegisterModel();

   

    @FXML
    private AnchorPane RrootPane;

    @FXML
    private Label RisEmail;

    @FXML
    private Tab RegisterTab;

    @FXML
    private Label RisUsername;

    @FXML
    private JFXTextField Raddress;

    @FXML
    private StackPane root;

    @FXML
    private Label RisAddress;

    @FXML
    private JFXTextField Lusername;

    @FXML
    private JFXPasswordField Rpassword;

    @FXML
    private Label Lispassword;

    @FXML
    private Label RisMobile;

    @FXML
    private JFXButton Rregister_btn;

    @FXML
    private JFXButton Llogin_btn;

    @FXML
    private JFXTextField Remail;

    @FXML
    private Tab LoginTab;

    @FXML
    private Label Lisusername;

    @FXML
    private JFXTabPane LoginRegisterTab;

    @FXML
    private JFXTextField Rname;

    @FXML
    private AnchorPane LrootPane;

    @FXML
    private Label RisName;

    @FXML
    private Label RisPassword;

    @FXML
    private JFXPasswordField Lpassword;

    @FXML
    private JFXTextField Rusername;

    @FXML
    private JFXTextField Rmobile;
    
    @FXML
    private JFXButton browse;

    @FXML
    private BorderPane imageBorder;

    @FXML
    private ImageView uImage;
    
    Stage stage;
    FileChooser fileChoose;    
    private Image image;
    public static File file;
    private FileInputStream imagefis;

    
    public void browseImage(ActionEvent event) {
        fileChoose = new FileChooser();
        fileChoose.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        file = fileChoose.showOpenDialog(this.stage);
        if (file != null) {
            String s = file.getAbsolutePath();
            System.out.println(s);
            image = new Image(file.toURI().toString());
            uImage.setImage(image);
        }
    }
    
    @FXML
    public void Login(ActionEvent event) throws SQLException, IOException {
        Stage stage;
        Parent loader;
        if (Lusername.getText().isEmpty() && Lpassword.getText().isEmpty()) {
            Lisusername.setText("Username cannot be Empty");
            Lispassword.setText("Password cannot be Empty");
            return;
        } else if (Lpassword.getText().isEmpty()) {
            Lispassword.setText("Password cannot be Empty");
            Lisusername.setText("");
            return;
        } else if (Lusername.getText().isEmpty()) {
            Lisusername.setText("Username cannot be Empty");
            Lispassword.setText("");
            return;
        } else {
            Lisusername.setText("");
            Lispassword.setText("");
            try {
                if (loginModel.isLogin(Lusername.getText(), Lpassword.getText())) {
                    Lispassword.setText("Username and password is correct");
                    stage = (Stage) Llogin_btn.getScene().getWindow();
                    loader = FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/DisplayPage.fxml"));        
                    Scene scene = new Scene(loader);
                    scene.getStylesheets().addAll(getClass().getResource("/onlinebanking/style.css").toExternalForm());
                    stage.setScene(scene);
                    stage.show();
                    loginModel.setLoginTime();
                } else {
                    Lispassword.setText("Username or password is wrong");
                }
            } catch (SQLException e) {
                Lispassword.setText("Username or password is wrong");
                e.printStackTrace();
            }
        }
    }

    public void Register(ActionEvent event) throws SQLException, FileNotFoundException {
        if (Rusername.getText().isEmpty()) {
            RisUsername.setText("Username cannot be blank!");
        } else if (registerModel.ifUsernameExists(Rusername.getText())) {
            RisUsername.setText("Username already exists!");
        } else {
            RisUsername.setText("");
        }
        if (Rname.getText().isEmpty()) {
            RisName.setText("Name cannot be blank!");
        } else {
            RisName.setText("");
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
        if(uImage.getImage()==null){
            System.out.println("No Image");
            file=null;            
        }else{
            System.out.println("Image present");
        }

        if (Rusername.getText().isEmpty() || Rpassword.getText().isEmpty() || Rname.getText().isEmpty()
                || Raddress.getText().isEmpty() || Remail.getText().isEmpty()
                || Rmobile.getText().isEmpty() || registerModel.ifUsernameExists(Rusername.getText())) {
            return;
        } else {
            if (registerModel.isRegister(Rname.getText(),Rusername.getText(), Rpassword.getText(), Raddress.getText(), Remail.getText(), Rmobile.getText())) {
                System.out.println("Done");
                Rusername.setText("");
                Rpassword.setText("");
                Rname.setText("");
                Raddress.setText("");
                Remail.setText("");
                Rmobile.setText("");
                uImage.setImage(null);
                RisUsername.setText("");
                RisEmail.setText("");
                LoginRegisterTab.getSelectionModel().select(0);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoginRegisterTab.widthProperty().addListener((observable, oldValue, newValue)
                -> {
            LoginRegisterTab.setTabMinWidth(((LoginRegisterTab.getWidth()) / 2)-5);
            LoginRegisterTab.setTabMaxWidth(((LoginRegisterTab.getWidth()) / 2)-5);

        });

        Rmobile.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                Rmobile.setText(oldValue);
            }
        });



        Rusername.textProperty().addListener((observable, oldValue, newValue) -> {

            Matcher matcher = usernamePattern.matcher(Rusername.getText());
            if (!matcher.matches()) {
                RisUsername.setText("Invalid Username!");
            } else {
                RisUsername.setText("");

            }

        });
        Remail.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = emailPattern.matcher(Remail.getText());
            if (!matcher.matches()) {
                RisEmail.setText("Invalid Email!");
            } else {
                RisEmail.setText("");
            }
        });
    }
}
