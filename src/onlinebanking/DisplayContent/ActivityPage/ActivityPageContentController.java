/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.DisplayContent.ActivityPage;

import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

/**
 *
 * @author dms
 */
public class ActivityPageContentController implements Initializable {

    @FXML
    private Tab BalanceTab;

    @FXML
    private JFXTabPane mainActivityTab;

    @FXML
    private Tab ActivityTab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainActivityTab.widthProperty().addListener((observable, oldValue, newValue)
                -> {
            mainActivityTab.setTabMinWidth((mainActivityTab.getWidth() - 10) / 2);
            mainActivityTab.setTabMaxWidth((mainActivityTab.getWidth() - 10) / 2);

        });
        
    }

}
