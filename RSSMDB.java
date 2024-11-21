<<<<<<< HEAD
=======

import java.sql.*;


>>>>>>> c5f009f0dcb23fb83f1d672f48aef7f8d1c20f35
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
<<<<<<< HEAD
package loginform;
import java.sql.*;
=======
>>>>>>> c5f009f0dcb23fb83f1d672f48aef7f8d1c20f35
/**
 *
 * @author vinit
 */
<<<<<<< HEAD
public class RssmDb {
        String url = "jdbc:mysql://localhost:3306/RSSMLIB";
        String user = "root";
        String pass = "root";
        //String name,String mobile,String email,String username,String password,int dob,int doj
        public boolean InsertData(String name,String mobile,String email,String username,String password,int dob,int doj,String profile){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Establish the connection
            Connection conn = DriverManager.getConnection(url, user, pass);

            // Create a statement
            Statement stmt = conn.createStatement();
            //(_id,_name,_mobile,_email,_username,_password,_dob,_Jdate,_profile,_isActive)
            String query = "update table Admin_Tbl set _isActive = 0 where _profile = '"+profile+"' ";
            stmt.executeUpdate(query);
            // Execute the insert query
            String sql = "insert into Admin_Tbl(_name,_mobile,_email,_username,_password,_dob,_Jdate,_profile,_isActive) values('"+name+"','"+mobile+"','"+email+"','"+username+"','"+password+"','"+dob+"','"+doj+"','"+profile+"',1);";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            return true;
        } catch (Exception e) {
            return false;
        }}
        public boolean checkdata(String username,String password,String profile){
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection(url,user,pass);
            
            Statement st=con.createStatement();
            
            String query="select _username,_password from Admin_Tbl where _isActive=1 and _profile='"+profile+"'";
            ResultSet rs=st.executeQuery(query);
            
            while(rs.next()){
                String userId=rs.getString(1);
                String passcode=rs.getString(2);
                if(passcode.equalsIgnoreCase(password) && userId.equalsIgnoreCase(username)){
                        return true;
                }
            }
            rs.close();
            st.close();
            con.close();
            return false;
        }
        catch(Exception e){
             return false;
        }
}
=======
public class RSSMDB {

    String db = "jdbc:mysql://localhost:3306/RSSMLIB";
    String user = "root";
    String pass = "root";

    public boolean checkdata(String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(db, user, pass);
            String s = "select _username,_password from Admin_Tbl where _isActive=1 and _profile='librarian'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(s);

            while (rs.next()) {
                String userId = rs.getString(1);
                String passcode = rs.getString(2);
                if(userId.equals(username) && passcode.equals(password)){
                    return true;
                }
                else{
                   return false;
                }
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            return false;
        }
        return false;
    }
>>>>>>> c5f009f0dcb23fb83f1d672f48aef7f8d1c20f35
}
