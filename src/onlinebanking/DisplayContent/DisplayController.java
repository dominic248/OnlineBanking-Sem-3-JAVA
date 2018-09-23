package onlinebanking.DisplayContent;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;

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
                            case "drawerHome":
                                try {
                                    mainAnchorPane.getChildren().clear();
                                    mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/HomePage/HomePageContent.fxml")));
                                    closeDrawer();
                                } catch (IOException ex) {
                                    Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            case "drawerAccounts":
                                try {
                                    mainAnchorPane.getChildren().clear();
                                    mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/AccountsPage/AccountsPageContent.fxml")));
                                    closeDrawer();
                                } catch (IOException ex) {
                                    Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            case "drawerFunds":
                                try {
                                    mainAnchorPane.getChildren().clear();
                                    mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/FundsPage/FundsPageContent.fxml")));
                                    closeDrawer();
                                } catch (IOException ex) {
                                    Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
//                                case "drawerBalance": try {
//                                    mainAnchorPane.getChildren().clear();
//                                    mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/AccountDetailsPageContent.fxml")));
//                                    closeDrawer();
//                                } catch (IOException ex) {
//                                    Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                                break;

                            case "drawerActivity":
                                try {
                                    mainAnchorPane.getChildren().clear();
                                    mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/ActivityPage/ActivityPageContent.fxml")));
                                    closeDrawer();
                                } catch (IOException ex) {
                                    Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            case "drawerAbout":
                                try {
                                    mainAnchorPane.getChildren().clear();
                                    mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/AboutPage/AboutPageContent.fxml")));
                                    closeDrawer();
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
            newLoadedPane = FXMLLoader.load(getClass().getResource("/onlinebanking/DisplayContent/HomePage/HomePageContent.fxml"));
            mainAnchorPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
