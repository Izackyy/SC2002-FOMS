package Entity;

import Interfaces.IPayment;

public class Payment implements  IPayment{

	@Override
	public void processPayment() {
		
		System.out.println("Default payment");
		
	}

}
