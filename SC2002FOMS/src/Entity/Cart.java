package Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Stores.AuthStore;
import Stores.MenuItem;
import Stores.MenuTextDB;

public class Cart {
	
	private static int totalItems;
	
	private static ArrayList<CartItem> cart;
	private static final Scanner sc = new Scanner(System.in);
	
	public Cart() {
        cart = new ArrayList<>(); // Initialize the cart ArrayList
    }
	
	public void addItem(String branch) throws IOException
	{
		String name;
		int quantity;
		MenuItem item = null;
		System.out.println("Item name");
		name = sc.nextLine();	

		List<MenuItem> al = MenuTextDB.readMenuItem("menu.txt");//test
        for (MenuItem menuitem : al)
        {
        	if (menuitem.getName().equals(name) && menuitem.getBranch().equals(branch))
        	{
        		item = menuitem;
        		break;
        	}
        }
        
        if (item == null) {
            System.out.println("Item does not exist");
            return;
        }
        
        System.out.println("Quantity");
        quantity = sc.nextInt();
        sc.nextLine(); //input buffer
        
        CartItem newItem = new CartItem(item,quantity);
        cart.add(newItem); 
        
        System.out.println("Item added to cart.");
        totalItems++;
       
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
		    System.out.println("Cart is empty. Nothing to remove.");
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

}
