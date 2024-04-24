package Controllers;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Stores.AuthStore;
import Stores.MenuItem;
import Stores.MenuTextDB;
import Services.MenuDisplay;
import Services.StaffDisplay;

public class ManagerController extends StaffController // inheritence
{
	
	private static final Scanner sc = new Scanner(System.in);

	public void start() throws IOException
	{
		boolean success = false;
		int selection;
		do
		{
			System.out.println("========Manager's Actions========");
			System.out.println("|| 1) Display New Order        ||");
			System.out.println("|| 2) Process Order            ||");
			System.out.println("|| 3) View Details of Order    ||");
			System.out.println("|| 4) Edit Menu                ||");
			System.out.println("|| 5) View Staff List          ||");
			System.out.println("|| 6) Change Password          ||");
			System.out.println("|| 7) Quit                     ||");
			System.out.println("=================================");
			
			selection = sc.nextInt();
			
			switch(selection)
			{
				case(1):
					displayNewOrder(AuthStore.getCurrentStaff().getBranch());
					break;
				case(2):
					processOrder();
					break;
				case(3):
					viewDetails();
					break;
				case(4):
					editMenu();
					break;
				case(5):
					StaffDisplay.printStaffList(AuthStore.getCurrentStaff().getBranch());
					break;
				case(6):
					success = changePassword();
					if (success)
					{
						System.out.println("Password changed successfully");
					}
					else
					{
						System.out.println("Password cannot be the same as existing password!");
					}
					break;
			}
			
		}while(selection!=7);
		
		System.out.println("Exiting Manager's Actions");
		AuthController.endSession();
	}


	private static void editMenu() throws IOException
	{
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
				
				List<MenuItem> al = MenuTextDB.readMenuItem("menu.txt");//test
		        for (MenuItem menuitem : al)
		        {
		        	if (menuitem.getName().equals(name) && menuitem.getBranch().equals(AuthStore.getCurrentStaff().getBranch()))
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
				
				System.out.println("Item added:");
				System.out.println("name:" + name + ", price:" + price + ", category:" + category);
			
				MenuItem menuitem = new MenuItem(name, price , AuthStore.getCurrentStaff().getBranch(), category);
				MenuTextDB.addMenuItem("menu.txt", menuitem);
				break;
			}
			
			case(2):
			{
				System.out.println("Name:");
				String name = sc.nextLine();
				
				List<MenuItem> al = MenuTextDB.readMenuItem("menu.txt");//test
				MenuItem toRemove = null;
		        for (MenuItem menuitem : al)
		        {
		        	if (menuitem.getName().equals(name) && menuitem.getBranch().equals(AuthStore.getCurrentStaff().getBranch()))
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
				
				List<MenuItem> al = MenuTextDB.readMenuItem("menu.txt");//test
		        for (MenuItem menuitem : al)
		        {
		        	if (menuitem.getName().equals(oldName) && menuitem.getBranch().equals(AuthStore.getCurrentStaff().getBranch()))
		        	{
		        		oldItem = menuitem;
		        		break;
		        	}
		        }
				
				MenuItem newItem = new MenuItem(oldItem.getName(), oldItem.getPrice(), oldItem.getBranch(), oldItem.getCategory(), oldItem.getCategory());
				
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
				System.out.println("Update description? (Y/N)"); // something wrong with this i can change existing items, but not the ones i created
				
				yesNo = sc.nextLine();
				if (yesNo.equalsIgnoreCase("Y"))
				{
					System.out.println("New description");
					String newDescription = sc.nextLine();
					newItem.setDescription(newDescription);
					yesNo = "N";
				}
				
				MenuTextDB.updateMenuItem("menu.txt", oldItem, newItem);
				break;
			}
		}
		System.out.println("Updated Menu:");
		MenuDisplay.printMenuItem(AuthStore.getCurrentStaff().getBranch());
	}
	
}
