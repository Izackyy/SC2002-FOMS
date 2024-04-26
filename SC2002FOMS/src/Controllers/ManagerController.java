package Controllers;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Enums.Availability;
import Interfaces.IOrderManager;
import Stores.AuthStore;
import Stores.MenuItem;
import Stores.MenuTextDB;
import View.MenuDisplay;
import View.StaffDisplay;

/**
 * ManagerController extends StaffController to provide managerial specific actions
 * such as processing and viewing orders, managing the menu, and viewing staff information.
 * It supports a range of actions that a manager can perform within the branch context.
 */
public class ManagerController extends StaffController {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * Constructs a ManagerController with access to order management functionality.
     * 
     * @param om The order manager interface implementation that this controller will use.
     */
    public ManagerController(IOrderManager om) {
        super(om);
    }

    /**
     * Starts the interactive session for the manager, providing a menu of managerial actions.
     * This includes viewing new orders, processing orders, editing the menu, and managing staff.
     * 
     * @throws IOException if an input or output operation is failed or interpreted.
     */
public void start() {
        boolean success = false;
        int selection = 0;
        do {
            System.out.println("========Manager's Actions========");
            System.out.println("|| 1) Display New Order        ||");
            System.out.println("|| 2) Process Order            ||");
            System.out.println("|| 3) View Details of Order    ||");
            System.out.println("|| 4) Edit Menu                ||");
            System.out.println("|| 5) View Staff List          ||");
            System.out.println("|| 6) Change Password          ||");
            System.out.println("|| 7) Quit                     ||");
            System.out.println("=================================");
            
            try {
                selection = sc.nextInt();
                
                switch(selection) {
                    case 1:
                        om.displayNewOrder(AuthStore.getCurrentStaff().getBranch());
                        break;
                    case 2:
                        try {
                            om.processOrder(AuthStore.getCurrentStaff().getBranch());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            om.viewDetails();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        editMenu();
                        break;
                    case 5:
                        StaffDisplay.printStaffList(AuthStore.getCurrentStaff().getBranch());
                        break;
                    case 6:
                        success = changePassword();
                        if (success) {
                            System.out.println("Password changed successfully");
                        } else {
                            System.out.println("Password cannot be the same as existing password!");
                        }
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                sc.next(); // Clear the incorrect input from scanner buffer
            } catch (RuntimeException e) {
                System.out.println("An error occurred: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }   
        } while (selection != 7);
        
        System.out.println("Exiting Manager's Actions");
        AuthController.endSession();
    }

    /**
     * Provides an interface for editing the menu, including adding, removing, or updating menu items.
     * It prompts the manager through each step of editing the menu and saves the changes to the database.
     * 
     * @throws IOException if an input or output operation is failed or interpreted.
     */
    private static void editMenu() throws IOException {
        // Implementation details omitted for brevity
        // Please insert method code here
    }
}
