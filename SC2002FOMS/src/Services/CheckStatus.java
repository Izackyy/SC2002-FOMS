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
	
	public static void check(String branch) throws IOException
	{
		Order o = null;
		int orderID;
		
		System.out.println("Enter Order ID:");
		
		orderID = sc.nextInt();
		
		List<Order> al = OrderTextDB.readOrder("order.txt");//test
		boolean found = false;
	    for (Order order : al)
	    {
	    	if (order.getOrderID() == orderID && order.getBranch().equals(branch))
	    	{
				found = true;
	    		o = order;
	    		break;
	    	}
	    }

		if(found == false)
		{
			System.out.println("OrderID does not exist");
			return;
		}

		if(o.getOrderStatus().equals(OrderStatus.PROCESSING) || o.getOrderStatus().equals(OrderStatus.CANCELLED))
		{

			if(o.getOrderStatus().equals(OrderStatus.PROCESSING))
			{
				System.out.println("Order is not ready yet.");
				return;
			}
			else
			{
				System.out.println("Order has been cancelled");
				return;
			}
		}
	    
	    System.out.println("Order Status: " + o.getOrderStatus());
	}
}

