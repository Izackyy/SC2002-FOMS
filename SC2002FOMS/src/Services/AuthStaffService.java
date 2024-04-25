package Services;

import java.io.IOException;
import java.util.List;
import Stores.AuthStore;
import Stores.StaffTextDB;
import Stores.Staff;

public class AuthStaffService {

	public boolean login(String staffID, String password) throws IOException {

		List<Staff> al = StaffTextDB.readStaff("staff.txt");// test
		for (Staff staff : al) {

			if (staff.getLoginID().equals(staffID)) {
				if (staff.getPassword().equals(password)) {
					AuthStore.setCurrentStaff(staff);
					return true;
				}
			}
		}
		System.out.println("Wrong password. Please try again");
		return false;
	}

	public void logout() {
		AuthStore.setCurrentStaff(null); // remove current user
	}

}
