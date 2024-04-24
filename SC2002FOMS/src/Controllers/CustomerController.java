package Controllers;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Entity.Cart;
import Entity.NewOrder;
import Services.ChangeStatus;
import Services.CheckStatus;
import Services.MenuDisplay;
import Stores.AuthStore;
import Stores.Branch;
import Stores.BranchTextDB;
import Stores.MenuItem;
import Stores.MenuTextDB;

public class CustomerController {
	
	private static Branch branch;
	
	private static final Scanner sc = new Scanner(System.in);

	public static void start() throws IOException
	{
		int i = 1;
		int selection;
		System.out.println("Select branch");
		
		List<Branch> al = BranchTextDB.readBranchList("branch.txt");//test
        for (Branch branch : al)
        {	//need to add condition that calls isopen() to only display branches that are open
        	System.out.println(i + ") " + branch.getName());
        	i++;
        }
        
        selection = sc.nextInt();
        
        branch = al.get(selection-1);
        
        do
        {
        	System.out.println("========Welcome to " + branch.getName() + " branch========");
    		System.out.println("1) New Order");
    		System.out.println("2) Check Order Status");
    		System.out.println("3) Collect Order");
    		System.out.println("4) Quit");
    		System.out.println("=====================================");
    		
    		selection = sc.nextInt();
    		
    		switch(selection)
    		{
    			case(1):
    				newOrder(branch);
    				break;
    				
    			case(2):
    				CheckStatus.check(branch.getName());
    				break;
    			case(3):
    				ChangeStatus.changeStatus(branch.getName());
    			
    			//collect food change status
    		}
        }while (selection != 4);
		
        System.out.println("Session end");
		
	}
	
	private static void newOrder(Branch branch) throws IOException
	{ //add to cart function works
		NewOrder.startOrder(branch);
	}

}
