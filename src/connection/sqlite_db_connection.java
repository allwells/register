package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author allie
 */
public class sqlite_db_connection {

    private static final String DB_DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:register.db";
    private static Connection con;

    public static Connection connection() {
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL);
            System.out.println("Connection Successful!");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Connection failed!");
        }
        return con;
    }

}
