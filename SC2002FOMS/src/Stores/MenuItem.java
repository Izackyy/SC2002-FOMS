package Stores;

import java.io.Serializable;

public class MenuItem {
	private String name;
	private double price;
	private String branch;
	private String category;
	private String description;
	// private boolean availabilty;
	
	
	public MenuItem(String name, double price, String branch, String category)
	{
		this.name = name;
		this.price = price;
		this.branch = branch;
		this.category = category;
		this.description = "NA";
	}
	
	public MenuItem(String name, double price, String branch, String category, String description)
	{
		this.name = name;
		this.price = price;
		this.branch = branch;
		this.category = category;
		this.description = description;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public void setBranch(String branch)
	{
		this.branch = branch;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public String getBranch()
	{
		return branch;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public boolean equals(Object o)
	{
		if (o instanceof MenuItem);
		{
			MenuItem m = (MenuItem)o;
			return (getName().equals(m.getName()));
		}
	}
	
}
