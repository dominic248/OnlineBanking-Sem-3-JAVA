package onlinebanking.DisplayContent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import onlinebanking.DisplayContent.HomePage.HomePageContentController;
import onlinebanking.LoginRegister.LoginModel;
import onlinebanking.OnlineBanking;
import onlinebanking.database.SqliteConnection;

public class DisplayController implements Initializable {

    
    public static int uid;
    LoginModel l=new LoginModel();
    Connection connection;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    public DisplayController() {
        connection = SqliteConnection.connector();
        if (connection == null) {
            System.exit(1);
        }
    }
    public String getUsername() throws SQLException {
        String query = "SELECT * FROM users WHERE uid=" + uid + ";";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return resultSet.getString("name");
        } catch (SQLException e) {
            return null;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
    public Image getImage() throws SQLException {
        String query = "SELECT * FROM users WHERE uid=" + uid + ";";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.getBytes("uImage")==null){
                return null;
            }else{
                InputStream input = new ByteArrayInputStream(resultSet.getBytes("uImage"));
                Image image = new Image(input);
                return image;
            }         
        } catch (SQLException e) {
            return null;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    Stage stage;


    @FXML
    private Label mainHeading;

    @FXML
    private AnchorPane mainAnchorPane,rootAnchor;

    @FXML
    private JFXDrawer mainDrawer;

    @FXML
    private  Pane mainHeader;

    @FXML
    private JFXHamburger mainHamburg;
    
    @FXML
    private JFXButton drawerLogout;

    public void closeDrawer() {
        if (mainDrawer.isOpened()) {
            mainDrawer.close();
            mainDrawer.toBack();
            rootAnchor.toBack();
            rootAnchor.setStyle("-fx-background-color: white;");
        }
    }

    public void openDrawer() {
        if (mainDrawer.isClosed()) {
            mainDrawer.open();
            rootAnchor.toFront();
            rootAnchor.setStyle("-fx-background-color: #d2d2d2;");
            mainDrawer.toFront();
        }
    }

   
       

    

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        mainAnchorPane.getStylesheets().addAll(getClass().getResource("/onlinebanking/style.css").toExternalForm());
        uid = LoginModel.uid;
        try {
            AnchorPane newLoadedPane;
            AnchorPane box = FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/mainDrawerContent.fxml"));

            mainDrawer.setSidePane(box);

            for (Node node : box.getChildren()) {
                System.out.println(node.getId());
                if (node instanceof Label) {
                    try {
                        ((Label) node).setText(getUsername());
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (node instanceof ImageView) {
                    try {
                        ((ImageView) node).setImage(getImage());
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if (node.getId() != null) {

                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {

                        switch (node.getId()) {
                            case "drawerHome":
                                try {
                                    mainAnchorPane.getChildren().clear();
                                    mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/HomePage/HomePageContent.fxml")));
                                    mainHeading.setText("Home");
                                    closeDrawer();
                                } catch (IOException ex) {
                                    Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            case "drawerAccounts":
                                try {
                                    mainAnchorPane.getChildren().clear();
                                    mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/AccountsPage/AccountsPageContent.fxml")));
                                    mainHeading.setText("Accounts");
                                    closeDrawer();
                                } catch (IOException ex) {
                                    Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            case "drawerFunds":
                                try {
                                    mainAnchorPane.getChildren().clear();
                                    mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/FundsPage/FundsPageContent.fxml")));
                                    mainHeading.setText("Funds");
                                    closeDrawer();
                                } catch (IOException ex) {
                                    Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;


                            case "drawerActivity":
                                try {
                                    mainAnchorPane.getChildren().clear();
                                    mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/ActivityPage/ActivityPageContent.fxml")));
                                    mainHeading.setText("Activity");
                                    closeDrawer();
                                } catch (IOException ex) {
                                    Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            
                            case "drawerAbout":
                                try {
                                    mainAnchorPane.getChildren().clear();
                                    mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/AboutPage/AboutPageContent.fxml")));
                                    
                                    mainHeading.setText("About");
                                    closeDrawer();
                                } catch (IOException ex) {
                                    Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            case "drawerLogout":
                                try {
                                    
                                    Stage stage;
                                    Parent loader;

                                    loader = FXMLLoader.load(getClass().getResource("/onlinebanking/LoginRegister/LoginRegisterPage.fxml"));
                                    
                                    stage = OnlineBanking.stage;
                                    stage.getScene().setRoot(loader);
                                    stage.show();
                                    l.setLogoutTime();
                                    LoginModel.uid=0;
                                    

                                } catch (IOException ex) {
                                    Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;

                        }
                    });
                }
            }

            mainHamburg.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                openDrawer();
            });
            mainAnchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                closeDrawer();
            });
            mainHeader.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                closeDrawer();
            });
            mainDrawer.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                closeDrawer();
            });
            rootAnchor.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                closeDrawer();
            });
            newLoadedPane = FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/HomePage/HomePageContent.fxml"));
            mainAnchorPane.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
