import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class FetchEmployee {

    private static final String URL = "jdbc:mysql://localhost:3306/job";
    private static final String USER = "root";
    private static final String PASSWORD = "Amivar@2002";

    public static void main(String[] args) {
        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to database!\n");

            // Create statement
            Statement stmt = conn.createStatement();

            // Execute query
            String sql = "SELECT * FROM employee";
            ResultSet rs = stmt.executeQuery(sql);

            // Print result
            System.out.println("ID\tName\tAge");
            System.out.println("---------------------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("Name");
                int age = rs.getInt("Age");

                System.out.println(id + "\t" + name + "\t" + age);
            }

            // Close connection
            rs.close();
            stmt.close();
            conn.close();
        } 
        catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found!");
            e.printStackTrace();
        } 
        catch (SQLException e) {
            System.out.println("❌ Database error!");
            e.printStackTrace();
        }
    }
}
