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
						System.out.println("Name " + menuitem.getName() );
						System.out.println("Price " + menuitem.getPrice() );
				}
				
			}catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
	  	}
	
	public static void printMenuItem(String branch) // print specific branch menu // Method overloading
	  {
		  String filename = "menu.txt" ;
			try {
				// read file containing Professor records.
				ArrayList al = MenuTextDB.readMenuItem(filename) ;
				for (int i = 0 ; i < al.size() ; i++) {
						MenuItem menuitem = (MenuItem)al.get(i);
						System.out.println("Name " + menuitem.getName() );
						System.out.println("Price " + menuitem.getPrice() );
				}
				
			}catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
	  	}
}
