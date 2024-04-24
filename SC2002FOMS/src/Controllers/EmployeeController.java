package Controllers;

import java.io.IOException;
import java.util.Scanner;

import Services.BranchService;
import Stores.AuthStore;
import Stores.MenuItem;
import Stores.Staff;
import Stores.StaffTextDB;

/*This controller is to lay the the fundamental actions every employee in the branch can do in the system
 * 
 * ie the change password action, view order, process order etc
 * all subclasses should be able to implement this, just need to inherit this method from superclass
 */

public class EmployeeController{
	
	private static final Scanner sc = new Scanner(System.in);
	
	protected static boolean changePassword() throws IOException
	{
		String oldPassword;
		String newPassword;
		
		Staff oldStaff = AuthStore.getCurrentStaff();
		Staff newStaff = new Staff(oldStaff.getName(), oldStaff.getLoginID(), oldStaff.getRole(), oldStaff.getGender(), oldStaff.getAge(), oldStaff.getBranch(), oldStaff.getPassword());
		
		oldPassword = AuthStore.getCurrentStaff().getPassword();
		
		System.out.println("New password:");
		newPassword = sc.nextLine();
		
		if (oldPassword.equals(newPassword))
		{
			return false;
		}
		
		newStaff.setPassword(newPassword);
		
		StaffTextDB.updateStaff("staff.txt", oldStaff , newStaff);
		
		
		return true;
	}


}
