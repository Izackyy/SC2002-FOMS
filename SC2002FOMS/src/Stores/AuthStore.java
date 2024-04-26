package Stores;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */
/**
 * The {@code AuthStore} class stores the current staff user.
 */
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
