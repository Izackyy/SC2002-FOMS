package Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Enums.OrderStatus;
import Stores.AuthStore;
import Stores.MenuItem;
import Stores.MenuTextDB;
import Stores.Order;
import Stores.OrderTextDB;

public class CheckStatus {

	
	private static final Scanner sc = new Scanner(System.in);
	
	public static void check() throws IOException
	{
		Order o = null;
		int orderID;
		String branch = null;
		
		int selection;
		
		System.out.println("Select branch:");
		System.out.println("1) NTU");
		System.out.println("2) JP");
		System.out.println("3) JE");
		
		selection = sc.nextInt();
		
		switch(selection)
		{
			case(1):
				branch = "NTU";
				break;
			case(2):
				branch = "JP";
				break;
			case(3):
				branch = "JE";
				break;
				//need to insert some sanity check for invalid input 
		}
		
		System.out.println("Please enter OrderID:");
		
		orderID = sc.nextInt();
		
		List<Order> al = OrderTextDB.readOrder("order.txt");//test
	    for (Order order : al)
	    {
	    	if (order.getOrderID() == orderID && order.getBranch().equals(branch))
	    	{
	    		o = order;
	    		break;
	    	}
	    }
	    
	    System.out.println("Order Status: " + o.getOrderStatus());
	}
}

