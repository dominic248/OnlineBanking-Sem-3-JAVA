package onlinebanking.DisplayContent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DisplayController implements Initializable {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private JFXDrawer mainDrawer;

    @FXML
    private Pane mainHeader;

    @FXML
    private JFXHamburger mainHamburg;


    public void closeDrawer() {
        if (mainDrawer.isOpened()) {
            mainDrawer.close();
            mainDrawer.toBack();
        }
    }

    public void openDrawer() {
        if (mainDrawer.isClosed()) {
            mainDrawer.open();
            mainDrawer.toFront();
        }
    }
    
    
//
//    public void openHomepage() {
//        AnchorPane newLoadedPane;
//        try {
//            newLoadedPane = FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/HomePageContent.fxml"));
//            mainAnchorPane.getChildren().add(newLoadedPane);
//        } catch (IOException ex) {
//            Logger.getLogger(mainDrawerContentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

//    public void openAccountDetailsPage() {
//        AnchorPane newLoadedPane;
//        try {
//            newLoadedPane = FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/AccountDetailsPageContent.fxml"));
//            mainAnchorPane.getChildren().add(newLoadedPane);
//        } catch (IOException ex) {
//            Logger.getLogger(mainDrawerContentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            AnchorPane newLoadedPane;
            VBox box = FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/mainDrawerContent.fxml"));

            mainDrawer.setSidePane(box);

            for (Node node : box.getChildren()) {
                System.out.println(node.getId());
                if (node.getId() != null) {

                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                        
                        switch (node.getId()) {
                            case "drawerHome": try {
                                    mainAnchorPane.getChildren().clear();
                                    mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/HomePageContent.fxml")));
                                } catch (IOException ex) {
                                    Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            case "drawerAccountDetails": try {
                                    mainAnchorPane.getChildren().clear();
                                    mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/AccountDetailsPageContent.fxml")));
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
            newLoadedPane = FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/HomePageContent.fxml"));
            mainAnchorPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
