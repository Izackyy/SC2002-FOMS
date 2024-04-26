package Services;

import java.io.IOException;
import java.util.ArrayList;

import Enums.Gender;
import Enums.Role;
import Stores.Staff;
import Stores.StaffTextDB;

public class StaffDisplay {
	
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
