package Controllers;
import java.util.*;

import Services.AuthStaffService;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */

/**
 * AuthController handles the authentication processes for users of the FOMS system.
 * It supports login for customers and staff and manages user sessions.
 */
public class AuthController {

    private static final Scanner sc = new Scanner(System.in);
    public static AuthStaffService authService;

    /**
     * Starts the authentication process for the system. Users can log in as customers or staff,
     * and the method handles user input errors and authentication validation.
     */
    public static void start() {
        boolean authenticated = false;
        System.out.println("===========================");
        System.out.println("||                       ||");
        System.out.println("||   Welcome to FOMS :)  ||");
        System.out.println("||                       ||");
        System.out.println("===========================");
        while (!authenticated) {
            try {
                System.out.println("Login as: ");
                System.out.println("1) Customer");
                System.out.println("2) Staff");
                System.out.println("3) Quit");

                if (!sc.hasNextInt()) {
                    System.out.println("Please enter a number.");
                    sc.next(); // Consume the non-integer input
                    continue; // Skip to the next iteration
                }
                int selection = sc.nextInt();
                sc.nextLine(); // Consume the newline left over

                switch (selection) {
                    case 1: // Customer
                        CustomerController.start();
                        return; // Exit after handling customer
                    case 2: // Staff
                        authService = new AuthStaffService();
                        break;
                    case 3: // Quit
                        return; // Exit the loop
                    default:
                        System.out.println("Invalid option. Please select 1, 2, or 3.");
                        continue;
                }

                System.out.println("UserID: ");
                String userID = sc.nextLine();
                if (userID.isEmpty()) {
                    System.out.println("User ID cannot be empty.");
                    continue;
                }

                System.out.println("Password: ");
                String password = sc.nextLine();
                if (password.isEmpty()) {
                    System.out.println("Password cannot be empty.");
                    continue;
                }

                authenticated = authService.login(userID, password);
                if (!authenticated) {
                    System.out.println("Invalid credentials, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine(); // Clear the scanner buffer
                continue; // Explicitly continue the loop
            } catch (Exception e) {
                System.out.println("An error occurred during login: " + e.getMessage());
                return; // Possibly exit, or handle differently
            }
        }
    }

    /**
     * Ends the user session by logging out and notifies the user of the logout.
     */
    public static void endSession() {
        authService.logout();
        System.out.println("You have been logged out.");
    }
}
