/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.LoginRegister;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import onlinebanking.database.SqliteConnection;

/**
 *
 * @author dms
 */
public class RegisterModel {

    Connection connection;

    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;

    public RegisterModel() {
        connection = SqliteConnection.connector();
        if (connection == null) {
            System.exit(1);
        }
    }
    private FileInputStream imagefis;

    public boolean ifUsernameExists(String acc_no) throws SQLException {
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

    public boolean isRegister(String name, String username, String password, String address, String email, String mobile) throws SQLException, FileNotFoundException {
        
            if(LoginRegisterController.file==null)
                imagefis =null;
            else
                imagefis = new FileInputStream(LoginRegisterController.file);
            String query = "INSERT INTO `users` (name,username,password,address,email,mobile,uDate,uImage) VALUES ('" + name + "','" + username + "','" + password + "','" + address + "','" + email + "'," + mobile + ",datetime('now', 'localtime'),?);\n";
            try {
                preparedStatement = connection.prepareStatement(query);
                if(imagefis==null)
                    preparedStatement.setNull(1, java.sql.Types.BLOB);
                else 
                    preparedStatement.setBinaryStream(1, (InputStream) imagefis, (int) LoginRegisterController.file.length());
                System.out.println(query);
                preparedStatement.execute();
                
                return true;

            } catch (SQLException e) {
                System.out.println("Error!");
                return false;
            }
        
    }

}
