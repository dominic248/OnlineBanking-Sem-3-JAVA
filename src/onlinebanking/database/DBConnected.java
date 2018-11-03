/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.database;

import java.sql.Connection;
import java.sql.SQLException;
import onlinebanking.OnlineBanking;

/**
 *
 * @author dms
 */
public class DBConnected {

    static Connection connection=OnlineBanking.connection;

    public DBConnected() {
        if (connection == null) {
            connection = SqliteConnection.connector();
        }
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
