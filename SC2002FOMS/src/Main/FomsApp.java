

package Main;

import java.io.IOException;

import Controllers.AdminController;
import Controllers.AuthController;
import Controllers.ManagerController;
import Controllers.StaffController;
import Enums.Role;
import Interfaces.IBranchManagement;
import Interfaces.IStaffManagement;
import Interfaces.IOrderManager;
import Interfaces.IPaymentManagement;
import Services.BranchManager; 
import Services.OrderManager; 
import Services.PaymentManager;
import Services.StaffManager;
import Stores.AuthStore;
import Stores.Staff;

/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */

/**
 * The {@code FomsApp} class is the main class that starts the application.
 * It checks the role of the current user and starts the appropriate controller.
 */
public class FomsApp {
	
	/**
	 * The main method of the application.
	 * It starts the application by checking the role of the current user and starting the appropriate controller.
	 * @param aArgs The arguments passed to the application.
	 * @throws IOException If an I/O error occurs.
	 */
	public static void main(String[]aArgs) throws IOException
	{
		AuthController.start(); //to log in
		
		
		Staff staff = AuthStore.getCurrentStaff();
		
		if(staff!=null)//if null then is customer
		{	
			if (staff.getRole().equals(Role.S)) //plan to change to enum
			{
				IOrderManager om = new OrderManager();
				StaffController staffController = new StaffController(om);
				staffController.start();
			}
			else if (staff.getRole().equals(Role.M))
			{
				IOrderManager om = new OrderManager();
				ManagerController managerController = new ManagerController(om);
				managerController.start();
			}
			else //admin
			{
				IBranchManagement bm = new BranchManager();
				IStaffManagement sm = new StaffManager();
				IPaymentManagement pm = new PaymentManager();
				AdminController adminController = new AdminController(bm,sm,pm);
				adminController.start();
			}
		}
		
		System.out.println("Thank you for using FOMS!");
	}
}

