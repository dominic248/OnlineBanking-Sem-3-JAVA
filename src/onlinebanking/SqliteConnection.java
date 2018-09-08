/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.JDBC.*;

/**
 *
 * @author dms
 */
public class SqliteConnection {

    public static Connection Connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:banking.db");
            Statement stmt = conn.createStatement();
            String sql = "create table if not exists AccountInfo(\n"
                    + "	ID integer NOT NULL UNIQUE primary key,\n"
                    + "	Name text NOT NULL,\n"
                    + "	Usernames text NOT NULL UNIQUE,\n"
                    + "	Passwords text NOT NULL,\n"
                    + "	DOB text NOT NULL,\n"
                    + "	Age integer NOT NULL,\n"
                    + "	Address text NOT NULL,\n"
                    + "	Citys text NOT NULL,\n"
                    + "	States text NOT NULL,\n"
                    + "	Countrys text NOT NULL,\n"
                    + "	Pincode integer NOT NULL,\n"
                    + "	Phone text NOT NULL,\n"
                    + "	Email text NOT NULL\n"
                    + ");";
            stmt.execute(sql);

            String sql1 = "insert or ignore into AccountInfo(ID,Name,Usernames,Passwords,DOB,Age,Address,Citys,States,Countrys,Pincode,Phone,Email) VALUES (1,'Dominic','dms24','123','24/08/1999',19,'Nav','Thane','Maharashtra','India',401107,'+919594183245','dms24081999@gmail.com');";
            stmt.execute(sql1);

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
}
