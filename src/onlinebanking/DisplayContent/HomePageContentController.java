/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.DisplayContent;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

/**
 *
 * @author dms
 */
public class HomePageContentController {
        
    @FXML
    private JFXButton change;
    
    @FXML
    private Label welcome;
    
    public void onChange(){
        welcome.setText("Hello"); 
    }
}
