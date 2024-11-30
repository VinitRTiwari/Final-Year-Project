/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package highjosh;
//import com.mysql.cj.protocol.Resultset;
import java.sql.*;
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
    public boolean AddBook(int bid,String title,String author,String isbn, String publisher, String price, String status){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("1");
            Connection conn = DriverManager.getConnection(db,user,pass);
            System.out.println("2");
            String s = "insert into bookinfo(_id, _title,_author,_isbn,_publisher,_price,_status,_active) values('"+ bid +"','"+ title +"','"+ author +"','"+ isbn +"','"+ publisher +"','"+ price +"','"+ status +"',1);";
            System.out.println("3");
            Statement stmt = conn.createStatement();
            System.out.println("4");
            stmt.executeUpdate(s);
            System.out.println("5");
            stmt.close();
            conn.close();
            return true;        
    }
    catch(Exception e){
        return false;
    }
    }
    public String deletebook(String test){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db,user,pass);
            String s = "select _id,_title,_author from bookinfo";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(s);
            String show = " ";
            while(rs.next()){
                String temp1 = rs.getString(1);
                String temp2 = rs.getString(2);
                String temp3 = rs.getString(3);
                if(temp1.equals(test)){
                    show = temp1 + "\n" + temp2 + "\n"+ temp3;
                    break;
                }
            }
            return show;
        }
        catch(Exception e){
            return "";
        }
    }
}
