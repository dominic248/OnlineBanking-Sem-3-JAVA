/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.LoginRegister;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import onlinebanking.OnlineBanking;
import onlinebanking.database.SqliteConnection;

/**
 *
 * @author dms
 */
public class LoginModel {
    static Connection connection=OnlineBanking.connection;
    public static int uid;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    
    
    public boolean isLogin(String username, String password) throws SQLException {
        if (connection.isClosed()) {
            connection = SqliteConnection.connector();
        }
        String query = "select * from users where username='"+username+"' and password ='"+password+"';\n";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            if (resultSet.next()) {
                System.out.println(resultSet.getInt("uid"));
                uid=resultSet.getInt("uid");
                System.out.println("User Id:"+uid);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            preparedStatement.close();
            resultSet.close();
            connection.close();
        }
    }
    
    public void setLoginTime() {
        try {
            if (connection.isClosed()) {
                connection = SqliteConnection.connector();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "INSERT INTO `activity` (aid,aDate,aType) VALUES ("+uid+",datetime('now', 'localtime'),'Login');\n";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            
        } catch (SQLException e) {
            System.out.println("Error in DateTime");
            e.printStackTrace();
        } finally {
            System.out.println("Executed!");
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public void setLogoutTime() {

        try {
            if (connection.isClosed()) {
                connection = SqliteConnection.connector();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = "INSERT INTO `activity` (aid,aDate,aType) VALUES ("+uid+",datetime('now', 'localtime'),'Logout');\n";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            
        } catch (SQLException e) {
            System.out.println("Error in DateTime");
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Executed!");
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
