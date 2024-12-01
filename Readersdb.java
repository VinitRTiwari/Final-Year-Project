package highjosh;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//import com.mysql.cj.protocol.Resultset;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author vinit
 */
public class Readersdb {
    String db = "jdbc:mysql://localhost:3306/READERS_HAVEN";
    String user = "root";
    String pass = "root";
    public boolean checkdata(String username,String password){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db,user,pass);
            String s = "select _username,_password from tbllog";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(s);
            while(rs.next()){
                String temp1 = rs.getString(1);
                String temp2 = rs.getString(2);
                if(temp1.equals(username) && temp2.equals(password)){
                    return true;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
            return false;
        }
        catch(Exception e){
            return false;
        }
    }
    public boolean Add(String query){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db,user,pass);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
            return true;        
    }
    catch(Exception e){
        return false;
    }
    }
    public boolean delete(String query){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db,user,pass);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
            return true;  
        }
        catch(Exception e){
            return false;
        }
    }

    public String test(String query,String check){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db,user,pass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String show = " ";
            while(rs.next()){
                String temp1 = rs.getString(1);
                String temp2 = rs.getString(2);
                if(temp1.equals(check)){
                    show = temp1 + "\n" + temp2;
                    break;
                }
            }
            return show;
        }
        catch(Exception e){
            return "";
        }
    }
    
    public boolean editUser(String query){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db,user,pass);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public int info(int amt){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db,user,pass);
            Statement stmt = conn.createStatement();
            String query = "select _totalFine from user;";
            ResultSet rs = stmt.executeQuery(query);
            int total = 0;
            while(rs.next()){
                int temp1 = rs.getInt(1);
                total = temp1 - amt;
                break;
            }
            stmt.close();
            conn.close();
            return total;
        }
        catch(Exception e){
            return 0;
        }
    }
    public int info2(int amt){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db,user,pass);
            Statement stmt = conn.createStatement();
            String query = "select _totalFine from user;";
            ResultSet rs = stmt.executeQuery(query);
            int total = 0;
            while(rs.next()){
                int temp1 = rs.getInt(1);
                total = temp1 + amt;
                break;
            }
            stmt.close();
            conn.close();
            return total;
        }
        catch(Exception e){
            return 0;
        }
    }
    public void overdue(){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();
            String currentDateString = sdf.format(currentDate);
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db,user,pass);
            Statement stmt = conn.createStatement();
            String query = "select _tid,_due from transaction where _status = 'issued' ;";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                int ttid = rs.getInt(1);
                String storedDateString = rs.getString(2);
                Date storedDate = sdf.parse(storedDateString);
                if (currentDate.compareTo(storedDate) > 0) {
                   String query1 = "update transaction set _status= 'overdue' where _tid= '"+ ttid +"' and _status = 'issued' ; " ;
                   boolean t = editUser(query1);
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
