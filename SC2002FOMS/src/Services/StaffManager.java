package Services;

import java.io.IOException;
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

public class StaffManager implements IStaffManagement {

    private static final Scanner sc = new Scanner(System.in);

    public void addStaffAcc() throws IOException {
        System.out.println("Name:");
        String name = sc.nextLine();

        @SuppressWarnings("unchecked")
        List<Staff> al = StaffTextDB.readStaff("staff.txt");// test
        for (Staff staff : al) {
            if (staff.getName().equalsIgnoreCase(name)) {
                System.out.println("Staff already exists");
                return;
            }
        }

        System.out.println("StaffID:");
        String staffID = sc.nextLine();
        System.out.println("Role: ");
        String sRole = sc.nextLine();

        Role role = null;
        if (sRole.equalsIgnoreCase("M")) {
            role = Role.M;
        } else if (sRole.equalsIgnoreCase("S")) {
            role = Role.S;
        } else {
            role = Role.A;
        }
        System.out.println("Gender: ");
        String sGender = sc.nextLine();
        Gender gender = null;
        if (sGender.equalsIgnoreCase("F")) {
            gender = Gender.F;
        } else {
            gender = Gender.M;
        }
        System.out.println("Age:");
        int age = sc.nextInt();

        int i = 1;
        System.out.println("Branch:");
        System.out.println("======Branch Option======");
        @SuppressWarnings("unchecked")
        List<Branch> b = BranchTextDB.readBranchList("branch.txt");// test
        for (Branch branch : b) {
            System.out.println(i + ") " + branch.getName());
            i++;
        }

        int selection = sc.nextInt();

        Branch branch = b.get(selection - 1);

        System.out.println("Staff has been successfully added");
        Staff staff = new Staff(name, staffID, role, gender, age, branch.getName());

        StaffTextDB.addStaff("staff.txt", staff);

    }

    public void removeStaffAcc() throws IOException {
        StaffTextDB.printStaffList("staff.txt");
        System.out.println("Enter Staff Name:");
        String sName = sc.nextLine();

        @SuppressWarnings("unchecked")
        List<Staff> s = StaffTextDB.readStaff("staff.txt");// test
        Staff toRemove = null;
        for (Staff sf : s) {
            if (sf.getName().equals(sName)) {
                toRemove = sf;
                StaffTextDB.removeStaff("staff.txt", toRemove);
                System.out.println("Staff removed successfully.");
                break;
            }
        }
        if (toRemove == null) {
            System.out.println("Staff does not exist");
        }
    }

    
    public void editStaffAcc() throws IOException {
        String yesNo;
        Staff oldStaff = null;
        System.out.println("========Staff list========");
        StaffTextDB.printStaffList("staff.txt");

        @SuppressWarnings("unchecked")
        List<Staff> s = StaffTextDB.readStaff("staff.txt");// test

        System.out.println("Select a staff member");
        int choice = sc.nextInt();
        oldStaff = s.get(choice - 1);

        sc.nextLine(); // test input buffer

        System.out.println("Current details of selected Staff:");

        System.out.println("Name: " + oldStaff.getName());
        System.out.println("StaffID: " + oldStaff.getLoginID());
        System.out.println("Password: " + oldStaff.getPassword());
        System.out.println("Age: " + oldStaff.getAge());
        System.out.println("");

        Staff newStaff = new Staff(oldStaff.getName(), oldStaff.getLoginID(), oldStaff.getRole(),
                oldStaff.getGender(), oldStaff.getAge(), oldStaff.getBranch(), oldStaff.getPassword());

        System.out.println("Update StaffID? (Y/N)");
        yesNo = sc.nextLine();
        if (yesNo.equalsIgnoreCase("Y")) {
            System.out.println("New Staff ID:");
            String newID = sc.nextLine();
            newStaff.setLoginID(newID);
            yesNo = "N";
        }
        System.out.println("Update Password? (Y/N)");
        yesNo = sc.nextLine();
        if (yesNo.equalsIgnoreCase("Y")) {
            System.out.println("New password:");
            String newPassword = sc.nextLine();
            newStaff.setPassword(newPassword);
            yesNo = "N";
        }

        System.out.println("Update Age? (Y/N)");
        yesNo = sc.nextLine();
        if (yesNo.equalsIgnoreCase("Y")) {
            System.out.println("New age:");
            int newAge = sc.nextInt();
            newStaff.setAge(newAge);
            yesNo = "N";
        }
        StaffTextDB.updateStaff("staff.txt", oldStaff, newStaff);
    }

    @Override
    public void editStaff() throws IOException {
        System.out.println("===========Staff Editor==========");
        System.out.println("|| 1) Add Staff                ||");
        System.out.println("|| 2) Remove Staff             ||");
        System.out.println("|| 3) Edit Staff               ||");
        System.out.println("=================================");

        int editChoice = sc.nextInt();
        sc.nextLine(); // input buffer
        switch (editChoice) {
            case (1):
                addStaffAcc();
                break;
            case (2):
                removeStaffAcc();
                break;
            case (3): // loginID, password,age
                editStaffAcc();
                break;
        }
        System.out.println("Staff details have been updated");
    }

