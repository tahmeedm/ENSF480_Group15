package main.java.com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/AcmePlex";
    private static final String USER = "root";
    private static final String PASSWORD = "ensf480g15";

    // Method to get a connection to the database
    public Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
            throw e;
        }
    }

    // Method to execute the setup.sql file
    public static void setupDatabase() {
        String filePath = "path/to/your/setup.sql";  // Update with the correct path to your setup.sql
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Load the SQL script from file
            String sql = new String(Files.readAllBytes(Paths.get(filePath)));

            // Execute the SQL script
            stmt.executeUpdate(sql);

            System.out.println("Database setup completed successfully.");

        } catch (IOException e) {
            System.out.println("Error reading setup.sql file: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error executing SQL script: " + e.getMessage());
        }
    }
}
