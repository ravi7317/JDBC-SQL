import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_ {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/job";  // Replace 'job' with your DB name
    private static final String USER = "root";                            // MySQL username
    private static final String PASSWORD = "Amivar@2002";                 // MySQL password

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database connected successfully!");
        } 
        catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found!");
            e.printStackTrace();
        } 
        catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
        }
        return conn;
    }

    // Main method to test the connection
    public static void main(String[] args) {
        getConnection();
    }
}
