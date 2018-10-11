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
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Label;

import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import onlinebanking.DisplayContent.ActivityPage.ActivityPageContentController;
import onlinebanking.DisplayContent.DisplayController;
import onlinebanking.DisplayContent.HomePage.HomePageContentController;

import onlinebanking.LoginRegister.LoginModel;
import onlinebanking.database.SqliteConnection;

/**
 *
 * @author dms
 */
public class FundsPageContentController implements Initializable {


    public static String[] splited = new String[4];

    Connection connection;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    static ResultSet resultSet1 = null;
    public static int acc_id;

    public FundsPageContentController() {
        connection = SqliteConnection.connector();
        if (connection == null) {
            System.exit(1);
        }
    }

    public ArrayList<String> getAccounts() throws SQLException {
        ArrayList<String> acc_no = new ArrayList<String>();
        String query = "select * from accounts where acc_id=" + acc_id + ";";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);

            System.out.println(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("acc_no"));
                System.out.println(resultSet.getString("acc_type"));
                acc_no.add(String.valueOf(resultSet.getInt("acc_no")) + " (" + resultSet.getString("acc_type") + ")");
            }
            return acc_no;
        } catch (SQLException e) {
            System.out.println("Error!" + e);
            return null;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public boolean withdrawDone(int acc_no, int amount) throws SQLException {
        String query = "INSERT INTO `withdraw` (waid,wAmount,wDate) VALUES (" + acc_no + "," + amount + ",datetime('now', 'localtime'));\n";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Error!" + e);
            return false;
        } finally {
            preparedStatement.close();

        }
    }

    public boolean checkWithdraw(int acc_no, int amount) throws SQLException {
        String query = "select * from accounts where acc_no=" + acc_no + ";\n";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.getInt("acc_amount"));
            int curr = resultSet.getInt("acc_amount");
            if (curr < amount) {
                return false;
            } else {
                String query2 = "update accounts set acc_amount=" + (curr - amount) + " where acc_no =" + acc_no + ";\n";
                System.out.println(query2);
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.execute();
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error!" + e);
            return false;
        } finally {
            preparedStatement.close();

        }
    }

    public boolean depositDone(int acc_no, int amount) throws SQLException {
        String query = "INSERT INTO `deposit` (daid,dAmount,dDate) VALUES (" + acc_no + "," + amount + ",datetime('now', 'localtime'));\n";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Error!" + e);
            return false;
        } finally {
            preparedStatement.close();

        }
    }

    public boolean checkDeposit(int acc_no, int amount) throws SQLException {
        String query = "select * from accounts where acc_no=" + acc_no + ";\n";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.getInt("acc_amount"));
            int curr = resultSet.getInt("acc_amount");
            if (curr < 0) {
                return false;
            } else {
                String query2 = "update accounts set acc_amount=" + (curr + amount) + " where acc_no =" + acc_no + ";\n";
                System.out.println(query2);
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.execute();
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error!" + e);
            return false;
        } finally {
            preparedStatement.close();

        }
    }

    public boolean transferDone(int curr_acc, int to_acc, int amount) throws SQLException {
        String query = "INSERT INTO `transfer` (tAccno,tToAccno,tAmount,tDate) VALUES (" + curr_acc + "," + to_acc + "," + amount + ",datetime('now', 'localtime'));\n";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Error!" + e);
            return false;
        } finally {
            preparedStatement.close();

        }
    }

    public boolean checkTransfer(int curr_acc, int to_acc, int amount) throws SQLException {
        String query_curr = "select * from accounts where acc_no=" + curr_acc + ";\n";
        String query_to = "select * from accounts where acc_no=" + to_acc + ";\n";
        System.out.println(query_curr);
        System.out.println(query_to);
        try {
            preparedStatement = connection.prepareStatement(query_curr);
            resultSet = preparedStatement.executeQuery();
            preparedStatement = connection.prepareStatement(query_to);
            resultSet1 = preparedStatement.executeQuery();
            System.out.println(resultSet.getInt("acc_amount"));
            int curr = resultSet.getInt("acc_amount");
            int to = resultSet1.getInt("acc_amount");
            if (curr < amount) {
                return false;
            } else {
                String query_curr2 = "update accounts set acc_amount=" + (curr - amount) + " where acc_no =" + curr_acc + ";\n";
                String query_to2 = "update accounts set acc_amount=" + (to + amount) + " where acc_no =" + to_acc + ";\n";

                System.out.println(query_curr2);
                preparedStatement = connection.prepareStatement(query_curr2);
                preparedStatement.execute();
                System.out.println(query_to2);
                preparedStatement = connection.prepareStatement(query_to2);
                preparedStatement.execute();
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error!" + e);
            return false;
        } finally {
            preparedStatement.close();

        }
    }

    int index;
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

    @FXML
    private JFXTreeTableView<Balance> BalanceTable;

    static ObservableList<Balance> data = FXCollections.observableArrayList();

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

                if (checkDeposit(Integer.parseInt(splited[0]), Integer.parseInt(Damount.getText()))) {
                    if (depositDone(Integer.parseInt(splited[0]), Integer.parseInt(Damount.getText()))) {
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
                if (checkWithdraw(Integer.parseInt(splited[0]), Integer.parseInt(Wamount.getText()))) {
                    if (withdrawDone(Integer.parseInt(splited[0]), Integer.parseInt(Wamount.getText()))) {

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
                if (checkTransfer(Integer.parseInt(splited[0]), Integer.parseInt(transferAcc.getText()), Integer.parseInt(transferAmount.getText()))) {
                    if (transferDone(Integer.parseInt(splited[0]), Integer.parseInt(transferAcc.getText()), Integer.parseInt(transferAmount.getText()))) {

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

    public void getData() {
        String toAcc;
        String query = "select acc_no,acc_type,acc_amount from accounts where acc_id=" + acc_id + ";\n";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                data.add(new Balance(Integer.toString(resultSet.getInt("acc_no")),
                        resultSet.getString("acc_type"),
                        Integer.toString(resultSet.getInt("acc_amount"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(ActivityPageContentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        index=HomePageContentController.index;
        if(index==1 ){
            mainFundsTab.getSelectionModel().select(0);
        }else if(index==2){
            mainFundsTab.getSelectionModel().select(1);
        }else if(index==3){
            mainFundsTab.getSelectionModel().select(2);
        }else if(index==4){
            mainFundsTab.getSelectionModel().select(3);
        }
        acc_id = LoginModel.uid;
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
            acc_no = getAccounts();
            ObservableList<String> acc_no_list = FXCollections.observableArrayList(acc_no);
            System.out.println(acc_no);
            Waccnotype.setItems(acc_no_list);
            Daccnotype.setItems(acc_no_list);
            currentAcc.setItems(acc_no_list);
        } catch (SQLException ex) {
            Logger.getLogger(FundsPageContentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        BalanceTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                BalanceTable.setRoot(null);
                data.clear();
                data.removeAll(data);
                JFXTreeTableColumn<Balance, String> AccNo = new JFXTreeTableColumn<>("Account Number");
                AccNo.setPrefWidth(150);
                AccNo.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Balance, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Balance, String> param) {
                        return param.getValue().getValue().acc_no;
                    }
                });
                JFXTreeTableColumn<Balance, String> AccType = new JFXTreeTableColumn<>("Account Type");
                AccType.setPrefWidth(150);
                AccType.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Balance, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Balance, String> param) {
                        return param.getValue().getValue().acc_type;
                    }
                });
                JFXTreeTableColumn<Balance, String> AccBal = new JFXTreeTableColumn<>("Account Balance");
                AccBal.setPrefWidth(150);
                AccBal.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Balance, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Balance, String> param) {
                        return param.getValue().getValue().acc_amount;
                    }
                });
                //        users.add(new User("1kj2", "1kj2", "1kjk2"));
                getData();
                final TreeItem<Balance> root = new RecursiveTreeItem<Balance>(data, RecursiveTreeObject::getChildren);
                BalanceTable.getColumns().setAll(AccNo, AccType, AccBal);
                BalanceTable.setRoot(root);
                BalanceTable.setShowRoot(false);
            }
        });

    }

    class Balance extends RecursiveTreeObject<Balance> {

        StringProperty acc_no;
        StringProperty acc_type;
        StringProperty acc_amount;

        public Balance(String acc_no, String acc_type, String acc_amount) {
            this.acc_no = new SimpleStringProperty(acc_no);
            this.acc_type = new SimpleStringProperty(acc_type);
            this.acc_amount = new SimpleStringProperty(acc_amount);

        }
    }
}
