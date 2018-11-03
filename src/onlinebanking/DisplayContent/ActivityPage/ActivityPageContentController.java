/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.DisplayContent.ActivityPage;

import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;
import onlinebanking.DisplayContent.HomePage.HomePageContentController;
import onlinebanking.LoginRegister.LoginModel;
import onlinebanking.OnlineBanking;
import onlinebanking.database.SqliteConnection;

/**
 *
 * @author dms
 */
public class ActivityPageContentController implements Initializable {

    LoginModel l = new LoginModel();
    static ObservableList<Transactions> data = FXCollections.observableArrayList();
    static ObservableList<Activity> adata= FXCollections.observableArrayList();
    static Connection connection=OnlineBanking.connection;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    static ResultSet resultSet1 = null;
    public static int acc_id;



    int index;
    @FXML
    private Tab BalanceTab;

    @FXML
    private JFXTabPane mainActivityTab;

    @FXML
    private Tab ActivityTab, TransactionsTab;

    @FXML
    private FlowPane TransFlow;

    @FXML
    private JFXTreeTableView<Transactions> TransTable;
    
    @FXML
    private JFXTreeTableView<Activity> ActivityTable;

    public void getData() {
        try {
            if (connection.isClosed()) {
                connection = SqliteConnection.connector();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityPageContentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String toAcc;
        String query = "select daid as CurrentAccount, null as ToAccount, damount as Amount, dDate as Date, op as Op from deposit where daid in (select acc_no from accounts where acc_id=" + acc_id + ")\n"
                + "UNION\n"
                + "select waid as CurrentAccount, null as ToAccount, wamount as Amount, wDate as Date, op as Op from withdraw where waid in (select acc_no from accounts where acc_id=" + acc_id + ")\n"
                + "UNION\n"
                + "select tAccno as CurrentAccount, tToAccno as ToAccount, tamount as Amount, tDate as Date, op as Op from transfer where tAccno in (select acc_no from accounts where acc_id=" + acc_id + ")\n"
                + "UNION\n"
                + "select rAccno as CurrentAccount, rFromAccno as ToAccount, ramount as Amount, rDate as Date, op as Op from received where rAccno in (select acc_no from accounts where acc_id=" + acc_id + ");";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("ToAccount") == 0) {
                    toAcc = "-";
                } else {
                    toAcc = Integer.toString(resultSet.getInt("ToAccount"));
                }

                data.add(new Transactions(Integer.toString(resultSet.getInt("CurrentAccount")),
                        toAcc,
                        Integer.toString(resultSet.getInt("Amount")),
                        resultSet.getString("Date"),
                        resultSet.getString("Op")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ActivityPageContentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void getActivityData() {
        try {
            if (connection.isClosed()) {
                connection = SqliteConnection.connector();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityPageContentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = "select aType,aDate from activity where aid="+acc_id+";";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                adata.add(new Activity(resultSet.getString("aType"),
                       resultSet.getString("aDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
                connection.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(ActivityPageContentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void loadTransactions(){
        TransTable.setRoot(null);
                data.clear();
                data.removeAll(data);
                JFXTreeTableColumn<Transactions, String> currAccCol = new JFXTreeTableColumn<>("Current Account");
                currAccCol.setPrefWidth(150);
                currAccCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Transactions, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Transactions, String> param) {
                        return param.getValue().getValue().currAcc;
                    }
                });
                JFXTreeTableColumn<Transactions, String> toAccCol = new JFXTreeTableColumn<>("To/From Account");
                toAccCol.setPrefWidth(150);
                toAccCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Transactions, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Transactions, String> param) {
                        return param.getValue().getValue().toAcc;
                    }
                });
                JFXTreeTableColumn<Transactions, String> amount = new JFXTreeTableColumn<>("Amount");
                amount.setPrefWidth(150);
                amount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Transactions, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Transactions, String> param) {
                        return param.getValue().getValue().Amount;
                    }
                });
                JFXTreeTableColumn<Transactions, String> date = new JFXTreeTableColumn<>("Date");
                date.setPrefWidth(150);
                date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Transactions, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Transactions, String> param) {
                        return param.getValue().getValue().Date;
                    }
                });
                JFXTreeTableColumn<Transactions, String> op = new JFXTreeTableColumn<>("Operation");
                op.setPrefWidth(150);
                op.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Transactions, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Transactions, String> param) {
                        return param.getValue().getValue().OP;
                    }
                });

//        users.add(new User("1kj2", "1kj2", "1kjk2"));
                getData();
                final TreeItem<Transactions> root = new RecursiveTreeItem<Transactions>(data, RecursiveTreeObject::getChildren);
                TransTable.getColumns().setAll(currAccCol, toAccCol, amount, date, op);
                TransTable.setRoot(root);
                TransTable.setShowRoot(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        index=HomePageContentController.index;
        connection = OnlineBanking.connection;
        
        if(index==5){
            mainActivityTab.getSelectionModel().select(0);
        }else if(index==6){
            mainActivityTab.getSelectionModel().select(1);
        }
        acc_id = LoginModel.uid;
        mainActivityTab.widthProperty().addListener((observable, oldValue, newValue)
                -> {
            mainActivityTab.setTabMinWidth((mainActivityTab.getWidth() - 10) / 2);
            mainActivityTab.setTabMaxWidth((mainActivityTab.getWidth() - 10) / 2);

        });
        loadTransactions();
        TransactionsTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                loadTransactions();
            }
        });
        
        ActivityTable.setRoot(null);
                adata.clear();
                adata.removeAll(data);
                JFXTreeTableColumn<Activity, String> AType = new JFXTreeTableColumn<>("Activity Type");
                AType.setPrefWidth(150);
                AType.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Activity, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Activity, String> param) {
                        return param.getValue().getValue().type;
                    }
                });
                JFXTreeTableColumn<Activity, String> ADate = new JFXTreeTableColumn<>("Date");
                ADate.setPrefWidth(150);
                ADate.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Activity, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Activity, String> param) {
                        return param.getValue().getValue().date;
                    }
                });
                
                getActivityData();
                final TreeItem<Activity> root = new RecursiveTreeItem<Activity>(adata, RecursiveTreeObject::getChildren);
                ActivityTable.getColumns().setAll(AType, ADate);
                ActivityTable.setRoot(root);
                ActivityTable.setShowRoot(false);

    }

    class Transactions extends RecursiveTreeObject<Transactions> {

        StringProperty currAcc;
        StringProperty toAcc;
        StringProperty Amount;
        StringProperty Date;
        StringProperty OP;

        public Transactions(String currAcc, String toAcc, String Amount, String Date, String OP) {
            this.currAcc = new SimpleStringProperty(currAcc);
            this.toAcc = new SimpleStringProperty(toAcc);
            this.Amount = new SimpleStringProperty(Amount);
            this.Date = new SimpleStringProperty(Date);
            this.OP = new SimpleStringProperty(OP);
        }
    }

    class Activity extends RecursiveTreeObject<Activity> {
        StringProperty type;
        StringProperty date;
        public Activity(String type, String date) {
            this.type = new SimpleStringProperty(type);
            this.date = new SimpleStringProperty(date);
        }
    }
}
