package Services;

import java.util.Scanner;

import Entity.Cash;
import Entity.CreditDebit;
import Entity.Epayment;
import Entity.Payment;

public class PaymentService extends Payment{
	
	private static Payment payment;
	
	public static void start()
	{
		int selection;
		System.out.println("Please select payment method");
		System.out.println("1) Cash");
		System.out.println("2) Credit/Debit");
		System.out.println("3) Paypal");
		
		Scanner sc = new Scanner(System.in);
		selection = sc.nextInt();
		sc.nextLine(); //input buffer
		
		switch(selection)
		{
			case(1):
				payment = new Cash();
				break;
			case(2):
				payment = new CreditDebit();
				break;
			case(3):
				payment = new Epayment();
				break;
		}
		
		payment.processPayment();
	}

}
