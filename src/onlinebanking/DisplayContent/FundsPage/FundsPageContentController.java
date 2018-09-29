/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.DisplayContent.FundsPage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Label;

import javafx.scene.control.Tab;


/**
 *
 * @author dms
 */
public class FundsPageContentController implements Initializable {

    public static String[] splited = new String[4];
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

    @FXML
    private Label isDamount;

    @FXML
    private Label isCurrentAcc;

    @FXML
    private Label isDaccnotype;

    @FXML
    private Label isWamount;

    @FXML
    private Label isWaccnotype;

    @FXML
    private Label isTransferAcc;

    @FXML
    private Label isTransferAmount;

    @FXML
    private JFXComboBox<String> currentAcc;

    @FXML
    private JFXTextField transferAcc;

    @FXML
    private JFXTextField transferAmount;


    @FXML
    private JFXListView<JFXButton> listBalance;

    public void Deposit() {
        if (Daccnotype.getValue() != null) {
            System.out.println(Daccnotype.getValue());
            splited = Daccnotype.getValue().split("\\s+");
            System.out.println(splited[0]);
            isDaccnotype.setText("");
        } else {
            isDaccnotype.setText("Select Account!");
            System.out.println("Error!");
        }
        if (Damount.getText().isEmpty()) {
            isDamount.setText("Please enter the amount!");
        } else {
            isDamount.setText("");
        }
        if ((Damount.getText().isEmpty() || Daccnotype.getValue() == null)) {
            System.out.println("?");
        } else {
            try {
                if (fundsModel.depositDone(Integer.parseInt(splited[0]), Integer.parseInt(Damount.getText()))) {
                    if (fundsModel.checkDeposit(Integer.parseInt(splited[0]), Integer.parseInt(Damount.getText()))) {
                        isDamount.setText("");
                    } else {
                        isDamount.setText("Insufficient amount!");
                    }
                    System.out.println("Done");
                } else {
                    System.out.println("failed");
                }
            } catch (SQLException ex) {
                Logger.getLogger(FundsPageContentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Withdraw() {
        if (Waccnotype.getValue() != null) {
            System.out.println(Waccnotype.getValue());
            splited = Waccnotype.getValue().split("\\s+");
            System.out.println(splited[0]);
            isWaccnotype.setText("");
        } else {
            isWaccnotype.setText("Select Account!");
            System.out.println("Error!");
        }
        if (Wamount.getText().isEmpty()) {
            isWamount.setText("Please enter the amount!");
        } else {
            isWamount.setText("");
        }
        if ((Wamount.getText().isEmpty() || Waccnotype.getValue() == null)) {
            System.out.println("?");
        } else {
            try {
                if (fundsModel.withdrawDone(Integer.parseInt(splited[0]), Integer.parseInt(Wamount.getText()))) {
                    if (fundsModel.checkWithdraw(Integer.parseInt(splited[0]), Integer.parseInt(Wamount.getText()))) {
                        isWamount.setText("");
                    } else {
                        isWamount.setText("Insufficient amount!");
                    }
                    System.out.println("Done");

                } else {
                    System.out.println("failed");
                }
            } catch (SQLException ex) {
                Logger.getLogger(FundsPageContentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Transfer() {
        if (currentAcc.getValue() != null) {
            System.out.println(currentAcc.getValue());
            splited = currentAcc.getValue().split("\\s+");
            System.out.println(splited[0]);
            isCurrentAcc.setText("");
        } else {
            isCurrentAcc.setText("Select Account!");
            System.out.println("Error!");
        }
        if (transferAcc.getText().isEmpty()) {
            isTransferAcc.setText("Please enter the Transfer Account!");
        } else {
            isTransferAcc.setText("");
        }
        if (transferAmount.getText().isEmpty()) {
            isTransferAmount.setText("Please enter the amount!");
        } else {
            isTransferAmount.setText("");
        }
        if ((transferAcc.getText().isEmpty() || currentAcc.getValue() == null)
                || transferAmount.getText().isEmpty()) {
            System.out.println("?");
        } else {
            try {
                if (fundsModel.transferDone(Integer.parseInt(splited[0]), Integer.parseInt(transferAcc.getText()), Integer.parseInt(transferAmount.getText()))) {
                    if (fundsModel.checkTransfer(Integer.parseInt(splited[0]), Integer.parseInt(transferAcc.getText()), Integer.parseInt(transferAmount.getText()))) {
                        isTransferAmount.setText("");
                    } else {
                        isTransferAmount.setText("Insufficient amount!");
                    }
                    System.out.println("Done");
                } else {
                    System.out.println("failed");
                }
            } catch (SQLException ex) {
                Logger.getLogger(FundsPageContentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainFundsTab.widthProperty().addListener((observable, oldValue, newValue)
                -> {
            mainFundsTab.setTabMinWidth(((mainFundsTab.getWidth()) / 4) - 3);
            mainFundsTab.setTabMaxWidth(((mainFundsTab.getWidth()) / 4) - 3);

        });
        Damount.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                Damount.setText(oldValue);
            }
        });
        try {
            ArrayList<String> acc_no = new ArrayList<String>();
            acc_no = fundsModel.getAccounts();
            ObservableList<String> acc_no_list = FXCollections.observableArrayList(acc_no);
            System.out.println(acc_no);
            Waccnotype.setItems(acc_no_list);
            Daccnotype.setItems(acc_no_list);
            currentAcc.setItems(acc_no_list);
        } catch (SQLException ex) {
            Logger.getLogger(FundsPageContentController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        for(int i=0;i<30;i++){
//            JFXButton butt=new JFXButton("item"+i);
//            listBalance.getItems().add(butt);
//        }   

    
    }
}
