package Services;

import java.io.IOException;
import java.util.ArrayList;

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
						System.out.printf("Name: %-20s Price: $%-10.2f Category: %s\n", menuitem.getName(), menuitem.getPrice(), menuitem.getCategory());
				}
				
			}catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
	  	}
	
	public static void printMenuItem(String branch) // print specific branch menu // Method overloading
	  {
		  String filename = "menu.txt" ;
		  
		  System.out.println("============================" + branch + " Menu============================");
			try {
				// read file containing Professor records.
				ArrayList al = MenuTextDB.readMenuItem(filename) ;
				for (int i = 0 ; i < al.size() ; i++) {
						MenuItem menuitem = (MenuItem)al.get(i);
						
						if (menuitem.getBranch().equals(branch))
						{
							System.out.printf("Name: %-20s Price: $%-10.2f Category: %s\n", menuitem.getName(), menuitem.getPrice(), menuitem.getCategory());
						}
						/*
						System.out.println("Name " + menuitem.getName() );
						System.out.println("Price " + menuitem.getPrice() );
						System.out.println("Category " + menuitem.getCategory());
						*/
				}
				
			}catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
			System.out.println("");
	  	}
}
