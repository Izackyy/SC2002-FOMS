package Stores;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */
/*
 * PaymentTextDB is a utility class that reads and writes Payment data to and from
 * a text file.
 */

public class PaymentTextDB {
	public static final String SEPARATOR = "|";

	// an example of reading
	/**
	 * Reads the Payment data from the specified file and returns a list of Payment
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<Payment> readPaymentType(String filename) throws IOException {
		ArrayList<String> stringArray = (ArrayList<String>) read(filename);
		ArrayList<Payment> alr = new ArrayList<>();

		for (String line : stringArray) {
			// Trim leading and trailing whitespace from the line
			String name = line.trim();

			// If the name is not empty, create a Payment object
			if (!name.isEmpty()) {
				Payment payment = new Payment(name);
				alr.add(payment);
			}
		}
		return alr;
	}

	// an example of saving
	/**
	 * Writes the Payment data to the specified file.
	 * 
	 * @param filename
	 * @param al
	 * @throws IOException
	 */
	public static void savePaymentType(String filename, List al) throws IOException {
		List alw = new ArrayList();

		for (int i = 0; i < al.size(); i++) {
			Payment payment = (Payment) al.get(i);
			StringBuilder st = new StringBuilder();
			st.append(payment.getName().trim());
			alw.add(st.toString());
		}
		write(filename, alw);
	}
	// an example of adding
	/**
	 * Adds a Payment object to the specified file.
	 * 
	 * @param filename
	 * @param payment
	 * @throws IOException
	 */
	public static void addPaymentType(String filename, Payment payment) throws IOException {
		List al = readPaymentType(filename);
		al.add(payment);
		savePaymentType(filename, al);
	}
	/**
	 * Removes a Payment object from the specified file.
	 * 
	 * @param filename
	 * @param payment
	 * @throws IOException
	 */
	public static void removePaymentType(String filename, Payment payment) throws IOException {
		List al = readPaymentType(filename);
		al.remove(payment);
		savePaymentType(filename, al);
	}

	/** Write fixed content to the given file. */
	// an example of writing
	
	/**
	 * Writes the given data to the specified file.
	 * 
	 * @param fileName
	 * @param data
	 * @throws IOException
	 */
	public static void write(String fileName, List data) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));

		try {
			for (int i = 0; i < data.size(); i++) {
				out.println((String) data.get(i));
			}
		} finally {
			out.close();
		}
	}

	/** Read the contents of the given file. */
	// an example of reading

	/**
	 * Reads the contents of the specified file and returns a list of strings.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static List read(String fileName) throws IOException {
		List data = new ArrayList();
		Scanner scanner = new Scanner(new FileInputStream(fileName));
		try {
			while (scanner.hasNextLine()) {
				data.add(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		return data;
	}
	/**
	 * Prints the Payment methods from the specified file.
	 * 
	 * @param filename
	 * @throws IOException
	 */
	public static void printPaymentMethod(String filename) throws IOException {
		System.out.println("Payment Methods: \n");
		List<Payment> payments = readPaymentType(filename);
		int c = 1;
		for (Payment p : payments) {
			System.out.println(c + ") " + p.getName());
			c++;
		}
	}
	/**
	 * Compares the input string to the names in the specified file.
	 * 
	 * @param filename
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static boolean compareName(String filename, String input) throws IOException {
		// Read names from the text file
		List<Payment> names = readPaymentType(filename);

		// Compare the input string to each name
		for (Payment p : names) {
			if (p.getName().equalsIgnoreCase(input)) {
				return true; // Match found
			}
		}

		return false; // No match found
	}
}
