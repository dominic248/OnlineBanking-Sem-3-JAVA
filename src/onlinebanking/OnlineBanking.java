/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking;

import onlinebanking.database.SqliteConnection;
import java.io.IOException;
import java.sql.Connection;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle; //StageStyle.DECORATED
import onlinebanking.LoginRegister.LoginModel;

public class OnlineBanking extends Application {

    public static Stage stage;
    Connection connection;
    LoginModel l = new LoginModel();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        mainWindow();
    }

    public void stop() {
        if (LoginModel.uid != 0) {
            l.setLogoutTime();
            LoginModel.uid = 0;
        }
        System.out.println("Stop");
    }

    public void mainWindow() throws IOException {
        connection = SqliteConnection.createdb();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/onlinebanking/LoginRegister/LoginRegisterPage.fxml"));
        StackPane pane = loader.load();
        Scene scene = new Scene(pane);
        scene.getStylesheets().addAll(getClass().getResource("/onlinebanking/style.css").toExternalForm());

        stage.setTitle("Online Banking");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaximized(false);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //DBConnected dbConnected = new DBConnected();
        launch(args);
    }
}
