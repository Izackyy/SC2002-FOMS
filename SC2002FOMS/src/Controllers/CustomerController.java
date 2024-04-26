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
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */

/**
 * CustomerController manages the customer interactions with branches,
 * allowing them to select a branch, place orders, check the status of orders,
 * and collect orders.
 */
public class CustomerController {
    
    private static Branch branch;
    
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Starts the customer session by listing available branches and allowing the customer
     * to choose one to place orders or perform other actions.
     * 
     * @throws IOException if an I/O error occurs when reading the branch list.
     */
    public static void start() throws IOException {
        int i = 1;
        int selection;
        System.out.println("Select branch");
        
        List<Branch> open = new ArrayList<>();
        
        @SuppressWarnings("unchecked")
        List<Branch> al = BranchTextDB.readBranchList("branch.txt"); // Read all branches
        for (Branch branch : al) {    
            if (branch.getBranchStatus().equals(BranchStatus.OPEN)) {
                System.out.println(i + ") " + branch.getName());
                i++;
                open.add(branch);
            }
        }
        
        selection = sc.nextInt();
        
        branch = open.get(selection-1); // Selecting the branch
        
        do {
            System.out.println("========Welcome to " + branch.getName() + " branch========");
            System.out.println("1) New Order");
            System.out.println("2) Check Order Status");
            System.out.println("3) Collect Order");
            System.out.println("4) Quit");
            System.out.println("=====================================");
            
            selection = sc.nextInt();
            
            switch(selection) {
                case(1):
                    newOrder(branch);
                    break;
                    
                case(2):
                    CheckStatus.check(branch.getName());
                    break;
                    
                case(3):
                    ChangeStatus.changeStatus(branch.getName());
                    // Collect food change status
            }
        } while (selection != 4);
        
        System.out.println("Session end");
        
    }
    
    /**
     * Initiates a new order for the specified branch. This method interacts with the NewOrder entity
     * to process ordering functionality.
     * 
     * @param branch The branch where the order is to be placed.
     * @throws IOException If there is an issue with order processing input/output.
     */
    private static void newOrder(Branch branch) throws IOException {
        NewOrder.startOrder(branch);
    }
}
