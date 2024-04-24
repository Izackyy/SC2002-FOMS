package Entity;

import Stores.MenuItem;

public class CartItem {
	
	private MenuItem item;
	private int quantity;
	private double price;
	
	public CartItem(MenuItem menuItem, int quantity)
	{
		this.item = menuItem;
		this.quantity = quantity;
		this.price = menuItem.getPrice();
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

}
