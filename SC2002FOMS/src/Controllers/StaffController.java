package Controllers;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Interfaces.IOrderManager;
import Services.OrderManager;
import Stores.AuthStore;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */

/**
 * StaffController extends the EmployeeController to provide general staff-related actions
 * like displaying new orders, processing orders, and changing passwords.
 * This controller serves as a base for more specific staff roles by providing common functionalities.
 */
public class StaffController extends EmployeeController {
    
    private static final Scanner sc = new Scanner(System.in);
    protected IOrderManager om;

    /**
     * Constructs a StaffController with an order manager to manage order-related actions.
     * 
     * @param om the order manager interface implementation that this controller will use.
     */
    public StaffController(IOrderManager om) {
        this.om = om;
    }

    /**
     * Starts the interactive session for the staff, providing a menu of actions including
     * order display, order processing, order details viewing, password changing, and quitting the session.
     * Handles user inputs and exceptions related to order management tasks.
     */
    public void start() {
        int selection = 0;
        do {
            System.out.println("======Staff Actions======");
            System.out.println("1) Display New Orders");
            System.out.println("2) Process Order");
            System.out.println("3) View details of Order");
            System.out.println("4) Change Password");
            System.out.println("5) Quit");
            System.out.println("=========================");

            try {
                System.out.println("Please make a selection:");
                selection = sc.nextInt();

                switch (selection) {
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
                        changePassword();
                        break;
                    case 5:
                        System.out.println("Quitting...");
                        break;
                    default:
                        System.out.println("Invalid selection");
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
        } while (selection != 5);
    }
}
