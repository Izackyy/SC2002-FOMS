package Services;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Enums.OrderStatus;
import Stores.Order;
import Stores.OrderTextDB;

public class ChangeStatus {
	
	private static final Scanner sc = new Scanner(System.in);
	
	public static void changeStatus(String branch) throws IOException
	{
		int orderID;
			
		System.out.println("Please enter OrderID:");
		
		orderID = sc.nextInt();
		
		Order oldStatus = null;
		Order o = null;
		List<Order> al = OrderTextDB.readOrder("order.txt");
	    for (Order order : al)
	    {
	    	if (order.getOrderID() == orderID && order.getBranch().equals(branch))
	    	{
	    		oldStatus = order;
	    		break;
	    	}
	    }
	    
	    Order newStatus = new Order(orderID, branch, OrderStatus.COLLECTED);
	    
	    OrderTextDB.updateOrder("order.txt", oldStatus, newStatus);
	    
	    System.out.println("Order ID: " + orderID);
	    
	    List<Order> alr = OrderTextDB.readOrder("order.txt");
	    for (Order order : alr)
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
