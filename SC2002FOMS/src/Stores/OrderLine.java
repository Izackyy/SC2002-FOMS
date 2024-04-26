package Stores;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */

/**
 * Represents an order line in the system.
 */

public class OrderLine {
	
	private int orderID;
	private String name;
	private int quantity;
	private String customisation;
	/**
	 * Constructor for creating an order line.
	 * 
	 * @param orderID The ID of the order.
	 * @param name The name of the item.
	 * @param quantity The quantity of the item.
	 * @param customisation The customisation of the item.
	 */
	
	public OrderLine(int orderID, String name, int quantity, String customisation)
	{
		this.orderID = orderID;
		this.name = name;
		this.quantity = quantity;
		this.customisation = customisation;
	}
	/**
	 * Gets the quantity of the item.
	 * @return The quantity of the item.
	 */
	
	public int getQuantity()
	{
		return quantity;
	}
	/**
	 * Gets the name of the item.
	 * @return The name of the item.
	 */
	
	public String getName()
	{
		return name;
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
	 * Gets the customisation of the item.
	 * @return The customisation of the item.
	 */
	public String getCustomisation()
	{
		return customisation;
	}
	/**
	 * Sets the quantity of the item.
	 * @param quantity The new quantity.
	 */
	public boolean equals(Object o)
	{
		if (o instanceof OrderLine);
		{
			OrderLine ol = (OrderLine)o;
			return (getName().equals(ol.getName()));
		}
	}

}
