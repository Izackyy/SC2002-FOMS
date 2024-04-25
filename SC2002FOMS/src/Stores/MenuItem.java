package Stores;

import Enums.Availability;

public class MenuItem {
	private String name;
	private double price;
	private String branch;
	private String category;
	private String description;
	private Availability availability;
	
	
	public MenuItem(String name, double price, String branch, String category)
{
		this.name = name;
		this.price = price;
		this.branch = branch;
		this.category = category;
		this.description = "NA";
		this.availability = Availability.AVAILABLE; //everytime a new item is added, itll auto set to available
	}
	
	
	public MenuItem(String name, double price, String branch, String category, String description, Availability availability)
{
		this.name = name;
		this.price = price;
		this.branch = branch;
		this.category = category;
		this.description = description;
		this.availability = availability;
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
	
	public void setAvailability(Availability availability)
	{
		this.availability = availability;
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
	
	public Availability getAvailability()
	{
		return availability;
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
