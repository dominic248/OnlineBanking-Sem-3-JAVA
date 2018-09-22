/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.JDBC.*;

/**
 *
 * @author dms
 */
public class SqliteConnection {

    public static Connection connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:banking.db");

            return conn;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
            //123
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SqliteConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Connection createdb() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:banking.db");

            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `users` (\n"
                    + "	`username`	TEXT NOT NULL,\n"
                    + "	`password`	TEXT NOT NULL,\n"
                    + "	`id`	INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,\n"
                    + "	`address`	TEXT NOT NULL,\n"
                    + "	`email`	TEXT NOT NULL,\n"
                    + "	`mobile`	INTEGER NOT NULL\n"
                    + ");\n"
                    + "CREATE TABLE IF NOT EXISTS `accounts` (\n"
                    + "	`acc_name`	TEXT NOT NULL,\n"
                    + "	`acc_no`	INTEGER UNIQUE,\n"
                    + "	`acc_type`	TEXT NOT NULL,\n"
                    + "	`acc_details`	TEXT\n"
                    + ");";
            stmt.execute(sql);

            String sql1 = "INSERT INTO `users` (username,password,address,email,mobile) VALUES ('dms','123','asd','@gmail',768),\n"
                    + " ('dm','123','asd','@gmail',768);";
            stmt.execute(sql1);
            return conn;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SqliteConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
