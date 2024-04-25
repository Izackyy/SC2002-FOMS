package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Enums.Availability;
import Enums.BranchStatus;
import Enums.Gender;
import Enums.Role;
import Services.MenuDisplay;
import Services.StaffDisplay;
import Services.BranchManager;
import Stores.AuthStore;
import Stores.Branch;
import Stores.BranchTextDB;
import Stores.MenuItem;
import Stores.MenuTextDB;
import Stores.Payment;
import Stores.PaymentTextDB;
import Stores.Staff;
import Stores.StaffTextDB;

public class AdminController extends EmployeeController {

	private static final Scanner sc = new Scanner(System.in);

	public void start() throws IOException {
		int selection;

		do {
			System.out.println("==========Admin's Actions==========");
			System.out.println("|| 1) Edit Staff List            ||"); // just like how you edit menu
			System.out.println("|| 2) Display Staff List         ||");
			System.out.println("|| 3) Promote Staff To Manager   ||");
			System.out.println("|| 4) Transfer Staff/Manager     ||");
			System.out.println("|| 5) Add/Remove Payment Method  ||");
			System.out.println("|| 6) Open/Close branch          ||");
			System.out.println("|| 7) Add Branch                 ||");
			System.out.println("|| 8) Remove branch              ||");
			System.out.println("|| 9) Change Password            ||");
			System.out.println("|| 10) Quit                       ||");
			System.out.println("===================================");

			selection = sc.nextInt();

			switch (selection) {
				case (1):
					editStaffAcc();
					break;
				case (2):
					filterStaff();
					break;
				case (3):
					promoteStaff();
					break;
				case (4):
					transferStaff();
					break;
				case (5):
					editPayment();
					break;
				case (6):
					setBranchStatus();
					break;
				case (7):
					sc.nextLine();// input buffer
					addBranch();
					break;
				case (8):
					sc.nextLine();// input buffer
					removeBranch();
					break;
				case (9):
					changePassword();
				default:
					return;
			}

		} while (selection != 10);
	}

	private static void editStaffAcc() throws IOException {
		// System.out.println("editing staff account");

		String yesNo;
		System.out.println("===========Staff Editor==========");
		System.out.println("|| 1) Add Staff                ||");
		System.out.println("|| 2) Remove Staff             ||");
		System.out.println("|| 3) Edit Staff               ||");
		System.out.println("=================================");

		int editChoice = sc.nextInt();
		sc.nextLine(); // input buffer
		switch (editChoice) {
			case (1):
				System.out.println("Name:");
				String name = sc.nextLine();

				List<Staff> al = StaffTextDB.readStaff("staff.txt");// test
				for (Staff staff : al) {
					if (staff.getName().equalsIgnoreCase(name)) {
						System.out.println("Staff already exists");
						return;
					}
				}

				System.out.println("StaffID:");
				String staffID = sc.nextLine();
				System.out.println("Role: ");
				String sRole = sc.nextLine();

				Role role = null;
				if (sRole.equalsIgnoreCase("M")) {
					role = Role.M;
				} else if (sRole.equalsIgnoreCase("S")) {
					role = Role.S;
				} else {
					role = Role.A;
				}
				System.out.println("Gender: ");
				String sGender = sc.nextLine();
				Gender gender = null;
				if (sGender.equalsIgnoreCase("F")) {
					gender = Gender.F;
				} else {
					gender = Gender.M;
				}
				System.out.println("Age:");
				int age = sc.nextInt();

				int i = 1;
				System.out.println("Branch:");
				System.out.println("======Branch Option======");
				List<Branch> b = BranchTextDB.readBranchList("branch.txt");// test
				for (Branch branch : b) {
					System.out.println(i + ") " + branch.getName());
					i++;
				}

				int selection = sc.nextInt();

				Branch branch = b.get(selection - 1);

				System.out.println("Staff has been successfully added");
				Staff staff = new Staff(name, staffID, role, gender, age, branch.getName());

				StaffTextDB.addStaff("staff.txt", staff);
				break;

			case (2): {
				StaffTextDB.printStaffList("staff.txt");
				System.out.println("Enter Staff Name:");
				String sName = sc.nextLine();

				List<Staff> s = StaffTextDB.readStaff("staff.txt");// test
				Staff toRemove = null;
				for (Staff sf : s) {
					if (sf.getName().equals(sName)) {
						toRemove = sf;
						StaffTextDB.removeStaff("staff.txt", toRemove);
						System.out.println("Staff removed successfully.");
						break;
					}
				}
				if (toRemove == null) {
					System.out.println("Staff does not exist");
				}
				break;
			}
			case (3): { // loginID, password,age
				Staff oldStaff = null;
				System.out.println("========Staff list========");
				StaffTextDB.printStaffList("staff.txt");

				List<Staff> s = StaffTextDB.readStaff("staff.txt");// test

				System.out.println("Select a staff member");
				int choice = sc.nextInt();
				oldStaff = s.get(choice - 1);

				sc.nextLine(); // test input buffer

				System.out.println("Current details of selected Staff:");

				System.out.println("Name: " + oldStaff.getName());
				System.out.println("StaffID: " + oldStaff.getLoginID());
				System.out.println("Password: " + oldStaff.getPassword());
				System.out.println("Age: " + oldStaff.getAge());
				System.out.println("");

				Staff newStaff = new Staff(oldStaff.getName(), oldStaff.getLoginID(), oldStaff.getRole(),
						oldStaff.getGender(), oldStaff.getAge(), oldStaff.getBranch(), oldStaff.getPassword());

				System.out.println("Update StaffID? (Y/N)");
				yesNo = sc.nextLine();
				if (yesNo.equalsIgnoreCase("Y")) {
					System.out.println("New Staff ID:");
					String newID = sc.nextLine();
					newStaff.setLoginID(newID);
					yesNo = "N";
				}
				System.out.println("Update Password? (Y/N)");
				yesNo = sc.nextLine();
				if (yesNo.equalsIgnoreCase("Y")) {
					System.out.println("New password:");
					String newPassword = sc.nextLine();
					newStaff.setPassword(newPassword);
					yesNo = "N";
				}

				System.out.println("Update Age? (Y/N)");
				yesNo = sc.nextLine();
				if (yesNo.equalsIgnoreCase("Y")) {
					System.out.println("New age:");
					int newAge = sc.nextInt();
					newStaff.setAge(newAge);
					yesNo = "N";
				}

				StaffTextDB.updateStaff("staff.txt", oldStaff, newStaff);
				break;
			}
		}
		System.out.println("Staff details have been updated");
		// dont know if you want to print and show updated
	}

