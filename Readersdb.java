package highjosh;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//import com.mysql.cj.protocol.Resultset;

import java.io.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author vinit
 */
public class Readersdb {

    String db = "jdbc:mysql://localhost:3306/READERS_HAVEN";
    String user = "root";
    String pass = "root";

    public boolean checkdata(String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pass);
            String s = "select _username,_password from tbllog";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                String temp1 = rs.getString(1);
                String temp2 = rs.getString(2);
                if (temp1.equals(username) && temp2.equals(password)) {
                    return true;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean Add(String query) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pass);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(String query) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pass);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String test(String query, String check) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String show = " ";
            while (rs.next()) {
                String temp1 = rs.getString(1);
                String temp2 = rs.getString(2);
                if (temp1.equals(check)) {
                    show = temp1 + "\n" + temp2;
                    break;
                }
            }
            return show;
        } catch (Exception e) {
            return "";
        }
    }

    public boolean editUser(String query) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pass);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int info(int amt,int uid) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pass);
            Statement stmt = conn.createStatement();
            String query = "select _totalFine from user where _userid= '"+ uid +"' ; " ;
            ResultSet rs = stmt.executeQuery(query);
            int total = 0;
            while (rs.next()) {
                int temp1 = rs.getInt(1);
                total = temp1 - amt;
                break;
            }
            stmt.close();
            conn.close();
            return total;
        } catch (Exception e) {
            return 0;
        }
    }

    public int info2(int amt, int uid) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pass);
            Statement stmt = conn.createStatement();
            String query = "select _totalFine from user where _userid= '"+ uid +"' ; " ;
            ResultSet rs = stmt.executeQuery(query);
            int total = 0;
            while (rs.next()) {
                int temp1 = rs.getInt(1);
                total = temp1 + amt;
                break;
            }
            stmt.close();
            conn.close();
            return total;
        } catch (Exception e) {
            return 0;
        }
    }

    public void overdue() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();
            String currentDateString = sdf.format(currentDate);
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pass);
            Statement stmt = conn.createStatement();
            String query = "select _tid,_due from transaction where _status = 'issued' ;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int ttid = rs.getInt(1);
                String storedDateString = rs.getString(2);
                Date storedDate = sdf.parse(storedDateString);
                if (currentDate.compareTo(storedDate) > 0) {
                    String query1 = "update transaction set _status= 'overdue' where _tid= '" + ttid + "' and _status = 'issued' ; ";
                    boolean t = editUser(query1);
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertCSV(String filePath, String query) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pass);
            BufferedReader bReader = new BufferedReader(new FileReader(filePath));
            String line = "";
            while ((line = bReader.readLine()) != null) {

                try {
                    if(line != null){
                        String [] array = line.split(",+");
                        for(String result:array){
                            System.out.println(result);
                            PreparedStatement ps = conn.prepareStatement(query);
                            ps.setString(1, array[0]);
                            ps.setString(2, array[1]);
                            ps.setString(3, array[2]);
                            ps.setString(4, array[3]);
                            ps.setString(5, array[4]);
                            ps.setString(6, array[5]);
                            ps.setString(7, array[6]);
                            ps.setString(8, array[7]);
                            ps.executeUpdate();
                            ps.close();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bReader == null) {
                        bReader.close();
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Data inserted succesfully...! ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public String forget(String query){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String show = " ";
            while (rs.next()) {
                String temp1 = rs.getString(1);
                
                show=temp1;
                
                return show;
                
            }
            return show;
        } catch (Exception e) {
            return "";
        }
    }
    public void insertExcel(String filePath){
         try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pass);
            String query = "insert into bookinfo(_id,_title,_author,_isbn,_publisher,_price,_status,_active) values(?,?,?,?,?,?,?,?)";
            
         try{
            FileInputStream inputStream = new FileInputStream(new File(filePath));
            Workbook workbook = null;
            
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                throw new IllegalArgumentException("The specified file is not Excel format.");
            }
            
            Sheet sheet = workbook.getSheetAt(0);
            
            for (Row row : sheet) {
             
                if (row.getRowNum() == 0) {
                    // Skip header row
                    continue;
                }
                int id1 = (int) row.getCell(0).getNumericCellValue();
                
                String title1 = row.getCell(1).getStringCellValue();
                
                String author1 = row.getCell(2).getStringCellValue();
                
                String isbn1 = row.getCell(3).getStringCellValue();
                
                String publisher1 = row.getCell(4).getStringCellValue();
                
                int price1 = (int) row.getCell(5).getNumericCellValue();
                
                String status1 = row.getCell(6).getStringCellValue();
                
                int active1 = (int) row.getCell(7).getNumericCellValue();
                // Insert into database
                
                
                try (PreparedStatement statement = conn.prepareStatement(query)) {
                    
                    statement.setInt(1, id1);
                   
                    statement.setString(2, title1);
                    
                    statement.setString(3, author1);
                    
                    statement.setString(4, isbn1);
                    
                    statement.setString(5, publisher1);
                    
                    statement.setInt(6, price1);
                    statement.setString(7,status1);
                    
                    statement.setInt(8, active1);
                    statement.executeUpdate();
                    
                }
                
            }
            
            workbook.close();
            JOptionPane.showMessageDialog(null, "Data has been inserted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     catch (Exception e) {
            e.printStackTrace();    
    }
    }
    public void insertExcelStudent(String filePath){
         try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pass);
            String query = "insert into user(_userid,_name,_email,_mobile,_address,_father,_department,_role,_active) values(?,?,?,?,?,?,?,'student',1)";
            
         try{
            FileInputStream inputStream = new FileInputStream(new File(filePath));
            Workbook workbook = null;
            
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                throw new IllegalArgumentException("The specified file is not Excel format.");
            }
            
            Sheet sheet = workbook.getSheetAt(0);
            
            for (Row row : sheet) {
             
                if (row.getRowNum() == 0) {
                    // Skip header row
                    continue;
                }
                int userid1 = (int) row.getCell(0).getNumericCellValue();                
                String name1 = row.getCell(1).getStringCellValue();                
                String email1 = row.getCell(2).getStringCellValue();                
                int mobile1 = (int) row.getCell(3).getNumericCellValue();                
                String address1 = row.getCell(4).getStringCellValue();                
                String father1 = row.getCell(5).getStringCellValue();
                String course1 = row.getCell(6).getStringCellValue();              
                try (PreparedStatement statement = conn.prepareStatement(query)) {
                    statement.setInt(1, userid1);                   
                    statement.setString(2, name1);                    
                    statement.setString(3, email1);                    
                    statement.setInt(4, mobile1);                    
                    statement.setString(5, address1);                    
                    statement.setString(6, father1);                                      
                    statement.setString(7, course1);
                    statement.executeUpdate();                    
                }                
            }            
            workbook.close();
            JOptionPane.showMessageDialog(null, "Data has been inserted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     catch (Exception e) {
            e.printStackTrace();    
    }
    }
    public void insertExcelFaculty(String filePath){
         try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pass);
            String query = "insert into user(_userid,_name,_email,_mobile,_department,_role,_active) values(?,?,?,?,?,'faculty',1)";
            
         try{
            FileInputStream inputStream = new FileInputStream(new File(filePath));
            Workbook workbook = null;
            
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                throw new IllegalArgumentException("The specified file is not Excel format.");
            }
            
            Sheet sheet = workbook.getSheetAt(0);
            
            for (Row row : sheet) {
             
                if (row.getRowNum() == 0) {
                    // Skip header row
                    continue;
                }
                int userid1 = (int) row.getCell(0).getNumericCellValue();                
                String name1 = row.getCell(1).getStringCellValue();                
                String email1 = row.getCell(2).getStringCellValue();                
                int mobile1 = (int) row.getCell(3).getNumericCellValue();       
                String dept1 = row.getCell(4).getStringCellValue();              
                try (PreparedStatement statement = conn.prepareStatement(query)) {
                    statement.setInt(1, userid1);                   
                    statement.setString(2, name1);                    
                    statement.setString(3, email1);                    
                    statement.setInt(4, mobile1);                                           
                    statement.setString(5, dept1);
                    statement.executeUpdate();                    
                }                
            }            
            workbook.close();
            JOptionPane.showMessageDialog(null, "Data has been inserted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     catch (Exception e) {
            e.printStackTrace();    
    }
    }
    public int calamount(String query){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int amount = 0;
            while (rs.next()) {
                amount = rs.getInt(1);
            }
            rs.close();
            stmt.close();
            conn.close();
            return amount;
        } catch (Exception e) {
            return 0;
        }
    }
}
