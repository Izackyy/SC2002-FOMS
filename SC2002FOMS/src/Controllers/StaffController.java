package Controllers;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Services.OrderManager;
import Stores.AuthStore;

public class StaffController extends EmployeeController {
    int selection;
    private static final Scanner sc = new Scanner(System.in);
    protected OrderManager om = new OrderManager();

    public void start() throws 	IOException{  // Removed 'throws IOException' because we are handling all IO exceptions inside
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
                            om.processOrder();
                        } catch (Exception e) {  // Catch all exceptions from processOrder
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
					try {
						om.viewDetails();
					} catch (Exception e) {  // Catch all exceptions from processOrder
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
            } catch (RuntimeException e) {  // Catching RuntimeException for general runtime issues, including unchecked exceptions
                System.out.println("An error occurred: " + e.getMessage());
            } finally {
                // Optional: Can add cleanup code here if needed
            }
        } while (selection != 5);
    }
}
