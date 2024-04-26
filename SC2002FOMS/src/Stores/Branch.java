package Stores;

import Enums.BranchStatus;

/**
 * Represents a branch of the restaurant.
 */
public class Branch {

	private String name;
	private String location;
	private int staffQuota;
	private BranchStatus branchStatus;

	/**
	 * Constructor for creating a branch.
	 * 
	 * @param name       The name of the branch.
	 * @param location   The location of the branch.
	 * @param staffQuota The staff quota of the branch.
	 * @param branchStatus The status of the branch.
	 */

	public Branch(String name, String location, int staffQuota, BranchStatus branchStatus) {
		this.name = name;
		this.location = location;
		this.staffQuota = staffQuota;
		this.branchStatus = branchStatus;
	}
	/**
	 * Gets the name of the branch.
	 * @return The name of the branch.
	 */

	public String getName() {
		return name;
	}
	/**
	 * Gets the location of the branch.
	 * @return The location of the branch.
	 */

	public String getLocation() {
		return location;
	}
	/**
	 * Gets the staff quota of the branch.
	 * @return The staff quota of the branch.
	 */

	public int getStaffQuota() {
		return staffQuota;
	}
	/**
	 * Gets the status of the branch.
	 * @return The status of the branch.
	 */

	public BranchStatus getBranchStatus() {
		return branchStatus;
	}
	/**
	 * Sets the name of the branch.
	 * @param name The new name of the branch.
	 */

	public boolean equals(Object o) {
		if (o instanceof MenuItem)
			;
		{
			Branch m = (Branch) o;
			return (getName().equals(m.getName()));
		}
	}
	/**
	 * Sets the location of the branch.
	 * @param location The new location of the branch.
	 */

	public boolean setBranchStatus(int a) {
		if (a == 1 && this.getBranchStatus() == branchStatus.OPEN) {
			System.out.println("Branch is already open!");
			return false;
		} else if (a == 0 && this.getBranchStatus() == branchStatus.CLOSED) {
			System.out.println("Branch is already closed!");
			return false;
		} else if (a == 1) {
			this.branchStatus = branchStatus.OPEN;
			return true;
		} else if (a == 0) {
			this.branchStatus = branchStatus.CLOSED;
			return true;
		}
		return false;
	}
	/**
	 * Sets the staff quota of the branch.
	 * @param staffQuota The new staff quota of the branch.
	 */

	public boolean isOpen() {
		if (this.branchStatus == branchStatus.OPEN) {
			return true;
		} else
			return false;
	}

}
