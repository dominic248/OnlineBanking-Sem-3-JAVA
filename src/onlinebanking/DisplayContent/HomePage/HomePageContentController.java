package onlinebanking.DisplayContent.HomePage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static onlinebanking.DisplayContent.AccountsPage.AccountsPageContentController.acc_id;
import onlinebanking.LoginRegister.LoginModel;
import onlinebanking.OnlineBanking;
import onlinebanking.database.SqliteConnection;

public class HomePageContentController implements Initializable {

    Connection connection;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    static ResultSet resultSet1 = null;

    public HomePageContentController() {
        connection = SqliteConnection.connector();
        if (connection == null) {
            System.exit(1);
        }
    }

    int uid;
    @FXML
    private AnchorPane depositAnchor;

    @FXML
    private Label depositTitle;

    @FXML
    private AnchorPane withdrawAnchor, mainAnchor;

    @FXML
    private Label withdrawTitle;

    @FXML
    private AnchorPane transferAnchor;

    @FXML
    private AnchorPane balanceAnchor;

    @FXML
    private Label balanceTitle;

    @FXML
    private AnchorPane transactionAnchor;

    @FXML
    private Label transactionTitle;

    @FXML
    private AnchorPane activityAnchor;

    public static int index = 0;

    @FXML
    private Label delUserAcc;

    @FXML
    void delUserAcc(MouseEvent event) {
        String query = "delete from users where uid=" + uid + ";";
        
        System.out.println(query);
   
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            Stage stage;
            Parent loader;

            loader = FXMLLoader.load(getClass().getResource("/onlinebanking/LoginRegister/LoginRegisterPage.fxml"));
            stage = OnlineBanking.stage;
            stage.getScene().setRoot(loader);
            stage.show();     
            
            LoginModel.uid = 0;
            System.out.println("Deleted");
        } catch (SQLException e) {
            System.out.println("Failed");
        } catch (IOException ex) {
            Logger.getLogger(HomePageContentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uid = LoginModel.uid;
        depositAnchor.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                try {
                    mainAnchor.getChildren().clear();
                    index = 1;
                    mainAnchor.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/FundsPage/FundsPageContent.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(HomePageContentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        withdrawAnchor.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                try {
                    mainAnchor.getChildren().clear();
                    index = 2;
                    mainAnchor.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/FundsPage/FundsPageContent.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(HomePageContentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        transferAnchor.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                try {
                    mainAnchor.getChildren().clear();
                    index = 3;
                    mainAnchor.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/FundsPage/FundsPageContent.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(HomePageContentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        balanceAnchor.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                try {
                    mainAnchor.getChildren().clear();
                    index = 4;
                    mainAnchor.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/FundsPage/FundsPageContent.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(HomePageContentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        transactionAnchor.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                try {
                    mainAnchor.getChildren().clear();
                    index = 5;
                    mainAnchor.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/ActivityPage/ActivityPageContent.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(HomePageContentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        activityAnchor.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                try {
                    mainAnchor.getChildren().clear();
                    index = 6;
                    mainAnchor.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/ActivityPage/ActivityPageContent.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(HomePageContentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
}
