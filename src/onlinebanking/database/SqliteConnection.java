/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking.database;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dms
 */
public class SqliteConnection {

    static Connection conn = null;
    static PreparedStatement preparedStatement = null;

    public static Connection connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:banking.db");

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

    public static void createdb() {

        try {
            if (conn.isClosed()) {
                conn = SqliteConnection.connector();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqliteConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String users = "CREATE TABLE IF NOT EXISTS `users` (\n"
                    + "	`uid`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n"
                    + "	`name`	TEXT NOT NULL,\n"
                    + "	`username`	TEXT NOT NULL,\n"
                    + "	`password`	TEXT NOT NULL,\n"
                    + "	`address`	TEXT NOT NULL,\n"
                    + "	`email`	TEXT NOT NULL,\n"
                    + "	`mobile`	TEXT NOT NULL,\n"
                    + "	`uDate`	TEXT NOT NULL,\n"
                    + "	`uImage`	blob\n"
                    + ");";
            preparedStatement = conn.prepareStatement(users);
            preparedStatement.execute();

            String accounts = "CREATE TABLE IF NOT EXISTS `accounts` (\n"
                    + "	`acc_no`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n"
                    + "	`acc_id`	INTEGER NOT NULL,\n"
                    + "	`acc_type`	TEXT NOT NULL,\n"
                    + "	`acc_details`	TEXT,\n"
                    + "	`acc_amount`	INTEGER NOT NULL,\n"
                    + "	`acc_date`	TEXT NOT NULL,\n"
                    + "	FOREIGN KEY(`acc_id`) REFERENCES `users`(`uid`) on delete cascade on update cascade\n"
                    + ");";
            preparedStatement = conn.prepareStatement(accounts);
            preparedStatement.execute();

            String withdraw = "CREATE TABLE IF NOT EXISTS `withdraw` (\n"
                    + "	`waid`	INTEGER NOT NULL,\n"
                    + "	`wAmount`	INTEGER NOT NULL,\n"
                    + "	`wDate`	TEXT NOT NULL,\n"
                    + "	`op`	text NOT NULL DEFAULT 'Withdrawed',\n"
                    + "	FOREIGN KEY(`waid`) REFERENCES `accounts`(`acc_no`) on delete cascade on update cascade\n"
                    + ");";
            preparedStatement = conn.prepareStatement(withdraw);
            preparedStatement.execute();

            String deposit = "CREATE TABLE IF NOT EXISTS `deposit` (\n"
                    + "	`daid`	INTEGER NOT NULL,\n"
                    + "	`dAmount`	INTEGER NOT NULL,\n"
                    + "	`dDate`	TEXT NOT NULL,\n"
                    + "	`op`	text NOT NULL DEFAULT 'Deposited',\n"
                    + "	FOREIGN KEY(`daid`) REFERENCES `accounts`(`acc_no`) on delete cascade on update cascade\n"
                    + ");";
            preparedStatement = conn.prepareStatement(deposit);
            preparedStatement.execute();

            String transfer = "CREATE TABLE IF NOT EXISTS `transfer` (\n"
                    + "	`tAccno`	INTEGER NOT NULL,\n"
                    + "	`tToAccno`	INTEGER NOT NULL,\n"
                    + "	`tAmount`	INTEGER NOT NULL,\n"
                    + "	`op`	text NOT NULL DEFAULT 'Transfered',\n"
                    + "	`tDate`	TEXT NOT NULL,\n"
                    + "	FOREIGN KEY(`tAccno`) REFERENCES `accounts`(`acc_no`) on delete cascade on update cascade\n"
                    + ");";
            preparedStatement = conn.prepareStatement(transfer);
            preparedStatement.execute();

            String received = "CREATE TABLE IF NOT EXISTS `received` (\n"
                    + "	`rAccno`	INTEGER NOT NULL,\n"
                    + "	`rFromAccno`	INTEGER NOT NULL,\n"
                    + "	`rAmount`	INTEGER NOT NULL,\n"
                    + "	`op`	text NOT NULL DEFAULT 'Received',\n"
                    + "	`rDate`	TEXT NOT NULL,\n"
                    + "	FOREIGN KEY(`rAccno`) REFERENCES `accounts`(`acc_no`) on delete cascade on update cascade\n"
                    + ");";
            preparedStatement = conn.prepareStatement(received);
            preparedStatement.execute();

            String activity = "CREATE TABLE IF NOT EXISTS `activity` (\n"
                    + "	`aid`	INTEGER NOT NULL,\n"
                    + "	`aDate`	TEXT NOT NULL,\n"
                    + "	`aType`	TEXT NOT NULL,\n"
                    + "	FOREIGN KEY(`aid`) REFERENCES `users`(`uid`) on delete cascade on update cascade\n"
                    + ");";
            preparedStatement = conn.prepareStatement(activity);
            preparedStatement.execute();

            String sql1 = "INSERT INTO `users` (uid,name,username,password,address,email,mobile,uDate,uImage) VALUES \n"
                    + " (1,'Dominic','dms24081999','123','Thane','dms24081999@gmail.com','2345346456',datetime('now', 'localtime'),NULL);";
            preparedStatement = conn.prepareStatement(sql1);
            preparedStatement.execute();
            conn.commit();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SqliteConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
