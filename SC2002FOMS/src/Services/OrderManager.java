package Services;

import java.io.IOException;
import java.util.List;

import Entity.OrderCancellation;
import Enums.OrderStatus;
import Interfaces.IOrderManager;
import Stores.AuthStore;
import Stores.Order;
import Stores.OrderLine;
import Stores.OrderLineTextDB;
import Stores.OrderTextDB;
import java.util.Scanner;

public class OrderManager implements IOrderManager{
    
    Scanner sc = new Scanner(System.in);
	@SuppressWarnings("unchecked")
	public void processOrder() throws IOException {
		String input;
		int orderID;
		String branch = null;
		Order oldStatus = null;
		Order o = null;
	
		System.out.println("Please enter OrderID:");
		input = sc.nextLine().trim();  // Read the entire line of input
	
		if (input.isEmpty()) {
			throw new IllegalArgumentException("No input provided");
		}
	
		try {
			orderID = Integer.parseInt(input);  // Try to parse the integer
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Error! Invalid input. Order ID must be a number.");
		}
	
		List<Order> allOrders = OrderTextDB.readOrder("order.txt");
		boolean found = false;
		for (Order order : allOrders) {
			if (order.getOrderID() == orderID && order.getBranch().equals(AuthStore.getCurrentStaff().getBranch())) {
				found = true;
				oldStatus = order;
				branch = order.getBranch();
				break;
			}
		}
	
		if (!found) {
			throw new IllegalArgumentException("Error! Order does not exist");
		}
	
		Order newStatus = new Order(orderID, branch, OrderStatus.READY_TO_PICKUP);
		OrderTextDB.updateOrder("order.txt", oldStatus, newStatus);
	
		OrderCancellation orderCancellation = new OrderCancellation();
		orderCancellation.scheduleOrderCancellation(orderID, AuthStore.getCurrentStaff().getBranch());
	
		System.out.println("Order ID: " + orderID);
	
		allOrders = OrderTextDB.readOrder("order.txt");
		for (Order order : allOrders) {
			if (order.getOrderID() == orderID && order.getBranch().equals(branch)) {
				o = order;
				break;
			}
		}
		System.out.println("Order Status: " + o.getOrderStatus());
	}
	
	
	public void viewDetails() throws IOException {
		String input;
		int orderID;
	
		System.out.println("Please enter Order ID:");
		input = sc.nextLine().trim();  // Read the entire line of input
	
		if (input.isEmpty()) {
			throw new IllegalArgumentException("No input provided");
		}
	
		try {
			orderID = Integer.parseInt(input);  // Try to parse the integer
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Error! Invalid input. Order ID must be a number.");
		}
	
		@SuppressWarnings("unchecked")
		List<OrderLine> allOrderLines = OrderLineTextDB.readOrderLine("orderline.txt");//test
	
		System.out.println("Order details for order ID " + orderID);
		boolean exists = false;
		for (OrderLine orderLine : allOrderLines) {
			if (orderLine.getOrderID() == orderID) {
				exists = true;
				System.out.printf("Name: %-20s Quantity: %-10d Customisation: %s\n", orderLine.getName(), orderLine.getQuantity(), orderLine.getCustomisation());
			}
		}
	
		if (!exists) {
			throw new IllegalArgumentException("Error! Order ID does not exist");
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

