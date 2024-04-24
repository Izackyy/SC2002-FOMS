package Stores;

public class Staff {
	private String name;
	private String loginID;
	private String role;
	private String gender;
	private int age;
	private String branch; 
	private String password;
	
	public Staff(String name, String loginID, String role, String gender, int age, String branch, String password)
	{
		this.name = name;
		this.loginID = loginID;
		this.role = role;
		this.gender = gender;
		this.age = age;
		this.branch = branch;
		this.password = password;
	}
	
	public String getName(){
		return name;
	}
	
	public String getLoginID(){
		return loginID;
	}
	
	public String getRole(){
		return role;
	}
	
	public String getGender(){
		return gender;
	}
	
	public int getAge(){
		return age;
	}
	
	public String getBranch(){
		return branch;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setRole(String role)
	{
		this.role = role;
	}
	
	public void setBranch(String branch)
	{
		this.branch = branch;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public boolean equals(Object o)
	{
		if (o instanceof Staff);
		{
			Staff s = (Staff)o;
			return (getName().equals(s.getName()));
		}
	}
	
	
}
