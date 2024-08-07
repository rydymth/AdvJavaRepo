/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class CallableSql {
    
    public static void main(String[] args) {
        
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
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/javaprc", "root", "vivek17");
            
            CallableStatement cs = con.prepareCall("{call insertRecord(?, ?, ?, ?)}");
            
            cs.setInt(1, id);
            cs.setString(2, name);
            cs.setString(3, subject);
            cs.setString(4, grade);
            
            int rowsInserted = cs.executeUpdate();
            
            System.out.println(rowsInserted + " rows inserted");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
