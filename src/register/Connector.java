package register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author allie
 */
public class Connector
{
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/register?zeroDateTimeBehavior=convertToNull";
    
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    public static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            
            System.out.println("Connected!");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}