/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.sql.*;

/**
 *
 * @author user
 */
public class BatchProcess {
    
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/javaprc", "root", "vivek17");  
            con.setAutoCommit(false);  

            Statement stmt=con.createStatement();  
            stmt.addBatch("insert into student values(5,'Shubham','Java', 'A')");  
            stmt.addBatch("insert into student values(6,'Shashank','Java', 'B')");  

            stmt.executeBatch();//executing the batch  

            con.commit();   
        }  catch(Exception e){
            
        }
    }
    
}
