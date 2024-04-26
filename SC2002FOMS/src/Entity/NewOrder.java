package Entity;

import java.io.IOException;

import java.util.Random;
import java.util.Scanner;

import Enums.DiningOption;
import Enums.OrderStatus;
import Services.MenuDisplay;
import Services.PaymentService;
import Stores.Branch;
import Stores.Order;
import Stores.OrderTextDB;

public class NewOrder {

	/**
 	* Represents a new order made by a customer.
 	*/

	// to include time and date

	private Branch branch;

	private Cart cart;

	private DiningOption option;

	public static int orderID;

	private static final Scanner sc = new Scanner(System.in);
	private static final Random random = new Random();

	// private timeOrder;

	public NewOrder(Branch branch) {

		cart = new Cart();
		option = DiningOption.TAKEAWAY;
		this.branch = branch;
	}

	public static void startOrder(Branch branch) throws IOException {
		String wantReceipt;
		NewOrder order = new NewOrder(branch);

		boolean confirm = false;
		int selection;

		do {
			System.out.println("======Cart Actions======");
			System.out.println("1) Add Item to cart");
			System.out.println("2) Remove Item from cart");
			System.out.println("3) Edit Item in cart");
			System.out.println("4) Confirm cart");

			selection = sc.nextInt();

			switch (selection) {
				case (1):
					MenuDisplay.printMenuItem(branch.getName());
					order.cart.addItem(branch.getName());
					System.out.println("======Updated Cart======");
					order.cart.printCart();
					break;
				case (2):
					order.cart.removeItem();
					System.out.println("======Updated Cart======");
					order.cart.printCart();
					break;
				case (3):
					order.cart.editItem();
					System.out.println("======Updated Cart======");
					order.cart.printCart();
					break;
				case (4):
					if (order.cart.checkItems() == false) {
						System.out.println("Cart is empty. Please add items.");
					} else {
						confirm = true;
					}
			}

		} while (confirm == false);

		System.out.println("Dine-in/Takeaway?");
		System.out.println("<Press 1 for Dine-in or Press 2 for Takeaway>");

		while (selection < 1 || selection > 2) {
			selection = sc.nextInt();
		}
		sc.nextLine(); // input buffer;

		if (selection == 1) {
			order.option = DiningOption.DINE_IN;
		}

		// check out
		PaymentService.start();
		Receipt receipt = new Receipt();
		System.out.println("Do you need a receipt? Y/N");
		wantReceipt = sc.nextLine();
		if (wantReceipt.equalsIgnoreCase("Y")) {
			System.out.println("Generating Receipt...");
			Receipt.printReceipt(order);
		}

		order.orderID = generateRandomOrderID();

		System.out.println("");

		System.out.println("Order ID: " + order.orderID);

		Order storeOrder = new Order(orderID, branch.getName(), OrderStatus.PROCESSING);
		OrderTextDB.addOrder("order.txt", storeOrder);
		order.cart.addOrderline(orderID);
	}

	private static int generateRandomOrderID() {
		return random.nextInt(100000);
	}

	public Branch getBranch() {
		return branch;
	}

	public Cart getCart() {
		return cart;
	}

	public DiningOption getDiningOption() {
		return option;
	}
}