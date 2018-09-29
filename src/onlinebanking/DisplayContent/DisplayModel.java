/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.DisplayContent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static onlinebanking.DisplayContent.AccountsPage.AccountsPageContentModel.acc_id;
import onlinebanking.LoginRegister.LoginModel;
import onlinebanking.database.SqliteConnection;

/**
 *
 * @author dms
 */
public class DisplayModel {
    public static int uid = LoginModel.uid;
    Connection connection;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    public DisplayModel() {
        connection = SqliteConnection.connector();
        if (connection == null) {
            System.exit(1);
        }
    }
    public String getUsername() throws SQLException {
        String query = "SELECT * FROM users WHERE uid=" + uid + ";";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return resultSet.getString("name");
        } catch (SQLException e) {
            return null;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

}