	private static void filterStaff() throws IOException {
		int selection;
		int choice;
		int i = 1;
		System.out.println("======Filter staff======");
		System.out.println("1) Branch");
		System.out.println("2) Role");
		System.out.println("3) Gender");
		System.out.println("4) Age");
		System.out.println("5) Display all (no filter)");

		selection = sc.nextInt();

		switch (selection) {
			case (1):
				String branch;
				System.out.println("Select a branch: ");
				List<Branch> al = BranchTextDB.readBranchList("branch.txt");// test
				for (Branch b : al) {
					System.out.println(i + ") " + b.getName());
					i++;
				}

				choice = sc.nextInt();

				branch = al.get(selection - 1).getName();

				StaffDisplay.printStaffList(branch);
				break;
			case (2):
				System.out.println("Select Role");
				System.out.println("1) Staff");
				System.out.println("2) Manager");

				choice = sc.nextInt();

				Role role = (choice == 1) ? Role.S : Role.M;
				StaffDisplay.printStaffList(role);
				break;
			case (3):
				System.out.println("Select gender");
				System.out.println("1) Female");
				System.out.println("2) Male");

				choice = sc.nextInt();

				Gender gender = (choice == 1) ? Gender.F : Gender.M;
				StaffDisplay.printStaffList(gender);
				break;
			case (4): // dont know if we can set a range
			{
				System.out.println("Enter age:");
				choice = sc.nextInt();
				StaffDisplay.printStaffList(selection);
				break;
			}

			case (5):
				StaffTextDB.printStaffList("staff.txt");
				break;
			default:
				System.out.println("Invalid choice.");

		}
	}

	private static void promoteStaff() throws IOException {

		int choice;
		String confirm;

		// select staff
		System.out.println("Select a staff member");
		StaffTextDB.printStaffList("staff.txt");
		List<Staff> staffs = StaffTextDB.readStaff("staff.txt");
		choice = sc.nextInt();
		sc.nextLine();
		Staff selectedStaff = staffs.get(choice - 1);
		Staff oldRole = selectedStaff;
		Staff newRole = oldRole;

		System.out.println("Promote " + selectedStaff.getName() + " ? <Y/N>");
		confirm = sc.nextLine();
		if (confirm.equalsIgnoreCase("Y")) {
			newRole.setRole(Role.M);
		}
		// add input check and cancel if N(?)

		StaffTextDB.updateStaff("staff.txt", oldRole, newRole);
		StaffTextDB.printStaffList("staff.txt");
		System.out.println("Staff branch status updated successfully.\n");

		return;

	}

	public void transferStaff() throws IOException {

		int choice, check;

		// select staff
		StaffTextDB.printStaffList("staff.txt");
		List<Staff> staffs = StaffTextDB.readStaff("staff.txt");
		System.out.println("Select a staff member");
		choice = sc.nextInt();
		Staff selectedStaff = staffs.get(choice - 1);
		Staff oldBranch = selectedStaff;
		Staff newBranch = oldBranch;

		// select branch
		BranchTextDB.printBranch("branch.txt");
		List<Branch> branches = BranchTextDB.readBranchList("branch.txt");
		choice = sc.nextInt();
		Branch b = branches.get(choice - 1);
		newBranch.setBranch(b.getName());

		// add input and branch check
		// check staff
		if (selectedStaff.getRole().equals(Role.S)) {
			if (BranchManager.checkStaffQuota(b)) {
				StaffTextDB.updateStaff("staff.txt", oldBranch, newBranch);
				StaffTextDB.printStaffList("staff.txt");
				System.out.println("Staff transferred successfully.\n");
			}
		}
		// check manager
		else if (selectedStaff.getRole().equals(Role.M)) {
			if (BranchManager.checkManagerQuota(b)) {
				StaffTextDB.updateStaff("staff.txt", oldBranch, newBranch);
				StaffTextDB.printStaffList("staff.txt");
				System.out.println("Staff transferred successfully.\n");
			}
		}
		return;
	}

