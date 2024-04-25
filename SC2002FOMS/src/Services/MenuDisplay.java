package Services;

import java.io.IOException;
import java.util.ArrayList;

import Enums.Availability;
import Stores.MenuItem;
import Stores.MenuTextDB;

public class MenuDisplay {
	
	public static void printMenuItem() // print entire menu
	  {
		  String filename = "menu.txt" ;
			try {
				ArrayList al = MenuTextDB.readMenuItem(filename) ;
				for (int i = 0 ; i < al.size() ; i++) {
						MenuItem menuitem = (MenuItem)al.get(i);
						System.out.printf("Name: %-20s Price: $%-10.2f Category: %-20s Description: %s\n", menuitem.getName(), menuitem.getPrice(), menuitem.getCategory(), menuitem.getDescription());
				}
			}catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
	  	}
	
	public static void printMenuItem(String branch) // print specific branch menu // Method overloading
	  	{
		String filename = "menu.txt" ;
		
		System.out.println("=============================================" + branch + " Menu=============================================");
		try {
			// read file containing Professor records.
			ArrayList al = MenuTextDB.readMenuItem(filename) ;
			for (int i = 0 ; i < al.size() ; i++) {
					MenuItem menuitem = (MenuItem)al.get(i);
					
					if (menuitem.getBranch().equals(branch) && menuitem.getAvailability().equals(Availability.AVAILABLE))
					{
						System.out.printf("Name: %-20s Price: $%-10.2f Category: %-20s Description: %s\n", menuitem.getName(), menuitem.getPrice(), menuitem.getCategory(), menuitem.getDescription());
					}
				}
			} catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
		}
		System.out.println("");
	}

	public static void printMenuItemS(String branch) 
	  	{
		String filename = "menu.txt" ;
		
		System.out.println("==========================================================" + branch + " Menu==========================================================");
		try {
			// read file containing Professor records.
			ArrayList al = MenuTextDB.readMenuItem(filename) ;
			for (int i = 0 ; i < al.size() ; i++) {
					MenuItem menuitem = (MenuItem)al.get(i);
					
					if (menuitem.getBranch().equals(branch))
					{
						System.out.printf("Name: %-20s Price: $%-6.2f Category: %-20s Description: %-20s Availabilty %s\n", menuitem.getName(), menuitem.getPrice(), menuitem.getCategory(), menuitem.getDescription(), menuitem.getAvailability());
					}
				}
			} catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
		}
		System.out.println("");
	}
		
}
