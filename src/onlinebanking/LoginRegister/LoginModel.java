/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.LoginRegister;

import java.sql.*;
import onlinebanking.database.SqliteConnection;

/**
 *
 * @author dms
 */
public class LoginModel {

    Connection connection;

    public LoginModel() {
        connection = SqliteConnection.connector();
        if (connection == null) {
            System.exit(1);
        }
    }

    public boolean isLogin(String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from users where username=? and password =?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username); //setting 1st ? of SQL from acc_no
            preparedStatement.setString(2, password);   //setting 2nd ? of SQL from pass
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
}
