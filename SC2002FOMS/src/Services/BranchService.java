package Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Enums.OrderStatus;
import Stores.Order;
import Stores.OrderTextDB;
import Stores.AuthStore;
import Stores.Branch;


public class BranchService{
	
	private static final Scanner sc = new Scanner(System.in);
	

	
	public static void processOrder() throws IOException {
		
		int selection;
		int orderID;
		String branch = null;
			
		System.out.println("Please enter OrderID:");
		
		orderID = sc.nextInt();
		
		Order oldStatus = null;
		Order o = null;
		List<Order> al = OrderTextDB.readOrder("order.txt");//test
	    for (Order order : al)
	    {
	    	if (order.getOrderID() == orderID && order.getBranch().equals(AuthStore.getCurrentStaff().getBranch()))
	    	{
	    		oldStatus = order;
	    		branch = order.getBranch();
	    		break;
	    	}
	    }
	    
	    Order newStatus = new Order(orderID, branch, OrderStatus.READY_TO_PICKUP);
	    
	    OrderTextDB.updateOrder("order.txt", oldStatus, newStatus);
	    
	    System.out.println("Order ID: " + orderID);
	    
	    List<Order> alr = OrderTextDB.readOrder("order.txt");//test
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
	
	
	
	public void viewDetails(String branch) { // need to create orderline to retrieve details of order
		// TODO Auto-generated method stub
		
	}

	
	
	public void displayNewOrder(String branch) throws IOException {
		
		System.out.println("New Orders: ");
		int i=0;
		
		List<Order> al = OrderTextDB.readOrder("order.txt"); 
	    for (Order order : al)
	    {
	    	if (order.getOrderStatus().equals(OrderStatus.PROCESSING)&& order.getBranch().equals(branch))
	    	{
	    		System.out.println(i + ") " + order.getOrderID());
	    	}
	    }
	    
		
	}
	

}
