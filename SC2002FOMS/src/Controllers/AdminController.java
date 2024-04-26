package Controllers;

import java.io.IOException;
import java.util.Scanner;

import Interfaces.IBranchManagement;
import Interfaces.IPaymentManagement;
import Interfaces.IStaffManagement;


public class AdminController extends EmployeeController {

	private IBranchManagement bm;
	private IStaffManagement sm;
	protected IPaymentManagement pm;

	private static final Scanner sc = new Scanner(System.in);

	public AdminController(IBranchManagement bm, IStaffManagement sm, IPaymentManagement pm) {
		this.bm = bm;
		this.sm = sm;
		this.pm = pm;
	}

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

			switch (selection) {
				case (1):
					sm.editStaffAcc();
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
