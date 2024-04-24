package Entity;

import Interfaces.IPayment;

public class CreditDebit extends Payment{

	@Override
	public void processPayment() {
		
		System.out.println("Processing credit/debit card payment...");
		
		System.out.println("Successful");
		
	}

}
