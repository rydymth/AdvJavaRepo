/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javamysqlconnection;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.io.FileInputStream;

/**
 *
 * @author rudy
 */
public class JavaMysqlConnection {

    static final String CONN_INFO = "/home/rudy/NetBeansProjects/javaMysqlConnection/src/javamysqlconnection/connectionInfo.properties";
    public static JdbcRowSet rowSet;

        public static Properties props(String fileName) throws FileNotFoundException, IOException
    {
       Properties retProps = new Properties();
       FileInputStream in = new FileInputStream(fileName);
       retProps.load(in);
       in.close();
       return retProps;
    }

    // Function to call all tables via row set
    public static void setConnection(Properties props) throws SQLException
    {
        rowSet = RowSetProvider.newFactory().createJdbcRowSet();
        rowSet.setUrl(props.getProperty("url"));
        rowSet.setUsername(props.getProperty("username"));
        rowSet.setPassword(props.getProperty("password"));
    }

    // Function to select database
    public static void selectDB(String dbName) throws SQLException
    {
        try
        {
            String sql = "USE " + dbName;
            rowSet.execute();
        }
        catch (SQLException e)
        {
            System.out.println("Hmmmm");
            System.out.println(e);
        }
        catch (NullPointerException e)
        {
            System.out.println("Its fine if its null");
        }
    }

    // Function to show all tables present in the database
    public static void showAllTables() throws SQLException
    {
        String sql = "SHOW TABLES";
        rowSet.setCommand(sql);
        rowSet.execute();
        System.out.println("Following are the tables present in the database msc. Select one:");
        while(rowSet.next())
        {
           System.out.println(rowSet.getString(1));
        }
    }

    public static void getTables(String tableName) throws SQLException
    {
        String sql = "SELECT * FROM " + tableName;
        rowSet.setCommand(sql);
        rowSet.execute();
        while(rowSet.next())
        {
            System.out.println("Company ID: " + rowSet.getInt(1));
            System.out.println("Company Name: " + rowSet.getString(2));
            System.out.println("Company Address: " + rowSet.getString(3));
            System.out.println("Company Email: " + rowSet.getString(4));
            System.out.println("Company Phone: " + rowSet.getString(5));
        }
    }
    
    public static int getTableLength(String tableName) throws SQLException
    {
        int len = 0;
        String sql = "SELECT COUNT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='" + tableName + "'";
        rowSet.setCommand(sql);
        rowSet.execute();
        while(rowSet.next())
        {
            len = rowSet.getInt(1);
        }
        return len;
    }
    
    public static String[] getTableColumns(String tableName) throws SQLException
    {
        int tableLength = getTableLength(tableName);
        String [] ret = new String[tableLength];
        int ctr = 0;
        try {
            String sql = "select COLUMN_NAME\n" +
                    "from INFORMATION_SCHEMA.COLUMNS\n" +
                    "where TABLE_NAME='" + tableName + "'\n";
            rowSet.setCommand(sql);
            rowSet.execute();
            while(rowSet.next())
            {
                ret[ctr] = rowSet.getString(1);
                ctr++;
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("Its fine if its null");
        }
        return ret;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try
        {
            Properties props = props(CONN_INFO);
            setConnection(props);
            selectDB("msc");
            showAllTables();
            Scanner sc = new Scanner(System.in);
            System.out.println("Write your table name: ");
            String tableName = sc.next();
            for (String i : getTableColumns(tableName))
                System.out.println(i);
        }
        catch (FileNotFoundException fe)
        {
            System.out.println("File not found");
        }
        catch (IOException ioe)
        {
            System.out.println("IO error");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
}
