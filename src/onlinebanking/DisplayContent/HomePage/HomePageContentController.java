package onlinebanking.DisplayContent.HomePage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class HomePageContentController implements Initializable {

    @FXML
    private AnchorPane depositAnchor;

    @FXML
    private Label depositTitle;

    @FXML
    private AnchorPane withdrawAnchor,mainAnchor;

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

    public static int index=0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        depositAnchor.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                try {
                    mainAnchor.getChildren().clear();
                    index=1;
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
                    index=2;
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
                    index=3;
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
                    index=4;
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
                    index=5;
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
                    index=6;
                    mainAnchor.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/ActivityPage/ActivityPageContent.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(HomePageContentController.class.getName()).log(Level.SEVERE, null, ex);
                }                                   
            }
        });

    }
}
