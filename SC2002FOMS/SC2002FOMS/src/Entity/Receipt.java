package Entity;

public class Receipt {
	
	private Cart cart;
	
	public Receipt(Cart cart)
	{
		this.cart = cart;
	}
	
	public static void printReceipt()
	{
		System.out.println("Receipt test..."); //placeholder
		// TO IMPLEMENT
		// display Items, quantity, branch, date and time, order status
	}
}
