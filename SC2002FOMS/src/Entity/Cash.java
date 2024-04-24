package Entity;

import Interfaces.IPayment;

public class Cash extends Payment {

	@Override
	public void processPayment() {

		System.out.println("Processing cash payment...");
		
		System.out.println("Successful");
		
	}

}
