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
public class LoginModel {
    Connection connection;
    public LoginModel(){
        connection=SqliteConnection.Connector();
        if(connection==null) {System.exit(1);}
    }
    public boolean isDbConnected(){
        try{
            return !connection.isClosed();
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean isLogin(String acc_no, String pass) throws SQLException{
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query = "select * from users where Name=? and Password =?";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,acc_no);
            preparedStatement.setString(2,pass);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            return false;
        }finally{
            preparedStatement.close();
            resultSet.close();
        }
    }
}
