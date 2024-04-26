package Services;

import java.io.IOException;
import java.util.List;

import Stores.AuthStore;
import Stores.StaffTextDB;
import Stores.Staff;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */
/**
 * The {@code AuthStaffService} class provides methods to authenticate staff members.
 * It allows staff members to log in and out of the system, and stores the current staff member in the authentication store.
 */
public class AuthStaffService {
	/**
     * Attempts to log in a staff member using their ID and password.
     * It reads the staff list from the storage, checks the credentials, and if successful,
     * sets the current staff in {@code AuthStore}.
     *
     * @param staffID the ID of the staff attempting to log in
     * @param password the password of the staff attempting to log in
     * @return {@code true} if the login is successful, {@code false} otherwise
     * @throws IOException if there is an issue reading from the staff storage file
     */
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
		System.out.println("Invalid UserID/Password.");
		return false;
	}
	 /**
     * Logs out the current staff member.
     * This method sets the current staff member in the authentication store to null, effectively
     * clearing any stored authentication details and logging the user out of the system.
     *
     * @return boolean Always returns true, indicating the logout process was successful.
     */


	public boolean logout() {
		AuthStore.setCurrentStaff(null); // remove current user
		return true;
	}
	   


}
