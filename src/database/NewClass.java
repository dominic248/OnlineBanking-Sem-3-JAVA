/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dms
 */
public final class NewClass {

    Connection connection;

    public NewClass() {
        createDBTables();

    }

    public void createDBTables() {
        try {
            Statement stmt = connection.createStatement();
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
        } catch (SQLException ex) {
            System.out.println("Error DB!");
        }

    }
}
