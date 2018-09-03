/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class FXMLDocumentController {

    @FXML
    private Pane rootPane1;

    @FXML
    private JFXButton login_btn1;

    @FXML
    private JFXPasswordField pass1;

    @FXML
    private Label welcome1;

    @FXML
    private JFXTextField acc_no1;


    @FXML
    void handleSubmitButtonAction(ActionEvent event) {
        if(acc_no1.getText().isEmpty() && pass1.getText().isEmpty()){
            System.out.println("Both are Empty");
            return;
        }else if(pass1.getText().isEmpty()){
            System.out.println("Password?");
            return;
        }else if(acc_no1.getText().isEmpty()){
            System.out.println("Account?");
            return;
        }else{
            System.out.println("Successful!");
        }
    }

}