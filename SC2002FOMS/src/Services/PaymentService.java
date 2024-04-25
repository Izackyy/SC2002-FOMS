package Services;

import java.util.Scanner;
import java.io.IOException;
import java.util.List;

import Stores.Payment;
import Stores.PaymentTextDB;

public class PaymentService {

	private static Payment payment;

	public static void start() throws IOException {

		int choice;
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Select a payment type: ");
			List<Payment> payments = PaymentTextDB.readPaymentType("payment.txt");
			int c = 1;
			for (Payment p : payments) {
				System.out.println(c + ") " + p.getName());
				c++;
			}
			choice = sc.nextInt();
			Payment selectedPayment = payments.get(choice - 1);
			selectedPayment.processPayment();
		}
	}
}

/*
 * int selection;
 * System.out.println("Please select payment method");
 * System.out.println("1) Cash");
 * System.out.println("2) Credit/Debit");
 * System.out.println("3) Paypal");
 * 
 * Scanner sc = new Scanner(System.in);
 * selection = sc.nextInt();
 * sc.nextLine(); //input buffer
 * 
 * switch(selection)
 * {
 * case(1):
 * payment = new Cash();
 * break;
 * case(2):
 * payment = new CreditDebit();
 * break;
 * case(3):
 * payment = new Epayment();
 * break;
 * }
 * 
 * payment.processPayment();
 * }
 * 
 * }
 */
