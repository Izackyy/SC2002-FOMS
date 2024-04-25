package Controllers;
import java.util.*;

import Services.AuthStaffService;

public class AuthController {

    private static final Scanner sc = new Scanner(System.in);
    public static AuthStaffService authService;

    public static void start() {
        boolean authenticated = false;
        System.out.println("Welcome to FOMS :)");
        do {
            try {
                System.out.println("Login as: ");
                System.out.println("1) Customer");
                System.out.println("2) Staff");
                System.out.println("3) Quit");

                int selection = sc.nextInt();
                sc.nextLine(); // Consume the rest of the line
                
                switch (selection) {
                    case (1): // Customer
                        CustomerController.start();
                        return;
                    case (2): // Staff
                        authService = new AuthStaffService();
                        break;
                    default: // Quit
                        return;
                }

                System.out.println("UserID: ");
                String userID = sc.nextLine();

                System.out.println("Password: ");
                String password = sc.nextLine();

                authenticated = authService.login(userID, password);

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                sc.next(); // Clear the scanner
            } catch (Exception e) {
                System.out.println("An error occurred during login: " + e.getMessage());
            }
        } while (!authenticated);
    }

    public static void endSession() {
        authService.logout();
        System.out.println("You have been logged out.");
        start();
    }
}
