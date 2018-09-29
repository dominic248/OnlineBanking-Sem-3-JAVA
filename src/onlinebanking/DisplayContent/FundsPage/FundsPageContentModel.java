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
import java.util.ArrayList;

import onlinebanking.LoginRegister.LoginModel;
import onlinebanking.database.SqliteConnection;

/**
 *
 * @author dms
 */
public class FundsPageContentModel {

    Connection connection;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    static ResultSet resultSet1 = null;
    public static int acc_id = LoginModel.uid;

    public FundsPageContentModel() {
        connection = SqliteConnection.connector();
        if (connection == null) {
            System.exit(1);
        }
    }

    public ArrayList<String> getAccounts() throws SQLException {
        ArrayList<String> acc_no = new ArrayList<String>();
        String query = "select * from accounts where acc_id=" + acc_id + ";";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);

            System.out.println(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("acc_no"));
                System.out.println(resultSet.getString("acc_type"));
                acc_no.add(String.valueOf(resultSet.getInt("acc_no")) + " (" + resultSet.getString("acc_type") + ")");
            }
            return acc_no;
        } catch (SQLException e) {
            System.out.println("Error!" + e);
            return null;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public boolean withdrawDone(int acc_no, int amount) throws SQLException {
        String query = "INSERT INTO `withdraw` (waid,wAmount,wDate) VALUES (" + acc_no + "," + amount + ",datetime('now', 'localtime'));\n";
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
    
        public boolean checkWithdraw(int acc_no,int amount) throws SQLException {
        String query = "select * from accounts where acc_no="+acc_no+";\n";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.getInt("acc_amount"));
            int curr=resultSet.getInt("acc_amount");
            if(curr<amount){
                return false;
            }else{
                String query2 = "update accounts set acc_amount="+(curr-amount)+" where acc_no ="+acc_no+";\n";
                System.out.println(query2);
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.execute();
                return true;
            }


        } catch (SQLException e) {
            System.out.println("Error!" + e);
            return false;
        } finally {
            preparedStatement.close();

        }
    }

    public boolean depositDone(int acc_no, int amount) throws SQLException {
        String query = "INSERT INTO `deposit` (daid,dAmount,dDate) VALUES (" + acc_no + "," + amount + ",datetime('now', 'localtime'));\n";
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

    public boolean checkDeposit(int acc_no,int amount) throws SQLException {
        String query = "select * from accounts where acc_no="+acc_no+";\n";
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.getInt("acc_amount"));
            int curr=resultSet.getInt("acc_amount");
            if(curr<0){
                return false;
            }else{
                String query2 = "update accounts set acc_amount="+(curr+amount)+" where acc_no ="+acc_no+";\n";
                System.out.println(query2);
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.execute();
                return true;
            }


        } catch (SQLException e) {
            System.out.println("Error!" + e);
            return false;
        } finally {
            preparedStatement.close();

        }
    }
    
    public boolean transferDone(int curr_acc, int to_acc,int amount) throws SQLException {
        String query = "INSERT INTO `transfer` (tAccno,tToAccno,tAmount,tDate) VALUES (" + curr_acc + "," + to_acc +","+ amount + ",datetime('now', 'localtime'));\n";
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
    
    public boolean checkTransfer(int curr_acc, int to_acc,int amount) throws SQLException {
        String query_curr = "select * from accounts where acc_no="+curr_acc+";\n";
        String query_to = "select * from accounts where acc_no="+to_acc+";\n";
        System.out.println(query_curr);
        System.out.println(query_to);
        try {
            preparedStatement = connection.prepareStatement(query_curr);
            resultSet = preparedStatement.executeQuery();
            preparedStatement = connection.prepareStatement(query_to);
            resultSet1 = preparedStatement.executeQuery();
            System.out.println(resultSet.getInt("acc_amount"));
            int curr=resultSet.getInt("acc_amount");
            int to=resultSet1.getInt("acc_amount");
            if(curr<amount){
                return false;
            }else{
                String query_curr2 = "update accounts set acc_amount="+(curr-amount)+" where acc_no ="+curr_acc+";\n";
                String query_to2 = "update accounts set acc_amount="+(to+amount)+" where acc_no ="+to_acc+";\n";
                
                System.out.println(query_curr2);
                preparedStatement = connection.prepareStatement(query_curr2);
                preparedStatement.execute();
                System.out.println(query_to2);
                preparedStatement = connection.prepareStatement(query_to2);
                preparedStatement.execute();
                return true;
            }


        } catch (SQLException e) {
            System.out.println("Error!" + e);
            return false;
        } finally {
            preparedStatement.close();

        }
    }
    
}
