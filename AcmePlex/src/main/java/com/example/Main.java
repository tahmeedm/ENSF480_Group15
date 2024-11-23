package main.java.com.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Set up the database (creates tables and inserts initial data)
        Database.setupDatabase();

        Controller controller = new Controller();
        Scanner scanner = new Scanner(System.in);

        // Show the menu
        while (true) {
            System.out.println("Welcome to the Movie Theater!");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. View Movies");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                // Login
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                boolean isLoggedIn = controller.loginUser(username, password);

                if (isLoggedIn) {
                    // Logged in successfully, show movie options
                    controller.getMovies();
                }
            } else if (choice == 2) {
                // Register
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                controller.registerUser(username, password, email);
            } else if (choice == 3) {
                // View Movies
                controller.getMovies();
            } else if (choice == 4) {
                // Exit
                break;
            }
        }
        scanner.close();
    }
}
