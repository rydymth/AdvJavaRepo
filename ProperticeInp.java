/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class ProperticeInp {
    public static void main(String[] args) {
        
        Properties properties = new Properties();
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter a id: ");
        int id = sc.nextInt();
        
        System.out.print("Enter a name: ");
        String name = sc.next();
        
        System.out.print("Enter a subject: ");
        String subject = sc.next();
        
        System.out.print("Enter a Grade: ");
        String grade = sc.next();
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            properties.load(new FileInputStream("D:\\javaProject\\JavaApplication1\\src\\javaapplication1\\connector.txt"));
            
            String url = properties.getProperty("url");
            System.out.println(url);
            String uname = properties.getProperty("username");
            System.out.println(uname);
            String pass = properties.getProperty("password");
            System.out.println(pass);
            
            Connection con = DriverManager.getConnection(url, uname, pass);
            
            String sql = "INSERT INTO student (id, name, subject, grade) VALUES (?, ?, ?, ?)";
 
            PreparedStatement statement = con.prepareStatement(sql);
            
            
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, subject);
            statement.setString(4, grade);
            
            int rowsInserted = statement.executeUpdate();
            
            System.out.println(rowsInserted + " rows inserted");
            
            
            
        }
        catch(Exception e) {
            
        }
    }
}
