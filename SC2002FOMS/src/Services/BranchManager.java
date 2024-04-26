package Services;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import Interfaces.IBranchManagement;
import Stores.BranchTextDB;
import Stores.Branch;
import Enums.BranchStatus;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */

/**
 * Manages branch operations including adding, removing, printing, and setting the status of branches.
 * Implements the IBranchManagement interface.
 */
public class BranchManager implements IBranchManagement {

    private static final Scanner sc = new Scanner(System.in);
    /**
     * Adds a new branch after ensuring it does not already exist in the branch list.
     * This method prompts the user for the branch's name, location, and staff quota, and
     * automatically sets its status to OPEN. The new branch is then added to the branch list.
     * 
     * @throws IOException If an error occurs during file operations.
     */

    @Override
    public void addBranch() throws IOException {

        System.out.println("Name:");
        String name = sc.nextLine();

        @SuppressWarnings("unchecked")
        List<Branch> al = BranchTextDB.readBranchList("branch.txt");// test
        for (Branch branch : al) {
            if (branch.getName().equalsIgnoreCase(name)) {
                System.out.println("Branch already exists");
                return;
            }
        }
        // String name, String location, int staffQuota, BranchStatus branchStatus
        System.out.println("Location");
        String location = sc.nextLine();
        System.out.println("Staff Quota:");
        int staffQuota = sc.nextInt();
        sc.nextLine(); // input buffer

        // assumption that every new branch added will be set as open

        System.out.println("Branch has been successfully added");

        Branch branch = new Branch(name, location, staffQuota, BranchStatus.OPEN);
        BranchTextDB.addBranch("branch.txt", branch);
    }
    /**
     * Sets the status of an existing branch to either OPEN or CLOSED based on user input.
     * The method lists all branches, allows the user to select a branch, and prompts for the new status.
     * It validates the input and updates the branch's status if the input is valid.
     * 
     * @throws IOException If an error occurs during file operations.
     */
    @Override
    public void setBranchStatus() throws IOException {
        // TODO Auto-generated method stub
        int set, choice;

        printBranch("branch.txt");
        @SuppressWarnings("unchecked")
        List<Branch> branches = BranchTextDB.readBranchList("branch.txt");
        choice = sc.nextInt();
        Branch selectedBranch = branches.get(choice - 1);
        Branch oldStatus = selectedBranch;
        Branch newStatus = oldStatus;

        System.out.println("Open/Close Branch");
        System.out.println("<Press 1 to open Branch, Press 0 to close Branch>");

        set = sc.nextInt();
        while (set != 1 && set != 0) {
            System.out.println("Invalid choice! Enter 1 to open or 0 to close the branch.");
            set = sc.nextInt();
        }
        while (!newStatus.setBranchStatus(set)) {
            set = sc.nextInt();
        }

        BranchTextDB.updateBranchStatus("branch.txt", oldStatus, newStatus);

        printBranch("branch.txt");
        System.out.println("Branch status updated successfully.\n");
    }
    /**
     * Removes a branch specified by the user from the branch list.
     * This method prompts the user to enter the name of the branch to be removed,
     * checks if it exists, and removes it from the list if it does.
     * 
     * @throws IOException If an error occurs during file operations.
     */

    @Override
    public void removeBranch() throws IOException {

        System.out.println("=======Branch List=======");
        @SuppressWarnings("unchecked")
        List<Branch> al = BranchTextDB.readBranchList("branch.txt");// test

        for (Branch branch : al) {
            System.out.println("Name: " + branch.getName());
        }
        // BranchTextDB.printBranch("branch.txt");

        System.out.println("========================");

        System.out.println("Enter Branch Name:");
        String name = sc.nextLine();

        Branch toRemove = null;
        for (Branch branch : al) {
            if (branch.getName().equals(name)) // assumes that branch names are always unique
            {
                toRemove = branch;
                BranchTextDB.removeBranch("branch.txt", toRemove);
                System.out.println("Branch has been removed successfully.");
                break;
            }
        }
        if (toRemove == null) {
            System.out.println("Branch does not exist");
        }
    }
    /**
     * Prints all branches along with their status from the branch list.
     * This method reads the branch list from a file and displays each branch with its status.
     * 
     * @param filename The name of the file containing the branch information.
     * @throws IOException If an error occurs during file operations.
     */

    @Override
    public void printBranch(String filename) throws IOException {

        System.out.println("All branches: ");
        @SuppressWarnings("unchecked")
        List<Branch> branches = BranchTextDB.readBranchList("branch.txt");
        int c = 1;
        for (Branch b : branches) {
            System.out.println(c + ") " + b.getName() + " - " + b.getBranchStatus());
            c++;
        }
    }
}
