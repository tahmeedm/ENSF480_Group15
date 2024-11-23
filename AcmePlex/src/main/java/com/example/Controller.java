package main.java.com.example;

import java.sql.*;
import java.util.Scanner;

public class Controller {

    private Database db;
    private Scanner scanner;

    public Controller() {
        this.db = new Database();
        this.scanner = new Scanner(System.in);
    }

    // Add a new movie
    public void addMovie(String title, String genre, String director, int releaseYear) {
        String query = "INSERT INTO Movies (title, genre, director, release_year) VALUES (?, ?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, genre);
            stmt.setString(3, director);
            stmt.setInt(4, releaseYear);
            stmt.executeUpdate();
            System.out.println("Movie added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding movie: " + e.getMessage());
        }
    }

    // Get all movies
    public void getMovies() {
        String query = "SELECT * FROM Movies";
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("Movie ID: " + rs.getInt("movie_id"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Genre: " + rs.getString("genre"));
                System.out.println("Director: " + rs.getString("director"));
                System.out.println("Release Year: " + rs.getInt("release_year"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving movies: " + e.getMessage());
        }
    }

    // Get showtimes for a specific movie
    public void getShowtimesForMovie(int movieId) {
        String query = "SELECT * FROM Showtimes WHERE movie_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, movieId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Showtime ID: " + rs.getInt("showtime_id"));
                System.out.println("Showtime: " + rs.getTimestamp("showtime"));
                System.out.println("Theater ID: " + rs.getInt("theater_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving showtimes: " + e.getMessage());
        }
    }

    // Book tickets for a showtime
    public void bookTickets(int userId, int showtimeId, int seats) {
        String query = "INSERT INTO Tickets (user_id, showtime_id, seats) VALUES (?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, showtimeId);
            stmt.setInt(3, seats);
            stmt.executeUpdate();
            System.out.println("Tickets booked successfully.");
        } catch (SQLException e) {
            System.out.println("Error booking tickets: " + e.getMessage());
        }
    }

    // User login
    public boolean loginUser(String username, String password) {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);  // In a real app, hash the password before comparing
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Login successful.");
                return true;
            } else {
                System.out.println("Invalid username or password.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
            return false;
        }
    }

    // User registration
    public void registerUser(String username, String password, String email) {
        String query = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);  // In a real app, hash the password before storing
            stmt.setString(3, email);
            stmt.executeUpdate();
            System.out.println("Registration successful.");
        } catch (SQLException e) {
            System.out.println("Error during registration: " + e.getMessage());
        }
    }
}
