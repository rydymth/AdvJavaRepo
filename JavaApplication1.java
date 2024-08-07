/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter a id: ");
        int id = sc.nextInt();
        
        System.out.print("Enter a name: ");
        String name = sc.next();
        
        System.out.print("Enter a subject: ");
        String subject = sc.next();
        
        System.out.print("Enter a Grade: ");
        String grade = sc.next();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/javaprc", "root", "vivek17");
            
            if (con != null) {
                System.out.println("Connected");
            }
            
            String sql = "INSERT INTO student (id, name, subject, grade) VALUES (?, ?, ?, ?)";
 
            PreparedStatement statement = con.prepareStatement(sql);
            
            
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, subject);
            statement.setString(4, grade);
            
            int rowsInserted = statement.executeUpdate();
            
            System.out.println(rowsInserted + " rows inserted");
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
