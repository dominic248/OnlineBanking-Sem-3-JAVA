/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author dms
 */
public class OnlineBanking extends Application {
    
    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage=primaryStage;
        mainWindow();
    }
    public void mainWindow() throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        AnchorPane pane=loader.load();
        Scene scene=new Scene(pane);
        scene.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Online Banking");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
