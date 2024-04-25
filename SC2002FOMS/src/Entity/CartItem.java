package Entity;

import Stores.MenuItem;

public class CartItem {
	
	private MenuItem item;
	private int quantity;
	private double price;
	private String customisation;
	
	public CartItem(MenuItem menuItem, int quantity, String customisation)
	{
		this.item = menuItem;
		this.quantity = quantity;
		this.price = menuItem.getPrice();
		this.customisation = customisation;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
	public MenuItem getItem()
	{
		return item;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public double getPrice()
	{
		return price;
	}

	public String getCustomisation()
	{
		return customisation;
	}

}
