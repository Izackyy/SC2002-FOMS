package Stores;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Enums.Gender;
import Enums.OrderStatus;
import Enums.Role;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */

/**
 * StaffTextDB is a utility class that reads and writes Staff data to and from a text file.
 */

public class StaffTextDB {
	public static final String SEPARATOR = "|";

	// an example of reading
	/**
	 * Reads the Staff data from the specified file and returns a list of Staff
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static ArrayList readStaff(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList alr = new ArrayList();// to store staff data into stafflist

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","

			String name = star.nextToken().trim(); // first token
			String loginID = star.nextToken().trim(); // second token
			Role role = Enums.Role.valueOf(star.nextToken().trim());
			// String role = star.nextToken().trim();
			// String gender = star.nextToken().trim();
			Gender gender = Enums.Gender.valueOf(star.nextToken().trim());
			int age = Integer.parseInt(star.nextToken().trim());
			String branch = star.nextToken().trim();
			String password = star.nextToken().trim();
			// create staff object from file data
			Staff staff = new Staff(name, loginID, role, gender, age, branch, password);
			// add to staff list
			alr.add(staff);
		}

		return alr;
	}

	// an example of saving
	/**
	 * Writes the Staff data to the specified file.
	 * 
	 * @param filename
	 * @param al
	 * @throws IOException
	 */
	public static void saveStaff(String filename, List al) throws IOException {
		List alw = new ArrayList();// to store MenuItem data

		for (int i = 0; i < al.size(); i++) {
			Staff staff = (Staff) al.get(i);
			StringBuilder st = new StringBuilder();
			st.append(staff.getName().trim());
			st.append(SEPARATOR);
			st.append(staff.getLoginID().trim());
			st.append(SEPARATOR);
			st.append(staff.getRole());
			st.append(SEPARATOR);
			st.append(staff.getGender());
			st.append(SEPARATOR);
			st.append(staff.getAge());
			st.append(SEPARATOR);
			st.append(staff.getBranch().trim());
			st.append(SEPARATOR);
			st.append(staff.getPassword().trim());
			alw.add(st.toString());
		}
		write(filename, alw);
	}
	// an example of adding
	/**
	 * Adds a Staff to the specified file.
	 * 
	 * @param filename
	 * @param staff
	 * @throws IOException
	 */
	public static void addStaff(String filename, Staff staff) throws IOException {
		List al = readStaff(filename);
		al.add(staff);
		saveStaff(filename, al);
	}
	/**
	 * Removes a Staff from the specified file.
	 * 
	 * @param filename
	 * @param staff
	 * @throws IOException
	 */
	public static void removeStaff(String filename, Staff staff) throws IOException {
		List al = readStaff(filename);
		al.remove(staff);
		saveStaff(filename, al);
	}
	/**
	 * Updates a Staff in the specified file.
	 * 
	 * @param filename
	 * @param oldStaff
	 * @param newStaff
	 * @throws IOException
	 */
	public static void updateStaff(String filename, Staff oldStaff, Staff newStaff) throws IOException {
		List al = readStaff(filename);
		if (al.contains(oldStaff)) {
			int index = al.indexOf(oldStaff);
			al.set(index, newStaff);
			saveStaff(filename, al);
		} else {
			System.out.println("Staff not found.");
		}
	}

	/** Write fixed content to the given file. */
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
	/**
	 * Reads the contents of the given file.
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
	 * Prints the Staff list from the specified file.
	 * 
	 * @param filename
	 * @throws IOException
	 */
	public static void printStaffList(String filename) throws IOException {
		System.out.println("Staff List: ");
		List<Staff> staffs = readStaff(filename);
		int c = 1;
		for (Staff s : staffs) {
			//System.out.println(c + ")" + "Name: " + s.getName() + ", LoginID:" + s.getLoginID() + ", Role:"
					//+ s.getRole() + ", Branch:" + s.getBranch());
			
			System.out.printf(c + ") Name: %-20s LoginID: %-20s Role: %-20s Branch: %s\n", s.getName(), s.getLoginID(), s.getRole(), s.getBranch());
			c++;
		}
	}

}