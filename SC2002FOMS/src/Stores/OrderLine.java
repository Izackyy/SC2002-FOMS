package Stores;

public class OrderLine {
	
	private int orderID;
	private String name;
	private int quantity;
	private String customisation;
	
	public OrderLine(int orderID, String name, int quantity, String customisation)
	{
		this.orderID = orderID;
		this.name = name;
		this.quantity = quantity;
		this.customisation = customisation;
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

	public String getCustomisation()
	{
		return customisation;
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