    @Override
    public void filterStaff() throws IOException {

        int selection;
        int choice;
        int i = 1;
        System.out.println("======Filter staff======");
        System.out.println("1) Branch");
        System.out.println("2) Role");
        System.out.println("3) Gender");
        System.out.println("4) Age");
        System.out.println("5) Display all (no filter)");

        selection = sc.nextInt();

        switch (selection) {
            case (1):
                String branch;
                System.out.println("Select a branch: ");
                @SuppressWarnings("unchecked") List<Branch> al = BranchTextDB.readBranchList("branch.txt");// test
                for (Branch b : al) {
                    System.out.println(i + ") " + b.getName());
                    i++;
                }

                choice = sc.nextInt();

                branch = al.get(choice - 1).getName();

                StaffDisplay.printStaffList(branch);
                break;
            case (2):
                System.out.println("Select Role");
                System.out.println("1) Staff");
                System.out.println("2) Manager");

                choice = sc.nextInt();

                Role role = (choice == 1) ? Role.S : Role.M;
                StaffDisplay.printStaffList(role);
                break;
            case (3):
                System.out.println("Select gender");
                System.out.println("1) Female");
                System.out.println("2) Male");

                choice = sc.nextInt();

                Gender gender = (choice == 1) ? Gender.F : Gender.M;
                StaffDisplay.printStaffList(gender);
                break;
            case (4): // dont know if we can set a range
            {
                System.out.println("Enter age:");
                choice = sc.nextInt();
                StaffDisplay.printStaffList(selection);
                break;
            }

            case (5):
                StaffTextDB.printStaffList("staff.txt");
                break;
            default:
                System.out.println("Invalid choice.");

        }
    }

    @Override
    public void promoteStaff() throws IOException {

        int choice, i = 0;
        String confirm;

        // select staff
        System.out.println("Select a staff member");
        StaffTextDB.printStaffList("staff.txt");
        @SuppressWarnings("unchecked")
        List<Staff> staffs = StaffTextDB.readStaff("staff.txt");
        choice = sc.nextInt();
        sc.nextLine();
        Staff selectedStaff = staffs.get(choice - 1);
        Staff oldRole = selectedStaff;
        Staff newRole = oldRole;
        @SuppressWarnings("unchecked")
        List<Branch> branches = BranchTextDB.readBranchList("branch.txt");
        Branch b = null;
        for (Branch branch : branches) {
            if (selectedStaff.getBranch().equals(branch.getName())) {
                b = branches.get(i);
                break;
            }
            i++;
        }

        System.out.println("Promote " + selectedStaff.getName() + " ? <Y/N>");
        confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            if (selectedStaff.getRole().equals(Role.S)) {
                newRole.setRole(Role.M);
                newRole.setBranch("UNASSIGNED");
                StaffTextDB.updateStaff("staff.txt", oldRole, newRole);
                StaffTextDB.printStaffList("staff.txt");
                System.out.println("Staff has been promoted successfully.\n");
            }
        } else
            return;
    }

    @Override
    public void transferStaff() throws IOException {

        int choice;

        // select staff
        StaffTextDB.printStaffList("staff.txt");
        @SuppressWarnings("unchecked")
        List<Staff> staffs = StaffTextDB.readStaff("staff.txt");
        System.out.println("Select a staff member");
        choice = sc.nextInt();
        Staff selectedStaff = staffs.get(choice - 1);
        Staff oldBranch = selectedStaff;
        Staff newBranch = oldBranch;

        // select branch
        BranchManager bm = new BranchManager();
        bm.printBranch("branch.txt");
        @SuppressWarnings("unchecked")
        List<Branch> branches = BranchTextDB.readBranchList("branch.txt");
        choice = sc.nextInt();
        Branch b = branches.get(choice - 1);
        newBranch.setBranch(b.getName());

        // add input and branch check
        // check staff
        if (selectedStaff.getRole().equals(Role.S)) {
            if (CheckQuota.checkStaffQuota(b)) {
                StaffTextDB.updateStaff("staff.txt", oldBranch, newBranch);
                StaffTextDB.printStaffList("staff.txt");
                System.out.println("Staff transferred successfully.\n");
                return;
            }
        }
        // check manager
        else if (selectedStaff.getRole().equals(Role.M)) {
            if (CheckQuota.checkManagerQuota(b)) {
                StaffTextDB.updateStaff("staff.txt", oldBranch, newBranch);
                StaffTextDB.printStaffList("staff.txt");
                System.out.println("Staff transferred successfully.\n");
                return;
            }
        }
    }
}
