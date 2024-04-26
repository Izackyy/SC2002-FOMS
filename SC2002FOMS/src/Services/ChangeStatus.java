package Services;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Enums.OrderStatus;
import Stores.Order;
import Stores.OrderTextDB;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */
/**
 * ChangeStatus class allows the staff to change the status of an order to COMPLETED.
 */
public class ChangeStatus {
	
	private static final Scanner sc = new Scanner(System.in);
	/**
	 * Changes the status of an order to COMPLETED.
	 * 
	 * @param branch The branch where the order is located.
	 * @throws IOException If an error occurs during file operations.
	 */
	public static void changeStatus(String branch) throws IOException
	{
		int orderID;
			
		System.out.println("Please enter OrderID:");
		
		orderID = sc.nextInt();
		
		Order oldStatus = null;
		@SuppressWarnings("unchecked")
		List<Order> al = OrderTextDB.readOrder("order.txt");//test
	    for (Order order : al)
	    {
	    	if (order.getOrderID() == orderID && order.getBranch().equals(branch))
	    	{
	    		oldStatus = order;
	    		break;
	    	}
	    }

		if (oldStatus == null)
		{
			System.out.println("OrderID does not exist.");
			return;
		}
	    
	    // to check
	    
	    if(oldStatus.getOrderStatus().equals(OrderStatus.PROCESSING) || oldStatus.getOrderStatus().equals(OrderStatus.CANCELLED))
		{

			if(oldStatus.getOrderStatus().equals(OrderStatus.PROCESSING))
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
	    
	    Order o = null;
	    
	    Order newStatus = new Order(orderID, branch, OrderStatus.COMPLETED);
	    
	    OrderTextDB.updateOrder("order.txt", oldStatus, newStatus);
	    
	    System.out.println("Order ID: " + orderID);
	    
	    @SuppressWarnings("unchecked")
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
}
