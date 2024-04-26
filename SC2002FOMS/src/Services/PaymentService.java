package Services;

import java.util.Scanner;
import java.io.IOException;
import java.util.List;

import Stores.Payment;
import Stores.PaymentTextDB;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */
/**
 * PaymentService class allows the staff to select a payment method.
 */
public class PaymentService {

	private static Payment payment;
	/**
	 * Starts the payment process.
	 * 
	 * @throws IOException If an error occurs during file operations.
	 */
	public static void start() throws IOException {

		int choice;
		Scanner sc = new Scanner(System.in);

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
