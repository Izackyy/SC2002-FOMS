package Stores;

import Interfaces.IPayment;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */
/**
 * Represents a payment method.
 */
public class Payment implements IPayment {

	private String name;
	/**
	 * Constructor for creating a payment method.
	 * @param name The name of the payment method.
	 */
	public Payment(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Payment name cannot be null or empty");
		}
		this.name = name;
	}
	/**
	 * Processes the payment.
	 */
	@Override
	public void processPayment() 
	{
		System.out.println("Processing " + this.getName() + " payment...");

		System.out.println("Successful");
	}

	
	/** 
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Checks if two payment methods are equal.
	 * @param o The payment method to compare.
	 * @return True if the payment methods are equal, false otherwise.
	 */
	public boolean equals(Object o) {
		if (o instanceof Payment)
			; // from the course site
		{
			Payment m = (Payment) o;
			return (getName().equals(m.getName()));
		}
	}
}
