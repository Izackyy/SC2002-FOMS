package View;

import java.io.IOException;
import java.util.ArrayList;

import Enums.Gender;
import Enums.Role;
import Stores.Staff;
import Stores.StaffTextDB;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */
/**
 * StaffDisplay class allows the staff to view the staff list.
 */
public class StaffDisplay {
	/**
	 * Prints the entire staff list.
	 */
	public static void printStaffList() //for admin to call, not required
	  {
		  String filename = "staff.txt" ;
			try {
				// read file containing staff records.
				@SuppressWarnings("rawtypes")
				ArrayList al = StaffTextDB.readStaff(filename) ;
				for (int i = 0 ; i < al.size() ; i++) {
						Staff staff = (Staff)al.get(i);
						System.out.printf((i+1) + ") Name: %-20s LoginID: %-20s Role: %-20s Branch: %s\n", staff.getName(), staff.getLoginID(), staff.getRole(), staff.getBranch());
						//System.out.println("Name: " + staff.getName() + ", LoginID:" + staff.getLoginID() + ", Role:" + staff.getRole() + ", Gender:" + staff.getGender() + ", Age:" + staff.getAge() + ", Branch:" + staff.getBranch());
				}
				
			}catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
	  	}
	  /**
	   * Prints the staff list of a specific branch.
	   * 
	   * @param branch The branch to print the staff list of.
	   */
	public static void printStaffList(String branch) //overload to call branch staff list
	{
		System.out.println("======Staff List @" + branch + "======");
		String filename = "staff.txt" ;
		try {
			// read file containing staff records.
			@SuppressWarnings("rawtypes")
			ArrayList al = StaffTextDB.readStaff(filename) ;
			for (int i = 0 ; i < al.size() ; i++) { 
					Staff staff = (Staff)al.get(i);
					if (staff.getBranch().equals(branch))
					{
						System.out.printf((i+1) + ") Name: %-20s LoginID: %-20s Role: %-20s Branch: %s\n", staff.getName(), staff.getLoginID(), staff.getRole(), staff.getBranch());
						//System.out.println("Name: " + staff.getName() + ", LoginID: " + staff.getLoginID() + ", Role: " + staff.getRole() + ", Gender: " + staff.getGender() + ", Age: " + staff.getAge() + ", Branch: " + staff.getBranch());
					}
			}
			
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}
	  
	// --- for manager ---
	/**
	 * Prints the staff list of a specific age.
	 * 
	 * @param age The age to print the staff list of.
	 */
	public static void printStaffList(int age)
	{
		System.out.println("======Staff List (Age)======");
		String filename = "staff.txt" ;
		try {
			// read file containing staff records.
			@SuppressWarnings("rawtypes")
			ArrayList al = StaffTextDB.readStaff(filename) ;
			for (int i = 0 ; i < al.size() ; i++) { 
				Staff staff = (Staff)al.get(i); // al is the array list
				if (staff.getAge() == age) // the age filter
				{
					System.out.printf((i+1) + ") Name: %-20s LoginID: %-20s Role: %-20s Branch: %s\n", staff.getName(), staff.getLoginID(), staff.getRole(), staff.getBranch());
					//System.out.println("Name: " + staff.getName() + ", LoginID: " + staff.getLoginID() + ", Role: " + staff.getRole() + ", Gender: " + staff.getGender() + ", Age: " + staff.getAge() + ", Branch: " + staff.getBranch());
				}
			}
			
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}
	/**
	 * Prints the staff list of a specific gender
	 * @param gender The gender to print the staff list of.
	 */
	public static void printStaffList(Gender gender)
	{
		System.out.println("======Staff List (Gender)======");
		String filename = "staff.txt" ;
		try {
			// read file containing staff records.
			@SuppressWarnings("rawtypes")
			ArrayList al = StaffTextDB.readStaff(filename) ;
			for (int i = 0 ; i < al.size() ; i++) { 
				Staff staff = (Staff)al.get(i);
				if (staff.getGender().equals(gender))
				{
					System.out.printf((i+1) + ") Name: %-20s LoginID: %-20s Role: %-20s Branch: %s\n", staff.getName(), staff.getLoginID(), staff.getRole(), staff.getBranch());
					//System.out.println("Name: " + staff.getName() + ", LoginID: " + staff.getLoginID() + ", Role: " + staff.getRole() + ", Gender: " + staff.getGender() + ", Age: " + staff.getAge() + ", Branch: " + staff.getBranch());
				}
			}
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}
	  /**
	   * Prints the staff list of a specific role.
	   * 
	   * @param role The role to print the staff list of.
	   */
	public static void printStaffList(Role role)
	{
		System.out.println("======Staff List (Role)======");
		String filename = "staff.txt" ;
		try {
			// read file containing staff records.
			@SuppressWarnings("rawtypes")
			ArrayList al = StaffTextDB.readStaff(filename) ;
			for (int i = 0 ; i < al.size() ; i++) { 
				Staff staff = (Staff)al.get(i);
				if (staff.getRole().equals(role))
				{
					System.out.printf("Name: %-20s LoginID: %-20s Role: %-20s Branch: %s\n", staff.getName(), staff.getLoginID(), staff.getRole(), staff.getBranch());
					//System.out.println("Name: " + staff.getName() + ", LoginID: " + staff.getLoginID() + ", Role: " + staff.getRole() + ", Gender: " + staff.getGender() + ", Age: " + staff.getAge() + ", Branch: " + staff.getBranch());
				}
			}
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}
}
