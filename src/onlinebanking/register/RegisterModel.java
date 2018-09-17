/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.register;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import database.SqliteConnection;

/**
 *
 * @author dms
 */
public class RegisterModel {

    Connection connection;

    public RegisterModel(){
        connection = SqliteConnection.connector();
        if (connection == null) {
            System.exit(1);
        }
    }
    public boolean ifUsernameExists(String acc_no) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from users where username=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, acc_no); //setting 1st ? of SQL from acc_no
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            if (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
        
    }
    public void isRegister(String username, String password,String address,String email,int mobile) throws SQLException {
        PreparedStatement preparedStatement = null;
        //ResultSet resultSet = null;
        String query = "INSERT INTO `users` (username,password,address,email,mobile) VALUES ('"+username+"','"+password+"','"+address+"','"+email+"',"+mobile+");\n";
        try {
            preparedStatement = connection.prepareStatement(query);

            System.out.println(query);
            System.out.println("Hey"+preparedStatement.execute());

        } catch (SQLException e) {
            System.out.println("Error!");
        } 
    }
    
}
