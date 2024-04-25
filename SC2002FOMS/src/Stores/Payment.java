package Stores;

import Interfaces.IPayment;

public class Payment implements IPayment {

	private String name;

	public Payment(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Payment name cannot be null or empty");
		}
		this.name = name;
	}

	@Override
	public void processPayment() {

		System.out.println("Processing " + this.getName() + " payment...");

		System.out.println("Successful");

	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object o) {
		if (o instanceof Payment)
			;
		{
			Payment m = (Payment) o;
			return (getName().equals(m.getName()));
		}
	}
}
