/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.DisplayContent.FundsPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import onlinebanking.LoginRegister.LoginModel;
import onlinebanking.database.SqliteConnection;

/**
 *
 * @author dms
 */
public class FundsPageContentModel {

    Connection connection;
    public static int acc_id=LoginModel.uid;

    public FundsPageContentModel() {
        connection = SqliteConnection.connector();
        if (connection == null) {
            System.exit(1);
        }
    }

    public boolean isCreateAccount(int wAmount, int waid) throws SQLException {
        PreparedStatement preparedStatement = null;
     
        String query = "INSERT INTO `withdraw` (wAmount,waid) VALUES (" + wAmount + "," + waid + ");\n";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);

            System.out.println(query);
            System.out.println("Hey" + preparedStatement.execute());
            return true;

        } catch (SQLException e) {
            System.out.println("Error!" + e);
            return false;
        }
    }
    public boolean getAccounts() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from accounts where acc_id="+acc_id+";";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);

            System.out.println(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getInt("acc_no"));
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error!" + e);
            return false;
        }finally{
            preparedStatement.close();
            resultSet.close();
        }
    }
}
