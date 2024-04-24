package Stores;

import Enums.OrderStatus;

// refering to the datas when viewing new orders - staff and manager

public class Order {
	
	private int orderID;
	
	private OrderStatus orderStatus;
	
	private String branch;
	
	// date and time?
	public Order(int orderID, String branch)
	{
		this.orderID = orderID;
		this.orderStatus = OrderStatus.PROCESSING; // will always start off like this until changed by staff/manager
		this.branch = branch;
	}
	
	public void setOrderStatus(OrderStatus orderStatus)
	{
		this.orderStatus = orderStatus;
	}
	
	public int getOrderID()
	{
		return orderID;
	}
	
	public String getBranch()
	{
		return branch;
	}
	
	public OrderStatus getOrderStatus()
	{
		return orderStatus;
	}

}
