package Stores;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Enums.BranchStatus;

public class BranchTextDB {

	public static final String SEPARATOR = "|";

	// an example of reading
	public static ArrayList readBranchList(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList alr = new ArrayList();

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","

			String name = star.nextToken().trim(); // first token
			String location = star.nextToken().trim(); // second token
			int staffQuota = Integer.parseInt(star.nextToken().trim()); // third token
			BranchStatus branchStatus = Enums.BranchStatus.valueOf(star.nextToken().trim()); // fourth token
			// create MenuItem object from file data
			Branch branch = new Branch(name, location, staffQuota, branchStatus);
			// add to Professors list
			alr.add(branch);
		}

		return alr;
	}

	// an example of saving
	public static void saveBranch(String filename, List al) throws IOException {
		List alw = new ArrayList();// to store MenuItem data

		for (int i = 0; i < al.size(); i++) {
			Branch branch = (Branch) al.get(i);
			StringBuilder st = new StringBuilder();
			st.append(branch.getName().trim());
			st.append(SEPARATOR);
			st.append(branch.getLocation().trim()); // this is a double
			st.append(SEPARATOR);
			st.append(branch.getStaffQuota()); // this is a double
			alw.add(st.toString());
		}
		write(filename, alw);
	}

	/** Write fixed content to the given file. */
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

}
