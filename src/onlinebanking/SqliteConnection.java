/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinebanking;
import java.sql.*;
/**
 *
 * @author dms
 */
public class SqliteConnection {
    public static Connection Connector(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn=DriverManager.getConnection("jdbc:sqlite:banking.db");
            return conn;
        }catch(Exception e){
            System.out.println(e);
            return null;
            //123
        }
    }
}
