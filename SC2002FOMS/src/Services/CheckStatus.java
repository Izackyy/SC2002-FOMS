package Services;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

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
		
		@SuppressWarnings("unchecked")
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
