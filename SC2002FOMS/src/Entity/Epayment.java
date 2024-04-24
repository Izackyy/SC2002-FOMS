package Entity;

import Interfaces.IPayment;

public class Epayment extends Payment{

	@Override
	public void processPayment() {
		
		System.out.println("Processing Paypal payment...");
		
		System.out.println("Successful");
		
	}

}
