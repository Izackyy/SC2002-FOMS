
package Controllers;

import java.io.IOException;
import java.util.Scanner;

import Interfaces.IBranchManagement;
import Interfaces.IPaymentManagement;
import Interfaces.IStaffManagement;

/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */

/**
 * AdminController extends the functionalities of EmployeeController to provide 
 * administrative actions that can be performed by admin users. 
 * It utilizes interfaces to manage branches, staff, and payment methods.
 */
public class AdminController extends EmployeeController {

    private IBranchManagement bm;
    private IStaffManagement sm;
    protected IPaymentManagement pm;

    private static final Scanner sc = new Scanner(System.in);

    /**
     * Constructs an AdminController with branch, staff, and payment management capabilities.
     * 
     * @param bm the branch management interface
     * @param sm the staff management interface
     * @param pm the payment management interface
     */
    public AdminController(IBranchManagement bm, IStaffManagement sm, IPaymentManagement pm) {
        this.bm = bm;
        this.sm = sm;
        this.pm = pm;
    }

    /**
     * Starts the admin control loop which allows performing various administrative tasks.
     * This method will continuously display a menu of options until the user decides to quit.
     * 
     * @throws IOException if an I/O error occurs
     */
    public void start() throws IOException{
        int selection;

        do {
            System.out.println("==========Admin's Actions==========");
            System.out.println("|| 1) Edit Staff Account         ||");
            System.out.println("|| 2) Add Staff Account          ||");
            System.out.println("|| 3) Remove Staff Account       ||");
            System.out.println("|| 4) Display Staff List         ||");
            System.out.println("|| 5) Promote Staff To Manager   ||");
            System.out.println("|| 6) Transfer Staff/Manager     ||");
            System.out.println("|| 7) Add/Remove Payment Method  ||");
            System.out.println("|| 8) Open/Close branch          ||");
            System.out.println("|| 9) Add Branch                 ||");
            System.out.println("|| 10) Remove branch             ||");
            System.out.println("|| 11) Change Password           ||");
            System.out.println("|| 12) Quit                      ||");
            System.out.println("===================================");

            selection = sc.nextInt();

			switch (selection) {
				case (1):
					sm.editStaffAcc();
					break;
				case (2):
                    sm.addStaffAcc();
                    break;
                case(3):
                    sm.removeStaffAcc();
                    break;
                case(4):
					sm.filterStaff();
					break;
				case (5):
					sm.promoteStaff();
					break;
				case (6):
					sm.transferStaff();
					break;
				case (7):
					pm.editPayment();
					break;
				case (8):
					bm.setBranchStatus();
					break;
				case (9):
					bm.addBranch();
					break;
				case (10):
					sc.nextLine();// input buffer
					bm.removeBranch();
					break;
				case (11):
					changePassword();
                    break;
				default:
					return;
			}

        } while (selection != 12);
    }
}
