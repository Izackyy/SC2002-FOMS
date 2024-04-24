package Stores;

public class OrderLine {
	
	private int orderID;
	private String name;
	private int quantity;
	
	public OrderLine(int orderID, String name, int quantity)
	{
		this.orderID = orderID;
		this.name = name;
		this.quantity = quantity;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getOrderID()
	{
		return orderID;
	}
	
	public boolean equals(Object o)
	{
		if (o instanceof OrderLine);
		{
			OrderLine ol = (OrderLine)o;
			return (getName().equals(ol.getName()));
		}
	}

}
