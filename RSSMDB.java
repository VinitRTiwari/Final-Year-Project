
import java.sql.*;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author gupta
 */
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
                System.out.println(username+" "+password);
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
}
