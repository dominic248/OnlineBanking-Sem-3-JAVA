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
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import onlinebanking.LoginRegister.LoginModel;
import onlinebanking.database.SqliteConnection;

/**
 *
 * @author dms
 */
public class AccountsPageContentController implements Initializable {

    static ObservableList<AccInfo> data = FXCollections.observableArrayList();
    Connection connection;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    static ResultSet resultSet1 = null;
    public static int acc_id;

    public AccountsPageContentController() {
        connection = SqliteConnection.connector();
        if (connection == null) {
            System.exit(1);
        }
    }

    Random random = new Random();
    ObservableList<String> Macc_type = FXCollections.observableArrayList("Student", "Savings", "Current");

    public boolean accTypeExists(String acc_type) throws SQLException {
        String query = "SELECT * FROM accounts WHERE acc_id=" + acc_id + " and acc_type='" + acc_type + "';";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return true;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public boolean isCreateAccount(int acc_no, String acc_type, String acc_details, int acc_amount) throws SQLException {
        String query = "INSERT INTO `accounts` (acc_no,acc_id,acc_type,acc_details,acc_amount,acc_date) VALUES (" + acc_no + "," + acc_id + ",'" + acc_type + "','" + acc_details + "'," + acc_amount + ",datetime('now', 'localtime'));\n";
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

    @FXML
    private JFXTreeTableView<AccInfo> AccInfoTable;

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

        if (caAcc_no.getText().isEmpty() || caAcc_type.getValue() == null || caAcc_desc.getText().isEmpty()) {
            System.out.println("?");
        } else {
            if (!accTypeExists(caAcc_type.getValue())) {
                iscaAcc_type.setText("");
                if (isCreateAccount(Integer.parseInt(caAcc_no.getText()), caAcc_type.getValue(), caAcc_desc.getText(), Integer.parseInt(caAccAmount.getText()))) {
                    System.out.println("Done");
                    mainAccountsTab.getSelectionModel().select(0);
                }
            } else {
                iscaAcc_type.setText("Account Type Exists");
            }
        }
    }

    public void getData() {
        String query = "select * from accounts where acc_id=" + acc_id + ";\n";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                data.add(new AccInfo(Integer.toString(resultSet.getInt("acc_no")),
                        resultSet.getString("acc_type"),
                        Integer.toString(resultSet.getInt("acc_amount")),
                        resultSet.getString("acc_details"),
                        resultSet.getString("acc_date")
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
    
    public void loadAccInfo(){
        AccInfoTable.setRoot(null);
                data.clear();
                data.removeAll(data);

                JFXTreeTableColumn<AccInfo, String> AccNo = new JFXTreeTableColumn<>("Account Number");
                AccNo.setPrefWidth(150);
                AccNo.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AccInfo, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AccInfo, String> param) {
                        return param.getValue().getValue().acc_no;
                    }
                });
                JFXTreeTableColumn<AccInfo, String> AccType = new JFXTreeTableColumn<>("Account Type");
                AccType.setPrefWidth(150);
                AccType.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AccInfo, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AccInfo, String> param) {
                        return param.getValue().getValue().acc_type;
                    }
                });
                JFXTreeTableColumn<AccInfo, String> AccAmount = new JFXTreeTableColumn<>("Amount");
                AccAmount.setPrefWidth(150);
                AccAmount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AccInfo, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AccInfo, String> param) {
                        return param.getValue().getValue().acc_amount;
                    }
                });
                JFXTreeTableColumn<AccInfo, String> AccDetails = new JFXTreeTableColumn<>("Account Details");
                AccDetails.setPrefWidth(150);
                AccDetails.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AccInfo, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AccInfo, String> param) {
                        return param.getValue().getValue().acc_details;
                    }
                });
                JFXTreeTableColumn<AccInfo, String> AccDate = new JFXTreeTableColumn<>("Date Created");
                AccDate.setPrefWidth(150);
                AccDate.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AccInfo, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AccInfo, String> param) {
                        return param.getValue().getValue().acc_date;
                    }
                });
                getData();
                final TreeItem<AccInfo> root = new RecursiveTreeItem<AccInfo>(data, RecursiveTreeObject::getChildren);
                AccInfoTable.getColumns().setAll(AccNo, AccType, AccAmount, AccDetails, AccDate);
                AccInfoTable.setRoot(root);
                AccInfoTable.setShowRoot(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        acc_id = LoginModel.uid;
        mainAccountsTab.widthProperty().addListener((observable, oldValue, newValue) -> {
            mainAccountsTab.setTabMinWidth((mainAccountsTab.getWidth() - 10) / 2);
            mainAccountsTab.setTabMaxWidth((mainAccountsTab.getWidth() - 10) / 2);

        });
        CreateAccountTab.setOnSelectionChanged((Event e) -> {
            caAcc_no.setText(String.valueOf(random.nextInt(10000) + 100));
        });
        caAcc_type.setItems(Macc_type);
        loadAccInfo();
        AccountDetailsTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                loadAccInfo();
            }
        });
    }

    class AccInfo extends RecursiveTreeObject<AccInfo> {

        StringProperty acc_no;
        StringProperty acc_type;
        StringProperty acc_amount;
        StringProperty acc_details;
        StringProperty acc_date;

        public AccInfo(String acc_no, String acc_type, String acc_amount, String acc_details, String acc_date) {
            this.acc_no = new SimpleStringProperty(acc_no);
            this.acc_type = new SimpleStringProperty(acc_type);
            this.acc_amount = new SimpleStringProperty(acc_amount);
            this.acc_details = new SimpleStringProperty(acc_details);
            this.acc_date = new SimpleStringProperty(acc_date);

        }
    }

}
