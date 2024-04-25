package Main;

import java.io.IOException;

import Controllers.AdminController;
import Controllers.AuthController;
import Controllers.ManagerController;
import Controllers.StaffController;
import Enums.Role;
import Stores.AuthStore;
import Stores.Staff;


public class FomsApp {
	

	public static void main(String[]aArgs) throws IOException
	{
		AuthController.start(); //to log in
		
		
		Staff staff = AuthStore.getCurrentStaff();
		
		if(staff!=null)//if null then is customer
		{	
			if (staff.getRole().equals(Role.S)) //plan to change to enum
			{
				StaffController staffController = new StaffController();
				staffController.start();
			}
			else if (staff.getRole().equals(Role.M))
			{
				ManagerController managerController = new ManagerController();
				managerController.start();
			}
			else //admin
			{
				AdminController adminController = new AdminController();
				adminController.start();
			}
		}
		
		
        //StaffTextDB.printStaffList();
	}
}
