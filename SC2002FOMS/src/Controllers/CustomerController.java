package Controllers;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import Entity.NewOrder;
import Enums.BranchStatus;
import Services.ChangeStatus;
import Services.CheckStatus;
import Stores.Branch;
import Stores.BranchTextDB;

public class CustomerController {
	
	private static Branch branch;
	
	private static final Scanner sc = new Scanner(System.in);

	public static void start() throws IOException
	{
		int i = 1;
		int selection;
		System.out.println("Select branch");
		
		List<Branch> open = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		List<Branch> al = BranchTextDB.readBranchList("branch.txt");//test
        for (Branch branch : al)
        {	
        	if(branch.getBranchStatus().equals(BranchStatus.OPEN))
        	{
        		System.out.println(i + ") " + branch.getName());
            	i++;
            	open.add(branch);
        	}
        }
        
        selection = sc.nextInt();
        
        branch = open.get(selection-1);
        
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
