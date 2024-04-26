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
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */

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
	 * Starts the interactive session for the manager, providing a menu of actions including
	 * displaying new orders, processing orders, viewing order details, editing the menu, viewing staff list,
	 * changing passwords, and quitting the session. Handles user inputs and exceptions related to order management tasks.
	 */
	@Override
	public void start(){
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
    private static void editMenu() throws IOException
	{
		// print menu so they can refer
		MenuDisplay.printMenuItem(AuthStore.getCurrentStaff().getBranch());
		String yesNo; 
		System.out.println("===========Menu Editor===========");
		System.out.println("|| 1) Add Item                 ||");
		System.out.println("|| 2) Remove Item              ||");
		System.out.println("|| 3) Edit Item                ||");
		System.out.println("=================================");
		
		int editChoice = sc.nextInt();
		sc.nextLine(); //input buffer
		switch (editChoice)
		{
			case(1):
			{
				System.out.println("Name:");
				String name = sc.nextLine();
				
				@SuppressWarnings("unchecked") 
				// telling the compiler to ignore warnings related to unchecked operations within the scope of the annotated element.
				List<MenuItem> al = MenuTextDB.readMenuItem("menu.txt");//test
		        for (MenuItem menuitem : al)
		        {
		        	if (menuitem.getName().equalsIgnoreCase(name) && menuitem.getBranch().equals(AuthStore.getCurrentStaff().getBranch()))
		        	{
		        		System.out.println("Item already exists");
		        		return;
		        	}
		        }
		       
				System.out.println("Price:");
				double price = sc.nextDouble();
				sc.nextLine(); //input buffer
				System.out.println("Category:");
				String category = sc.nextLine();
				System.out.println("Want to add description? (Y/N)");
				String desc = sc.nextLine();

				String description;

				if(desc.equalsIgnoreCase("Y"))
				{
					System.out.println("Description: ");
					description = sc.nextLine();
				}
				else
				{
					description = "NA";
				}
				
				System.out.println("Item has been successfully added:");
			
				MenuItem menuitem = new MenuItem(name, price , AuthStore.getCurrentStaff().getBranch(), category, description);
				MenuTextDB.addMenuItem("menu.txt", menuitem);
				break;
			}
			
			case(2):
			{
				System.out.println("Name:");
				String name = sc.nextLine();

				@SuppressWarnings("unchecked")
				List<MenuItem> al = MenuTextDB.readMenuItem("menu.txt");//test
				MenuItem toRemove = null;
		        for (MenuItem menuitem : al)
		        {
		        	if (menuitem.getName().equalsIgnoreCase(name) && menuitem.getBranch().equals(AuthStore.getCurrentStaff().getBranch()))
		        	{
		        		toRemove = menuitem;
		        		MenuTextDB.removeMenuItem("menu.txt", toRemove);
		        		System.out.println("Item removed successfully.");
		        		break;
		        	}
		        }
		        if (toRemove == null)
		        {
		        	System.out.println("Item does not exist");
		        }
				break;
			}
			case(3):
			{
				MenuItem oldItem = null;
				System.out.println("Item to update:");
				String oldName = sc.nextLine(); //need to check if this item exists
				
				@SuppressWarnings("unchecked")
				List<MenuItem> al = MenuTextDB.readMenuItem("menu.txt");//test
				boolean found = false;
		        for (MenuItem menuitem : al)
		        {
		        	if (menuitem.getName().equalsIgnoreCase(oldName) && menuitem.getBranch().equals(AuthStore.getCurrentStaff().getBranch()))
		        	{
		        		found = true;
		        		oldItem = menuitem;
		        		break;
		        	}
		        }
		        
		        if(found==false)
		        {
		        	break;
		        }
		        
		        System.out.println("Current details of selected item:");
		        
		        System.out.println("Name: " + oldItem.getName());
		        System.out.println("Price: $" + oldItem.getPrice());
		        System.out.println("Category: " + oldItem.getCategory());
		        System.out.println("Availability: " + oldItem.getAvailability());
		        System.out.println("");
				
				MenuItem newItem = new MenuItem(oldItem.getName(), oldItem.getPrice(), oldItem.getBranch(), oldItem.getCategory(), oldItem.getCategory(),oldItem.getAvailability());
				
				System.out.println("Update name? (Y/N)");
				yesNo = sc.nextLine();
				if (yesNo.equalsIgnoreCase("Y"))
				{
					System.out.println("New name:");
					String newName = sc.nextLine();
					newItem.setName(newName);
					yesNo = "N";
				}
				System.out.println("Update price? (Y/N)");
				yesNo = sc.nextLine();
				if (yesNo.equalsIgnoreCase("Y"))
				{
					System.out.println("New price:");
					double newPrice = sc.nextDouble();
					newItem.setPrice(newPrice);
					yesNo = "N";
					sc.nextLine(); //input buffer
				}
				
				System.out.println("Update category? (Y/N)");
				yesNo = sc.nextLine();
				if (yesNo.equalsIgnoreCase("Y"))
				{
					System.out.println("New category");
					String newCategory = sc.nextLine();
					newItem.setCategory(newCategory);
					yesNo = "N";
				}
				System.out.println("Update description? (Y/N)"); 
				
				yesNo = sc.nextLine();
				if (yesNo.equalsIgnoreCase("Y"))
				{
					System.out.println("New description");
					String newDescription = sc.nextLine();
					newItem.setDescription(newDescription);
					yesNo = "N";
				}
				
				System.out.println("Update availability? (Y/N)");
				
				yesNo = sc.nextLine();
				if (yesNo.equalsIgnoreCase("Y"))
				{
					if(oldItem.getAvailability().equals(Availability.AVAILABLE))
					{
						newItem.setAvailability(Availability.UNAVAILABLE);
					}
					else
					{
						newItem.setAvailability(Availability.AVAILABLE);
					}
					yesNo = "N";
				}
				
				MenuTextDB.updateMenuItem("menu.txt", oldItem, newItem);
				break;
			}
		}
		System.out.println("Updated Menu:");
		MenuDisplay.printMenuItemS(AuthStore.getCurrentStaff().getBranch());
	}
}
