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
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;

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

    @FXML
    private FlowPane TransFlow;

    @FXML
    private JFXTreeTableView<User> TransTable;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainActivityTab.widthProperty().addListener((observable, oldValue, newValue)
                -> {
            mainActivityTab.setTabMinWidth((mainActivityTab.getWidth() - 10) / 2);
            mainActivityTab.setTabMaxWidth((mainActivityTab.getWidth() - 10) / 2);

        });
        
        JFXTreeTableColumn<User,String> currAccCol=new JFXTreeTableColumn<>("Current Account");
        currAccCol.setPrefWidth(150);
        currAccCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User,String>,ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().currAcc;
            }
        });
        JFXTreeTableColumn<User,String> toAccCol=new JFXTreeTableColumn<>("To Account");
        toAccCol.setPrefWidth(150);
        toAccCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User,String>,ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().toAcc;
            }
        });
        JFXTreeTableColumn<User,String> Amount=new JFXTreeTableColumn<>("Amount");
        Amount.setPrefWidth(150);
        Amount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User,String>,ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().Amount;
            }
        });
        ObservableList<User> users=FXCollections.observableArrayList();
        users.add(new User("1kj2", "1kj2", "1kjk2"));
        final TreeItem<User> root=new RecursiveTreeItem<User>(users,RecursiveTreeObject::getChildren);
        TransTable.getColumns().setAll(currAccCol,toAccCol,Amount);
        TransTable.setRoot(root);
        TransTable.setShowRoot(false);
        
        
    }

    class User extends RecursiveTreeObject<User>{

        StringProperty currAcc;
        StringProperty toAcc;
        StringProperty Amount;
        StringProperty Date;
        StringProperty op;
        public User(String currAcc,String toAcc,String Amount) {
            this.currAcc=new SimpleStringProperty(currAcc);
            this.toAcc=new SimpleStringProperty(toAcc);
            this.Amount=new SimpleStringProperty(Amount);
        }
    }

}
