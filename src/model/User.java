package model;

import java.io.Serializable;
import java.util.Comparator;

public class User implements Serializable
{
  String name;
  String password;
  double topSpeed;
  double avgSpeed;
  int noVisits;
  public static Comparator<User> userComparator = new Comparator<User>() {
	  public int compare(User user1, User user2) {
		  return Double.compare(user1.getTopSpeed(), user2.getTopSpeed());
	  }
  };
  	public User(String name,String password)
  	{
  		this.name = name;
  		this.password= password;
  		topSpeed = 0.0;
  		avgSpeed = 0.0;
  		noVisits = 0;
  	}
  	public User(){}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public double getTopSpeed() 
	{
		return topSpeed;
	}
	public void setTopSpeed(double topSpeed) 
	{
		this.topSpeed = topSpeed;
	}
	public double getAvgSpeed() 
	{
		return avgSpeed;
	}
	public void setAvgSpeed(double avgSpeed) 
	{
		this.avgSpeed = avgSpeed;
	}
	public int getNoVisits() 
	{
		return noVisits;
	}
	public void setNoVisits(int noVisits) 
	{
		this.noVisits = noVisits;
	}
}
