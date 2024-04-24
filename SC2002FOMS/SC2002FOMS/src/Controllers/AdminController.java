package Controllers;

import java.util.Scanner;

public class AdminController {

	public void start()
	{
		final Scanner sc = new Scanner(System.in);
		int selection;
		
		do
		{
			System.out.println("==========Admin's Actions==========");
			System.out.println("|| 1) Edit Staff Account         ||"); //just like how you edit menu
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
			
			switch (selection)
			{
				case(1):
				{
					editStaffAcc();
				}
				case(2):
				{
					
				}
			}
			
		} while (selection != 9);
	}
	
	private static void editStaffAcc()
	{
		System.out.println("editing staff account"); 
	}
}
