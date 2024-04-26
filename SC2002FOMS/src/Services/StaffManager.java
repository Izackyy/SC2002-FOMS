package Services;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import Stores.Staff;
import Stores.StaffTextDB;
import View.StaffDisplay;
import Stores.Branch;
import Stores.BranchTextDB;
import Enums.Role;
import Enums.Gender;
import Interfaces.IStaffManagement;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */
/**
 * StaffManager class allows the manager to manage staff accounts.
 */

public class StaffManager implements IStaffManagement {
    private static final Scanner sc = new Scanner(System.in);
    /**
     * Adds a new staff account to the system.
     * 
     * @throws IOException if an error occurs during file operations
     */

    public void addStaffAcc() {
        try {
            System.out.println("Name:");
            String name = sc.nextLine();

            List<Staff> staffList = StaffTextDB.readStaff("staff.txt");
            for (Staff staff : staffList) {
                if (staff.getName().equalsIgnoreCase(name)) {
                    System.out.println("Staff already exists");
                    return;
                }
            }

            System.out.println("StaffID:");
            String staffID = sc.nextLine();
            System.out.println("Role: ");
            Role role = Role.valueOf(sc.nextLine().toUpperCase());
            System.out.println("Gender: ");
            Gender gender = Gender.valueOf(sc.nextLine().toUpperCase());
            System.out.println("Age:");
            int age = sc.nextInt(); sc.nextLine();

            System.out.println("Branch:");
            List<Branch> branches = BranchTextDB.readBranchList("branch.txt");
            branches.forEach(branch -> System.out.println(branch.getName()));
            int selection = sc.nextInt(); sc.nextLine();

            Branch branch = branches.get(selection - 1);

            Staff newStaff = new Staff(name, staffID, role, gender, age, branch.getName());
            StaffTextDB.addStaff("staff.txt", newStaff);
            System.out.println("Staff has been successfully added.");
        } catch (IOException e) {
            System.err.println("Failed to process input/output: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public void removeStaffAcc() {
        try {
            StaffTextDB.printStaffList("staff.txt");
            System.out.println("Enter Staff Name:");
            String sName = sc.nextLine();

            List<Staff> staffList = StaffTextDB.readStaff("staff.txt");
            Staff toRemove = staffList.stream()
                                      .filter(staff -> staff.getName().equalsIgnoreCase(sName))
                                      .findFirst()
                                      .orElse(null);

            if (toRemove == null) {
                System.out.println("Staff does not exist");
                return;
            }

            StaffTextDB.removeStaff("staff.txt", toRemove);
            System.out.println("Staff removed successfully.");
        } catch (IOException e) {
            System.err.println("Failed to process input/output: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public void editStaffAcc() {
        try {
            StaffTextDB.printStaffList("staff.txt");
            System.out.println("Select a staff member (input number):");
            int choice = sc.nextInt(); sc.nextLine();

            List<Staff> staffList = StaffTextDB.readStaff("staff.txt");
            Staff oldStaff = staffList.get(choice - 1);

            System.out.println("Current details of selected Staff:");
            System.out.println("Name: " + oldStaff.getName());
            System.out.println("StaffID: " + oldStaff.getLoginID());
            System.out.println("Password: " + oldStaff.getPassword());
            System.out.println("Age: " + oldStaff.getAge());

            System.out.println("Update StaffID? (Y/N)");
            if ("Y".equalsIgnoreCase(sc.nextLine())) {
                System.out.println("New Staff ID:");
                oldStaff.setLoginID(sc.nextLine());
            }

            System.out.println("Update Password? (Y/N)");
            if ("Y".equalsIgnoreCase(sc.nextLine())) {
                System.out.println("New password:");
                oldStaff.setPassword(sc.nextLine());
            }

            System.out.println("Update Age? (Y/N)");
            if ("Y".equalsIgnoreCase(sc.nextLine())) {
                System.out.println("New age:");
                oldStaff.setAge(sc.nextInt()); sc.nextLine();
            }

            StaffTextDB.updateStaff("staff.txt", oldStaff, oldStaff);
            System.out.println("Staff details have been updated.");
        } catch (InputMismatchException e) {
            System.err.println("Invalid input: " + e.getMessage());
            sc.nextLine();  // consume the wrong input
        } catch (IOException e) {
            System.err.println("Failed to process input/output: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
    public void filterStaff() {
        try {
            System.out.println("======Filter staff======");
            System.out.println("1) Branch");
            System.out.println("2) Role");
            System.out.println("3) Gender");
            System.out.println("4) Age");
            System.out.println("5) Display all (no filter)");
            int selection = sc.nextInt(); sc.nextLine();

            switch (selection) {
                case 1:
                    System.out.println("Select a branch:");
                    List<Branch> branches = BranchTextDB.readBranchList("branch.txt");
                    for (int i = 0; i < branches.size(); i++) {
                        System.out.println((i + 1) + ") " + branches.get(i).getName());
                    }
                    int choice = sc.nextInt(); sc.nextLine();
                    Branch selectedBranch = branches.get(choice - 1);
                    StaffDisplay.printStaffList(selectedBranch.getName());
                    break;
                case 2:
                    System.out.println("Select Role (1 for Staff, 2 for Manager):");
                    Role role = Role.values()[sc.nextInt() - 1]; sc.nextLine();
                    StaffDisplay.printStaffList(role);
                    break;
                case 3:
                    System.out.println("Select gender (1 for Female, 2 for Male):");
                    Gender gender = Gender.values()[sc.nextInt() - 1]; sc.nextLine();
                    StaffDisplay.printStaffList(gender);
                    break;
                case 4:
                    System.out.println("Enter age:");
                    int age = sc.nextInt(); sc.nextLine();
                    StaffDisplay.printStaffList(age);
                    break;
                case 5:
                    StaffTextDB.printStaffList("staff.txt");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.err.println("Invalid input: " + e.getMessage());
            sc.nextLine();  // consume the wrong input
        } catch (IOException e) {
            System.err.println("Failed to process input/output: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public void promoteStaff() {
        try {
            StaffTextDB.printStaffList("staff.txt");
            System.out.println("Select a staff member (input number):");
            int choice = sc.nextInt(); sc.nextLine();
            List<Staff> staffList = StaffTextDB.readStaff("staff.txt");
            Staff selectedStaff = staffList.get(choice - 1);

            System.out.println("Promote " + selectedStaff.getName() + " ? <Y/N>");
            if ("Y".equalsIgnoreCase(sc.nextLine())) {
                if (selectedStaff.getRole() == Role.S) {
                    selectedStaff.setRole(Role.M);
                    StaffTextDB.updateStaff("staff.txt", selectedStaff, selectedStaff);
                    System.out.println("Staff promoted successfully.");
                }
            }
        } catch (InputMismatchException e) {
            System.err.println("Invalid input: " + e.getMessage());
            sc.nextLine();  // consume the wrong input
        } catch (IOException e) {
            System.err.println("Failed to process input/output: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public void transferStaff() {
        try {
            StaffTextDB.printStaffList("staff.txt");
            System.out.println("Select a staff member (input number):");
            int choice = sc.nextInt(); sc.nextLine();
            List<Staff> staffList = StaffTextDB.readStaff("staff.txt");
            Staff selectedStaff = staffList.get(choice - 1);

            System.out.println("Select a new branch:");
            List<Branch> branches = BranchTextDB.readBranchList("branch.txt");
            branches.forEach(branch -> System.out.println(branch.getName()));
            int branchChoice = sc.nextInt(); sc.nextLine();
            Branch newBranch = branches.get(branchChoice - 1);

            selectedStaff.setBranch(newBranch.getName());
            StaffTextDB.updateStaff("staff.txt", selectedStaff, selectedStaff);
            System.out.println("Staff transferred successfully.");
        } catch (InputMismatchException e) {
            System.err.println("Invalid input: " + e.getMessage());
            sc.nextLine();  // consume the wrong input
        } catch (IOException e) {
            System.err.println("Failed to process input/output: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    // Promote, Transfer, and Filter functions would be similar, adding try-catch blocks
    // around the code previously discussed. Each would catch IOException, InputMismatchException,
    // and general Exception, logging appropriate error messages and stopping the execution flow
    // as you requested.
}
