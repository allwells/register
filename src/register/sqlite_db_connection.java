package register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author allie
 */
public class sqlite_db_connection {
    
    private static final String DB_DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:register.db";
    private static  Connection con;
    private static Statement stmt;
    
    public static Statement connection(String sql){
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL);
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                
            }
            System.out.println("Connection Successful!");
        } catch (Exception ex) {
            System.out.println("Connection failed!");
        }
        return stmt;
    }
    
}
