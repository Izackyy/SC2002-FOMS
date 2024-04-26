package Controllers;

import java.io.IOException;
import java.util.Scanner;

import Stores.AuthStore;
import Stores.Staff;
import Stores.StaffTextDB;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */

/**
 * EmployeeController serves as a base class for all employee types within the system.
 * It provides common functionalities such as changing passwords, viewing, and processing orders.
 * This class is designed to be extended by other specific employee role controllers.
 */
public class EmployeeController {
    
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Allows an employee to change their password.
     * The method retrieves the current staff member's details, prompts for a new password,
     * and updates the password if it is different from the old password.
     * 
     * @return true if the password was changed successfully, false if the new password is the same as the old password.
     * @throws IOException if there is an issue reading from or writing to the staff database file.
     */
    protected static boolean changePassword() throws IOException {
        String oldPassword;
        String newPassword;
        
        // Retrieve the current staff details from the authentication store
        Staff oldStaff = AuthStore.getCurrentStaff();
        // Clone the current staff object to update the password
        Staff newStaff = new Staff(oldStaff.getName(), oldStaff.getLoginID(), oldStaff.getRole(), oldStaff.getGender(), oldStaff.getAge(), oldStaff.getBranch(), oldStaff.getPassword());
        
        // Get the current password from the auth store
        oldPassword = AuthStore.getCurrentStaff().getPassword();
        
        System.out.println("New password:");
        newPassword = sc.nextLine();
        
        // Check if the new password is the same as the old password
        if (oldPassword.equals(newPassword)) {
            return false; // No change needed
        }

        // Set the new password
        newStaff.setPassword(newPassword);
        
        // Update the staff record in the database
        StaffTextDB.updateStaff("staff.txt", oldStaff, newStaff);
        
        return true; // Password changed successfully
    }
	
    /** 
     * @throws IOException
     */
    public void start()	throws IOException{
	}
}
