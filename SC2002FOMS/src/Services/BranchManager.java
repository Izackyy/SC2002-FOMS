package Services;

import Stores.StaffTextDB;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import Stores.Staff;
import Stores.Branch;
import Enums.Role;
import Stores.BranchTextDB;

//define max staff and manager?

public class BranchManager {

    private static int staffCount = 0, managerCount = 0;

    public static int staffQuota(Branch b) throws IOException {
        List<Staff> staffs = StaffTextDB.readStaff("staff.txt");
        for (Staff s : staffs) {
            if (s.getRole().equals(Enums.Role.S) && s.getBranch().equals(b.getName())) {
                staffCount++;
            }
        }
        return staffCount;
    }

    public static int managerQuota(Branch b) throws IOException {
        List<Staff> staffs = StaffTextDB.readStaff("staff.txt");
        for (Staff s : staffs) {
            if (s.getRole().equals(Enums.Role.M) && s.getBranch().equals(b.getName())) {
                managerCount++;
            }
        }
        return managerCount;
    }

    public static int remainderStaffQuota(Branch b) throws IOException {

        int s = staffQuota(b);
        int max_s = b.getStaffQuota();

        // overstaffed
        if (max_s - s < 0)
            return -1;
        else
            return max_s - s;
    }

    public static int remainderManagerQuota(Branch b) throws IOException {

        int s = staffQuota(b);
        int m = managerQuota(b);

        // check manager quota, too many managers = -1
        if (1 <= s && s <= 4) {
            if (1 - m <= 0)
                return -1;
            else
                return 1 - m;
        }

        if (5 <= s && s <= 8) {
            if (2 - m <= 0)
                return -1;
            else
                return 2 - m;
        }

        if (9 <= s && s <= 15) {
            if (3 - m <= 0)
                return -1;
            else
                return 3 - m;
        }
        return -1;
    }

    public static boolean checkStaffQuota(Branch b) throws IOException {
        int check;
        check = remainderStaffQuota(b);
        if (check < 0) {
            System.out.println("Too many staffs in " + b.getName() + ". Remove some staffs.");
            return false;
        } else if (check == 0) {
            System.out.println("No more staff slots left in" + b.getName() + ". Choose another branch.");
            return false;
        } else if (check > 0) {
            System.out.println(check + " staff slot(s) remaining.");
            return true;
        }
        return false;
    }

    public static boolean checkManagerQuota(Branch b) throws IOException {
        int check;
        check = remainderManagerQuota(b);
        if (check < 0) {
            System.out.println("Too many managers in " + b.getName() + ". Remove some managers.");
            return false;
        } else if (check == 0) {
            System.out.println("No more manager slots left in" + b.getName() + ". Choose another branch.");
            return false;
        } else if (check > 0) {
            System.out.println(check + " manager slot(s) remaining.");
            return true;
        }
        return false;
    }

}
