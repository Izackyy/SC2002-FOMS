package Stores;

import Enums.Gender;
import Enums.Role;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */
/**
 * Represents a staff member.
 */
public class Staff {
	private String name;
	private String loginID;
	private Role role;
	private Gender gender;
	private int age;
	private String branch; 
	private String password;
	/**
	 * Creates a new staff member.
	 * 
	 * @param name The name of the staff member.
	 * @param loginID The login ID of the staff member.
	 * @param role The role of the staff member. */
	public Staff(String name, String loginID, Role role, Gender gender, int age, String branch)
	{
		this.name = name;
		this.loginID = loginID;
		this.role = role;
		this.gender = gender;
		this.age = age;
		this.branch = branch;
		this.password = "password";
	}
	/**
	 * Creates a new staff member.
	 * 
	 * @param name The name of the staff member.
	 * @param loginID The login ID of the staff member.
	 * @param role The role of the staff member.*/

	public Staff(String name, String loginID, Role role, Gender gender, int age, String branch, String password)
	{
		this.name = name;
		this.loginID = loginID;
		this.role = role;
		this.gender = gender;
		this.age = age;
		this.branch = branch;
		this.password = password;
	}
	/**
	 * Gets the name of the staff member.
	 * 
	 * @return The name of the staff member.
	 */
	public String getName(){
		return name;
	}
	/**
	 * Gets the login ID of the staff member.
	 * 
	 * @return The login ID of the staff member.
	 */
	public String getLoginID(){
		return loginID;
	}
	/**
	 * Gets the role of the staff member.
	 * 
	 * @return The role of the staff member.
	 */
	public Role getRole(){
		return role;
	}
	
	/**
	 * Gets the gender of the staff member.
	 * 
	 * @return The gender of the staff member/
	 * */

	public Gender getGender(){
		return gender;
	}

	/**
	 * Gets the age of the staff member.
	 * 
	 * @return The age of the staff member.
	 */
	
	public int getAge(){
		return age;
	}
	
	/**
	 * Sets the name of the staff member.
	 * 
	 * @param name The new name of the staff member.
	 */
	public void setLoginID(String loginID)
	{
		this.loginID = loginID;
	}
	/**
	 * Sets the role of the staff member.
	 * 
	 * @param role The new role of the staff member.
	 */
	
	public void setAge(int age)
	{
		this.age = age;
	}
	/**
	 * Sets the branch of the staff member.
	 * 
	 * @param branch The new branch of the staff member.
	 */
	public String getBranch(){
		return branch;
	}
	/**
	 * Sets the password of the staff member.
	 * 
	 * @param password The new password of the staff member.
	 */
	public String getPassword(){
		return password;
	}
	/**
	 * Sets the name of the staff member.
	 * 
	 * @param name The new name of the staff member.
	 */
	public void setRole(Role role)
	{
		this.role = role;
	}
	
	/**
	 * Sets the Branch of the staff member
	 * 
	 * @param branch The new branch of the staff member.
	 */

	public void setBranch(String branch)
	{
		this.branch = branch;
	}
	/**
	 * Sets the password of the staff member.
	 * 
	 * @param password The new password of the staff member.
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	/**
	 * Returns a string representation of the staff member.
	 * 
	 * @return A string representation of the staff member.
	 */
	public boolean equals(Object o)
	{
		if (o instanceof Staff);
		{
			Staff s = (Staff)o;
			return (getName().equals(s.getName()));
		}
	}
	
	
}