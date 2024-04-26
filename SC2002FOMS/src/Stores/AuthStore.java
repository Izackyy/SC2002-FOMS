package Stores;
//      * Gets the customisation options for the item.
public class AuthStore {
	
	//this will keep track of current user, and be able to provide all know information about staff user eg. role, branch
	
	private static Staff currentStaff;

	/**
	 * Sets the current staff user.
	 * @param currentStaff The current staff user.
	 */
	public static void setCurrentStaff(Staff currentStaff)
	{
		AuthStore.currentStaff = currentStaff;
	}
	/**
	 * Gets the current staff user.
	 * @return The current staff user.
	 */
	public static Staff getCurrentStaff()
	{
		return currentStaff;
	}

}
