package Stores;

import Enums.BranchStatus;

public class Branch {

	private String name;
	private String location;
	private int staffQuota;
	private BranchStatus branchStatus;

	public Branch(String name, String location, int staffQuota, BranchStatus branchStatus) {
		this.name = name;
		this.location = location;
		this.staffQuota = staffQuota;
		this.branchStatus = branchStatus.OPEN;
	}

	public void setStaffQuota(String branch) // not sure if this is needed tho
	{
		this.staffQuota = staffQuota;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public int getStaffQuota() {
		return staffQuota;
	}

	public BranchStatus getBranchStatus() {
		return branchStatus;
	}

	public boolean equals(Object o) {
		if (o instanceof MenuItem)
			;
		{
			Branch m = (Branch) o;
			return (getName().equals(m.getName()));
		}
	}

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

	public boolean isOpen() {
		if (this.branchStatus == branchStatus.OPEN) {
			return true;
		} else
			return false;
	}

}
