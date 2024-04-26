package Stores;

import Enums.Availability;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */

/**
 * Represents a menu item in a branch.
 */
public class MenuItem {
	private String name;
	private double price;
	private String branch;
	private String category;
	private String description;
	private Availability availability;

	/**
	 * Constructor for creating a menu item.
	 * 
	 * @param name        The name of the menu item.
	 * @param price       The price of the menu item.
	 * @param branch      The branch of the menu item.
	 * @param category    The category of the menu item.
	 * @param description The description of the menu item.
	 */
	public MenuItem(String name, double price, String branch, String category, String description) {
		this.name = name;
		this.price = price;
		this.branch = branch;
		this.category = category;
		this.description = description;
		this.availability = Availability.AVAILABLE; // everytime a new item is added, itll auto set to available
	}

	/**
	 * Constructor for creating a menu item.
	 * 
	 * @param name         The name of the menu item.
	 * @param price        The price of the menu item.
	 * @param branch       The branch of the menu item.
	 * @param category     The category of the menu item.
	 * @param description  The description of the menu item.
	 * @param availability The availability of the menu item.
	 */
	public MenuItem(String name, double price, String branch, String category, String description,
			Availability availability) {
		this.name = name;
		this.price = price;
		this.branch = branch;
		this.category = category;
		this.description = description;
		this.availability = availability;
	}

	/**
	 * Sets the name of the menu item.
	 * 
	 * @param name The name of the menu item.
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the price of the menu item.
	 * 
	 * @param price The price of the menu item.
	 */
	public void setPrice(double price) {
		if (price < 0) {
			System.out.println("Invalid input! Price cannot be negative!");
		} else
			this.price = price;
	}

	/**
	 * Sets the branch of the menu item.
	 * 
	 * @param branch The branch of the menu item.
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}

	/**
	 * Sets the category of the menu item.
	 * 
	 * @param category The category of the menu item.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Sets the description of the menu item.
	 * 
	 * @param description The description of the menu item.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the availability of the menu item.
	 * 
	 * @param availability The availability of the menu item.
	 */
	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	/**
	 * Gets the name of the menu item.
	 * 
	 * @return The name of the menu item.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the price of the menu item.
	 * 
	 * @return The price of the menu item.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Gets the branch of the menu item.
	 * 
	 * @return The branch of the menu item.
	 */
	public String getBranch() {
		return branch;
	}

	/**
	 * Gets the category of the menu item.
	 * 
	 * @return The category of the menu item.
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Gets the description of the menu item.
	 * 
	 * @return The description of the menu item.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the availability of the menu item.
	 * 
	 * @return The availability of the menu item.
	 */
	public Availability getAvailability() {
		return availability;
	}

	/**
	 * Returns a string representation of the menu item.
	 * 
	 * @return A string representation of the menu item.
	 */
	public boolean equals(Object o) {
		if (o instanceof MenuItem)
			;
		{
			MenuItem m = (MenuItem) o;
			return (getName().equals(m.getName()));
		}
	}

}
