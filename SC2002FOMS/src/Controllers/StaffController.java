package Controllers;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Entity.OrderCancellation;
import Enums.OrderStatus;
import Stores.AuthStore;
import Stores.Order;
import Stores.OrderLine;
import Stores.OrderLineTextDB;
import Stores.OrderTextDB;

public class StaffController extends EmployeeController{ // inheritance
	int selection;
	private static final Scanner sc = new Scanner(System.in);

	public void start() throws IOException
	{do{
		System.out.println("======Staff Actions======");
		System.out.println("1) Display New Orders");
		System.out.println("2) Process Order");
		System.out.println("3) View details of Order");
		System.out.println("4) Quit");
		System.out.println("=========================");

		try {
                selection = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                sc.next(); // Clear the wrong input from scanner buffer
                continue;
            }
		
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
				System.out.println("Quitting...");
				break;
			default:
				System.out.println("Invalid selection");
				break;
		}
		}while(selection!=4);
	}

	public void processOrder() throws IOException 
	{	
		int orderID;
		String branch = null;
			
		System.out.println("Please enter OrderID:");
		
		orderID = sc.nextInt();
		
		Order oldStatus = null;
		Order o = null;
		@SuppressWarnings("unchecked")
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
	    
	    OrderCancellation orderCancellation = new OrderCancellation();
	    orderCancellation.scheduleOrderCancellation(orderID, AuthStore.getCurrentStaff().getBranch());
	    
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
	
	public void viewDetails() throws IOException { // need to create orderline to retrieve details of order
		
		int orderID;
		System.out.println("Order ID:");
		orderID = sc.nextInt();
		
		@SuppressWarnings("unchecked")
		List<OrderLine> al = OrderLineTextDB.readOrderLine("orderline.txt");//test
		
		System.out.println("Order details for orderID " + orderID);
		int exist;
		exist = 0;
	    for (OrderLine orderLine : al)
	    {
	    	if (orderLine.getOrderID() == orderID)
	    	{
	    		exist = 1;
	    		System.out.printf("Name: %-20s Quantity: %-10d\n", orderLine.getName(), orderLine.getQuantity());
	    	}
	    }
	    
	    if(exist == 0)
	    {
	    	System.out.println("OrderID does not exist");
	    }
		
	}

	public void displayNewOrder(String branch) throws IOException {
		
		System.out.println("New Orders: ");
		
		@SuppressWarnings("unchecked")
		List<Order> al = OrderTextDB.readOrder("order.txt"); 
	    for (Order order : al)
	    {
	    	if (order.getOrderStatus().equals(OrderStatus.PROCESSING)&& order.getBranch().equals(branch))
	    	{
	    		System.out.println(order.getOrderID());
	    	}
	    }
	}
}
