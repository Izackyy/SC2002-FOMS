package Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Stores.AuthStore;
import Stores.MenuItem;
import Stores.MenuTextDB;
import Stores.OrderLine;
import Stores.OrderLineTextDB;
import Exceptions.ItemNotFoundException;
import java.util.InputMismatchException;

public class Cart {
	
	private static int totalItems; // i think this is not needed already
	
	private static ArrayList<CartItem> cart;
	private static final Scanner sc = new Scanner(System.in);
	
	public Cart() {
        cart = new ArrayList<>(); // Initialize the cart ArrayList
    }
	
	public void addItem(String branch) {
		String name;
		int quantity;
		MenuItem item = null;
		System.out.println("Item name");
	
		try {
			name = sc.nextLine();
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
			sc.nextLine(); // Clear the input buffer
	
			CartItem newItem = new CartItem(item, quantity);
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
	
	
	public void removeItem()
	{
		if (cart.isEmpty()) 
		{
		    System.out.println("Cart is empty. Nothing to remove.");
		    return;
		}	
			
		System.out.println("Items in the cart:");
		for (int i = 0; i < cart.size(); i++) 
		{
		    System.out.println((i + 1) + ". " + cart.get(i).getQuantity() + "x " + cart.get(i).getItem().getName());
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
	
	public void editItem()
	{
		int newQuantity;
		
		if (cart.isEmpty()) 
		{
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
	    
	    CartItem editItem = cart.get(selection -1);
	    
	    System.out.println("New quantity:");
	    
	    newQuantity = sc.nextInt();
	    sc.nextLine(); // input buffer
	    
	    editItem.setQuantity(newQuantity);
	    
	    System.out.println("Quantity updated.");
	}
	
	public void printCart() {
		int i=1;
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Items in the cart:");
            for (CartItem item : cart) {
                System.out.println(i + ") " +item.getQuantity() + "x " + item.getItem().getName());
                i++;
            }
        }
    }
	
	public double calculateCart() {
		System.out.println("Items in the cart:");
		double total = 0;
        for (CartItem item : cart) 
        {
        	total += ((double)item.getQuantity() * item.getPrice());
        }
        return total;
    }
	
	public boolean checkItems()
	{
		if (cart.isEmpty()) 
		{
            return false;
		}
		return true;
            
	}
	
	public int getTotalItems()
	{
		return totalItems;
	}
	
	public void addOrderline(int orderID) throws IOException
	{
		for (CartItem item : cart) {
			OrderLine orderLine = new OrderLine(orderID, item.getItem().getName(),item.getQuantity());
            OrderLineTextDB.addOrder("orderline.txt", orderLine);
        }
	}

}
