package Services;

import java.io.IOException;
import java.util.List;

import Controllers.AuthController;
import Main.FomsApp;
import Stores.AuthStore;
import Stores.StaffTextDB;
import Stores.Staff;

public class AuthStaffService {

	public boolean login(String staffID, String password) throws IOException {

		@SuppressWarnings("unchecked")
		List<Staff> al = StaffTextDB.readStaff("staff.txt");// test
		for (Staff staff : al) {

			if (staff.getLoginID().equals(staffID)) {
				if (staff.getPassword().equals(password)) {
					AuthStore.setCurrentStaff(staff);
					return true;
				}
			}
		}
		System.out.println("Error occurred during login. Please try again");
		return false;
	}

	public boolean logout() {
		AuthStore.setCurrentStaff(null); // remove current user
		return true;
	}

}
