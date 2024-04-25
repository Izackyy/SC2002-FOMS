package Controllers;

import java.io.IOException;
import java.util.Scanner;

import Services.BranchManager;
import Services.StaffManager;
import Services.PaymentManager;

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
			PaymentManager pm = new PaymentManager();

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
					pm.editPayment();
					break;
				case (6):
					bm.setBranchStatus();
					break;
				case (7):
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
}
