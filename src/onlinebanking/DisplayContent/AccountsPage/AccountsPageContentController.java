/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.DisplayContent.AccountsPage;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;


/**
 *
 * @author dms
 */
public class AccountsPageContentController implements Initializable {

    AccountsPageContentModel AccModel = new AccountsPageContentModel();
    Random random = new Random();
    ObservableList<String> Macc_type = FXCollections.observableArrayList("Student", "Savings","Current");

    @FXML
    private JFXTextField caAcc_no;

    @FXML
    private Tab AccountDetailsTab;

    @FXML
    private Tab CreateAccountTab;

    @FXML
    private JFXTabPane mainAccountsTab;

    @FXML
    private JFXComboBox<String> caAcc_type;

    @FXML
    private JFXTextArea caAcc_desc;

    @FXML
    private Label iscaAcc_type;
    @FXML
    private JFXTextField caAccAmount;

    public void CreateAccount(ActionEvent event) throws SQLException {
        if (caAcc_type.getValue() != null) {
            iscaAcc_type.setText("");
            System.out.println(caAcc_type.getValue());
        } else {
            iscaAcc_type.setText("Please choose the type!");
        }

        if ((caAcc_no.getText().isEmpty() || caAcc_type.getValue() == null) || caAcc_desc.getText().isEmpty()) {
            System.out.println("?");
        } else {
            if (!AccModel.accTypeExists(caAcc_type.getValue())) {
                iscaAcc_type.setText("");
                if (AccModel.isCreateAccount(Integer.parseInt(caAcc_no.getText()), caAcc_type.getValue(), caAcc_desc.getText(),Integer.parseInt(caAccAmount.getText()))) {
                    System.out.println("Done");
                    mainAccountsTab.getSelectionModel().select(0);
                }
            }else{
                iscaAcc_type.setText("Account Type Exists");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainAccountsTab.widthProperty().addListener((observable, oldValue, newValue) -> {
            mainAccountsTab.setTabMinWidth((mainAccountsTab.getWidth() - 10) / 2);
            mainAccountsTab.setTabMaxWidth((mainAccountsTab.getWidth() - 10) / 2);

        });
        CreateAccountTab.setOnSelectionChanged((Event e) -> {
            caAcc_no.setText(String.valueOf(random.nextInt(10000) + 100));
        });
        caAcc_type.setItems(Macc_type);

    }

}
