package Stores;

public class Branch {
	
	private String name;
	private String location;
	private int staffQuota;
	
	public Branch(String name, String location, int staffQuota)
	{
		this.name = name;
		this.location = location;
		this.staffQuota = staffQuota;
	}
	
	public void setStaffQuota(String branch) //not sure if this is needed tho
	{
		this.staffQuota = staffQuota;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public int getStaffQuota()
	{
		return staffQuota;
	}
	
	public boolean equals(Object o)
	{
		if (o instanceof MenuItem);
		{
			MenuItem m = (MenuItem)o;
			return (getName().equals(m.getName()));
		}
	}

}
