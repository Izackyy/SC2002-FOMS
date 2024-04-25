package Controllers;

import java.io.IOException;
import java.util.*;

import Services.AuthStaffService;

//import Services.AuthStaffService;

public class AuthController {

	private static final Scanner sc = new Scanner(System.in);

	public static AuthStaffService authService;

	public static void start() throws IOException {

		boolean authenticated = false;
		System.out.println("===========================");
		System.out.println("||                       ||");
		System.out.println("||   Welcome to FOMS :)  ||");
		System.out.println("||                       ||");
		System.out.println("===========================");

		// currently does not bring user back to this page after
		// they end session
		// System.out.println("<Press 0 to terminate>");

		do {

			System.out.println("Login as: ");
			System.out.println("1) Customer");
			System.out.println("2) Staff");
			System.out.println("3) Quit");

			int selection = sc.nextInt();
			switch (selection) {
				case (1): // no need for authenticated, bring straight to CustomerController.start()
					CustomerController.start();
					return;

				case (2):
					authService = new AuthStaffService();
					break;

				default:
					return;
			}

			System.out.println("UserID: ");
			sc.nextLine(); // Clear the input buffer
			String userID = sc.nextLine();

			System.out.println("Password: ");
			String password = sc.nextLine();

			authenticated = authService.login(userID, password);

		} while (authenticated == false);
	}

	public static void endSession() {
		authService.logout();
		System.out.println("You have been logged out");
	}
}