	private static void editPayment() throws IOException {

		PaymentTextDB.printPaymentMethod("payment.txt");
		System.out.println("===========Payment Editor===========");
		System.out.println("|| 1) Add Payment Method          ||");
		System.out.println("|| 2) Remove Payment Method       ||");
		System.out.println("|| 3) Quit                        ||");
		System.out.println("====================================");
		int Choice = sc.nextInt();
		sc.nextLine(); // input buffer

		do {
			switch (Choice) {
				case (1): {
					System.out.println("Name:");
					String name = sc.nextLine();

					// Check for duplicate payment method names (ignoring case)
					if (PaymentTextDB.compareName("payment.txt", name)) {
						System.out.println("Payment method already exists.");
						return;
					}

					Payment payment = new Payment(name);
					PaymentTextDB.addPaymentType("payment.txt", payment);
					System.out.println("Payment method added successfully.");
					System.out.println("Updated Payment Methods:");
					PaymentTextDB.printPaymentMethod("payment.txt");
					return;
				}

				case (2): {
					System.out.println("Name:");
					String name = sc.nextLine();

					List<Payment> al = PaymentTextDB.readPaymentType("payment.txt");// test
					Payment toRemove = null;
					for (Payment payment : al) {
						if (payment.getName().equalsIgnoreCase(name)) {
							toRemove = payment;
							PaymentTextDB.removePaymentType("payment.txt", toRemove);
							System.out.println("Payment method removed successfully.");
							System.out.println("Updated Payment Methods:");
							PaymentTextDB.printPaymentMethod("payment.txt");
							return;
						}
					}
					if (toRemove == null) {
						System.out.println("Payment method does not exist");
					}
					return;
				}
				default:
					return;

			}
		} while (Choice != 3);
	}

	private static void setBranchStatus() throws IOException {

		int set, choice;

		BranchTextDB.printBranch("branch.txt");
		List<Branch> branches = BranchTextDB.readBranchList("branch.txt");
		choice = sc.nextInt();
		Branch selectedBranch = branches.get(choice - 1);
		Branch oldStatus = selectedBranch;
		Branch newStatus = oldStatus;

		System.out.println("Open/Close Branch");
		System.out.println("<Press 1 to open Branch or Press 0 to close Branch>");

		set = sc.nextInt();
		while (set != 1 && set != 0) {
			System.out.println("Invalid choice! Enter 1 to open or 0 to close the branch.");
			set = sc.nextInt();
		}
		while (!newStatus.setBranchStatus(set)) {
			set = sc.nextInt();
		}

		BranchTextDB.updateBranchStatus("branch.txt", oldStatus, newStatus);

		BranchTextDB.printBranch("branch.txt");
		System.out.println("Branch status updated successfully.\n");

		return;
	}

	public void addBranch() throws IOException {

		System.out.println("Name:");
		String name = sc.nextLine();

		List<Branch> al = BranchTextDB.readBranchList("branch.txt");// test
		for (Branch branch : al) {
			if (branch.getName().equalsIgnoreCase(name)) {
				System.out.println("Branch already exists");
				return;
			}
		}
		// String name, String location, int staffQuota, BranchStatus branchStatus
		System.out.println("Location");
		String location = sc.nextLine();
		System.out.println("Staff Quota:");
		int staffQuota = sc.nextInt();
		sc.nextLine(); // input buffer

		// assumption that every new branch added will be set as open

		System.out.println("Branch has been succesfully added");

		Branch branch = new Branch(name, location, staffQuota, BranchStatus.OPEN);
		BranchTextDB.addBranch("branch.txt", branch);

	}

	public void removeBranch() throws IOException {
		System.out.println("=======Branch List=======");
		List<Branch> al = BranchTextDB.readBranchList("branch.txt");// test

		for (Branch branch : al) {
			System.out.println(branch.getName());
		}
		// BranchTextDB.printBranch("branch.txt");

		System.out.println("Enter Branch Name:");
		String name = sc.nextLine();

		Branch toRemove = null;
		for (Branch branch : al) {
			if (branch.getName().equals(name)) // assumptions that branch names are always unique
			{
				toRemove = branch;
				BranchTextDB.removeBranch("branch.txt", toRemove);
				System.out.println("Branch has been removed successfully.");
				break;
			}
		}
		if (toRemove == null) {
			System.out.println("Branch does not exist");
		}
	}

}
