package Stores;

import Enums.OrderStatus;

/**
 * Represents an order in the system.
 */

public class Order {
	
	private int orderID;
	
	private OrderStatus orderStatus;
	
	private String branch;
	
	/**
	 * Constructor for creating an order.
	 * 
	 * @param orderID The ID of the order.
	 * @param branch The branch of the order.
	 * @param orderStatus The status of the order.
	 */
	public Order(int orderID, String branch, OrderStatus orderStatus)
	{
		this.orderID = orderID;
		this.branch = branch;
		this.orderStatus = orderStatus; 
	}
	/**
	 * Sets the order status.
	 * 
	 * @param orderStatus The status of the order.
	 */
	public void setOrderStatus(OrderStatus orderStatus)
	{
		this.orderStatus = orderStatus;
	}
	/**
	 * Gets the ID of the order.
	 * @return The ID of the order.
	 */
	public int getOrderID()
	{
		return orderID;
	}
	/**
	 * Gets the branch of the order.
	 * @return The branch of the order.
	 */
	public String getBranch()
	{
		return branch;
	}
	/**
	 * Gets the status of the order.
	 * @return The status of the order.
	 */
	public OrderStatus getOrderStatus()
	{
		return orderStatus;
	}
	/**
	 * Checks if the order is equal to another object.
	 * 
	 * @param o The object to compare.
	 * @return True if the order is equal to the object, false otherwise.
	 */
	public boolean equals(Object o)
	{
		if (o instanceof Order);
		{
			Order order = (Order)o;
			return getOrderID() == order.getOrderID();
		}
	}
	/**
	 * Returns a string representation of the order.
	 * 
	 * @return A string representation of the order.
	 */
	public void cancelOrder(){

		
	}

}
