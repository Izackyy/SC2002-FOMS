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
import Services.CheckQuota;
import Services.BranchManager;
import Services.StaffManager;
import Services.AuthStaffService;
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
			System.out.println("|| 1) Edit Staff Account         ||"); // just like how you edit menu
			System.out.println("|| 2) Display Staff List         ||");
			System.out.println("|| 3) Promote Staff To Manager   ||");
			System.out.println("|| 4) Transfer Staff/Manager     ||");
			System.out.println("|| 5) Add/Remove Payment Method  ||");
			System.out.println("|| 6) Open/Close branch          ||");
			System.out.println("|| 7) Add Branch                 ||");
			System.out.println("|| 8) Remove branch              ||");
			System.out.println("|| 9) Change Password            ||");
			System.out.println("|| 10) Quit                      ||");
			System.out.println("===================================");

			selection = sc.nextInt();
			BranchManager bm = new BranchManager();
			StaffManager sm = new StaffManager();

			switch (selection) {
				case (1):
					sm.editStaff();
					break;
				case (2):
					sm.filterStaff();
					break;
				case (3):
					sm.promoteStaff();
					break;
				case (4):
					sm.transferStaff();
					break;
				case (5):
					editPayment();
					break;
				case (6):
					bm.setBranchStatus();
					break;
				case (7):
					sc.nextLine();// input buffer
					bm.addBranch();
					break;
				case (8):
					sc.nextLine();// input buffer
					bm.removeBranch();
					;
					break;
				case (9):
					changePassword();
				default:
					return;
			}

		} while (selection != 10);
	}

	public void editPayment() throws IOException {

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

}
