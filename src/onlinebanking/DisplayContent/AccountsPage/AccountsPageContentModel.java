/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.DisplayContent.AccountsPage;

import java.sql.*;
import onlinebanking.LoginRegister.LoginModel;

import onlinebanking.database.SqliteConnection;

/**
 *
 * @author dms
 */
public class AccountsPageContentModel {

    Connection connection;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    public static int acc_id = LoginModel.uid;

    public AccountsPageContentModel() {
        connection = SqliteConnection.connector();
        if (connection == null) {
            System.exit(1);
        }
    }

    public boolean accTypeExists(String acc_type) throws SQLException {
        String query = "SELECT * FROM accounts WHERE acc_id=" + acc_id + " and acc_type='" + acc_type + "';";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return true;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public boolean isCreateAccount(int acc_no, String acc_type, String acc_details,int acc_amount) throws SQLException {
        String query = "INSERT INTO `accounts` (acc_no,acc_id,acc_type,acc_details,acc_amount,acc_date) VALUES (" + acc_no + ","+acc_id+",'" + acc_type + "','" + acc_details + "',"+acc_amount+",datetime('now', 'localtime'));\n";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Error!" + e);
            return false;
        } finally {
            preparedStatement.close();

        }
    }
}
