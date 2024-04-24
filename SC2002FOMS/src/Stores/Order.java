package Stores;

import Enums.OrderStatus;

// refering to the datas when viewing new orders - staff and manager

public class Order {
	
	private int orderID;
	
	private OrderStatus orderStatus;
	
	private String branch;
	
	// date and time?
	public Order(int orderID, String branch, OrderStatus orderStatus)
	{
		this.orderID = orderID;
		this.branch = branch;
		this.orderStatus = orderStatus; 
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
	
	public boolean equals(Object o)
	{
		if (o instanceof Order);
		{
			Order order = (Order)o;
			return getOrderID() == order.getOrderID();
		}
	}

	public void cancelOrder(){

		
	}

}
