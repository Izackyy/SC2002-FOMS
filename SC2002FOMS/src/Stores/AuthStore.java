package Stores;

public class AuthStore {
	
	//this will keep track of current user, and be able to provide all know information about staff user eg. role, branch
	
	private static Staff currentStaff;
	
	public static void setCurrentStaff(Staff currentStaff)
	{
		AuthStore.currentStaff = currentStaff;
	}
	
	public static Staff getCurrentStaff()
	{
		return currentStaff;
	}

}
