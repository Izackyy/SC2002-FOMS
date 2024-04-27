package Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Stores.MenuItem;
import Stores.MenuTextDB;
import Stores.OrderLine;
import Stores.OrderLineTextDB;
import Exceptions.ItemNotFoundException;
import java.util.InputMismatchException;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */

	/**
 	* The {@code Cart} class represents a shopping cart containing items selected by the customer.
 	* It includes methods for adding, removing, and editing items, as well as calculating the total price.
 	*/

public class Cart {

	private static int totalItems; // i think this is not needed already

	private static ArrayList<CartItem> cart;
	private static final Scanner sc = new Scanner(System.in);
	

	public Cart() {
		cart = new ArrayList<>(); // Initialize the cart ArrayList
	}
	/**
     * Adds a menu item to the cart after validating its existence based on the provided name and branch.
     * Prompts the user for item details such as name, quantity, and any customizations.
     *
     * @param branch The branch identifier to match the menu item.
     * @throws IOException If an error occurs during reading the menu item from the file.
     * @throws ItemNotFoundException If the item with the specified name does not exist.
     * @throws InputMismatchException If an incorrect format is used for quantity.
     */

	public void addItem(String branch) {
		String name;
		int quantity;
		String customisation;
		MenuItem item = null;
		System.out.println("Item name");

		try {
			name = sc.nextLine().toUpperCase();
			@SuppressWarnings("unchecked")
			List<MenuItem> al = MenuTextDB.readMenuItem("menu.txt");
			for (MenuItem menuitem : al) {
				if (menuitem.getName().equalsIgnoreCase(name) && menuitem.getBranch().equals(branch)) {
					item = menuitem;
					break;
				}
			}

			if (item == null) {
				throw new ItemNotFoundException("Item does not exist");
			}

			System.out.println("Quantity");
			quantity = sc.nextInt();
			while (quantity<=0)
					{
						System.out.println("Invalid input! Quantity cannot be negative or 0!");
						quantity = sc.nextInt();
					}
			sc.nextLine(); // Clear the input buffer

			System.out.println("Any special request? (Customisation): ");
			customisation = sc.nextLine();

			CartItem newItem = new CartItem(item, quantity,customisation);
			cart.add(newItem);

			System.out.println("Item added to cart.");
			totalItems++;
		} catch (InputMismatchException ime) {
			System.out.println("Please enter a valid number for quantity.");
			sc.next(); // Clear the scanner buffer
		} catch (ItemNotFoundException infe) {
			System.out.println(infe.getMessage());
		} catch (IOException ioe) {
			System.out.println("An error occurred when trying to read the menu: " + ioe.getMessage());
		} catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
		}
	}
	/**
     * Removes a selected item from the cart. Prompts the user to select an item by number.
     * Displays an error message if the cart is empty or if an invalid number is entered.
     */

	public void removeItem() {
		if (cart.isEmpty()) {
			System.out.println("Cart is empty. Nothing to remove.");
			return;
		}

		System.out.println("Items in the cart:");
		for (int i = 0; i < cart.size(); i++) {
			System.out.println((i + 1) + ") " + cart.get(i).getQuantity() + "x " + cart.get(i).getItem().getName());
		}

		System.out.println("Number of the item you want to remove:");
		int selection = sc.nextInt();
		sc.nextLine(); // Input buffer

		if (selection < 1 || selection > cart.size()) {
			System.out.println("Invalid selection.");
			return;
		}

		cart.remove(selection - 1);
		System.out.println("Item removed from cart.");
		totalItems--;

	}
	/**
     * Edits the quantity of a selected item in the cart. Prompts the user to select an item by number
     * and to input a new quantity. Displays an error if the cart is empty or an invalid number is selected.
     */

	public void editItem() {
		int newQuantity;

		if (cart.isEmpty()) {
			System.out.println("Cart is empty. Nothing to edit.");
			return;
		}

		System.out.println("======Items in the cart======");
		printCart();

		System.out.println("Number of the item you want to edit:");
		int selection = sc.nextInt();
		sc.nextLine(); // Input buffer

		if (selection < 1 || selection > cart.size()) {
			System.out.println("Invalid selection.");
			return;
		}

		CartItem editItem = cart.get(selection - 1);

		System.out.println("New quantity:");

		newQuantity = sc.nextInt();
		while (newQuantity<=0)
					{
						System.out.println("Invalid input! Quantity cannot be negative or 0!");
						newQuantity = sc.nextInt();
					}
		sc.nextLine(); // input buffer

		editItem.setQuantity(newQuantity);

		System.out.println("Quantity updated.");
	}
	/**
     * Prints all items in the cart with their quantities and names.
     * Displays a message if the cart is empty.
     */

	public void printCart() {
		int i = 1;
		if (cart.isEmpty()) {
			System.out.println("Cart is empty.");
		} else {
			System.out.println("Items in the cart:");
			for (CartItem item : cart) {
				System.out.printf(i + ") x%-1d %-20s Customisation:: %s\n", item.getQuantity(), item.getItem().getName(), item.getCustomisation());
				i++;
			}
		}
	}
	/**
     * Calculates the total price of all items in the cart based on their quantities and prices.
     * Outputs each item's details to the console during calculation.
     *
     * @return double The total price of all items in the cart.
     */
	public double calculateCart() {
		System.out.println("Items in the cart:");
		double total = 0;
		for (CartItem item : cart) {
			total += ((double) item.getQuantity() * item.getPrice());
		}
		return total;
	}
	/**
     * Checks if the cart contains any items.
     *
     * @return boolean True if the cart has items, false otherwise.
     */

	public boolean checkItems() {
		if (cart.isEmpty()) {
			return false;
		}
		return true;

	}
	/**
     * Gets the total number of items in the cart.
     *
     * @return int The total count of items in the cart.
     */

	public int getTotalItems() {
		return totalItems;
	}
	/**
     * Adds all items in the cart as order lines to a specified order ID.
     * Creates an OrderLine for each CartItem and writes it to the order line database.
     *
     * @param orderID The identifier of the order to which the items will be added.
     * @throws IOException If an error occurs during writing to the order line file.
     */

	public void addOrderline(int orderID) throws IOException {
		for (CartItem item : cart) {
			OrderLine orderLine = new OrderLine(orderID, item.getItem().getName(), item.getQuantity(),item.getCustomisation());
			OrderLineTextDB.addOrder("orderline.txt", orderLine);
		}
	}
}
