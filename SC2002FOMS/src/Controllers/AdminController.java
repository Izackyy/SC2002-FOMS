package Controllers;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Enums.Gender;
import Enums.Role;
import Services.StaffDisplay;
import Stores.Branch;
import Stores.BranchTextDB;
import Stores.Payment;
import Stores.PaymentTextDB;

public class AdminController extends EmployeeController {

	private static final Scanner sc = new Scanner(System.in);

	public void start() throws IOException {
		int selection;

		do {
			System.out.println("==========Admin's Actions==========");
			System.out.println("|| 1) Edit Staff Account         ||"); // just like how you edit menu
			System.out.println("|| 2) Display Staff List         ||");
			System.out.println("|| 3) Assign Manager             ||");
			System.out.println("|| 4) Promote Staff To Manager   ||");
			System.out.println("|| 5) Transfer Staff/Manager     ||");
			System.out.println("|| 6) Add/Remove Payment Method  ||");
			System.out.println("|| 7) Open/Close branch          ||");
			System.out.println("|| 8) Change Password            ||");
			System.out.println("|| 9) Quit                       ||");
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

				case (4):

				case (5):

				case (6):
					editPayment();
					break;
				case (7):
					setBranchStatus();
					break;
				case (8):
					changePassword();
					break;
				case (9):
					return;
			}

		} while (selection != 9);
	}

	private static void editStaffAcc() {
		System.out.println("editing staff account");
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
				int c = 1;
				for (Branch b : al) // need to add condition that calls isopen() to only display branches that are
									// open
				{
					if (b.isOpen()) {
						System.out.println(i + ") " + b.getName());
						i++;
					}
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

		}
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

		System.out.println(selectedBranch.getName() + " - " + selectedBranch.getBranchStatus());
		System.out.println("Branch status updated successfully.\n");

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
			}
		} while (Choice < 3);
	}
}
