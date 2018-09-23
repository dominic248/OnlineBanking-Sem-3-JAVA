/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.DisplayContent.FundsPage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

/**
 *
 * @author dms
 */
public class FundsPageContentController implements Initializable {

    FundsPageContentModel fundsModel = new FundsPageContentModel();
    @FXML
    private Tab WithdrawTab;

    @FXML
    private Tab DepositTab;

    @FXML
    private JFXButton Wsubmit;

    @FXML
    private Tab BalanceTab;

    @FXML
    private JFXTextField Wamount;

    @FXML
    private JFXTabPane mainFundsTab;

    @FXML
    private JFXComboBox<String> Daccnotype;

    @FXML
    private JFXComboBox<String> Waccnotype;

    @FXML
    private JFXTextField Damount;

    @FXML
    private JFXButton Dsubmit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainFundsTab.widthProperty().addListener((observable, oldValue, newValue)
                -> {
            mainFundsTab.setTabMinWidth((mainFundsTab.getWidth() - 13) / 3);
            mainFundsTab.setTabMaxWidth((mainFundsTab.getWidth() - 13) / 3);

        });
        Damount.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                Damount.setText(oldValue);
            }
        });
        try {
            boolean a = fundsModel.getAccounts();
//        Waccnotype.setItems(Macc_type);
//        Daccnotype.setItems(Macc_type);
        } catch (SQLException ex) {
            Logger.getLogger(FundsPageContentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
