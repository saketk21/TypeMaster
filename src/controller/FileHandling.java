package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import model.User;

public class FileHandling {
	public static void writeScoreFile(User user)
	{
		ArrayList<User> al = readScoreFile();
		if(al == null)
			al = new ArrayList<User>();
		for(User currentUser:al) {
			if(currentUser.getName().equals(user.getName())) {
				al.remove(currentUser);
				break;
			}
		}
		al.add(user);
		Collections.sort(al, User.userComparator.reversed());
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try
		{
			fos = new FileOutputStream("score.dat");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(al);
		}
		catch(Exception e){}
	}
	@SuppressWarnings("unchecked")
	static ArrayList<User> readScoreFile()
	{
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		ArrayList<User> al =null;
		try
		{
			fis = new FileInputStream("score.dat");
			ois = new ObjectInputStream(fis);
			al = (ArrayList<User>)ois.readObject();
			fis.close();
			ois.close();
		}
		catch(Exception e){}
		return al;
	}
	public static User getUser(String name)
	{
		ArrayList<User> al = readScoreFile();
		if(al == null)
			return null;
		int size = al.size();
		User user  = null;
		for(int i=0;i<size;i++)
		{
			if(al.get(i).getName().equals(name))
			{
				user = al.get(i);
			}
		}
		return user;
	}
	public static boolean checkPassword(User user,String password)
	{
		return user.getPassword().equals(password);
	}
	public static User authenticate(String name,String password)
	{
		User user = getUser(name);
		if(user == null)
			return user;
		else
		{
			if(!checkPassword(user,password))
				user.setName("");
		}
		return user;
	}
	public static User addUser(String name,String password)
	{
		User user = getUser(name);
		if(user==null)
			user = new User(name,password);
		else
			user = null;
		return user;
	}
	public static String readPara()
	{
		int n = ((int)(Math.random()*100))%5;
                
		String filesFolder = "Paragraphs/";
		String str = "";
		switch(n)
		{
			case 0:
				str = "a.txt";
				break;
			case 1:
				str = "b.txt";
				break;
			case 2:
				str = "c.txt";
				break;
			case 3:
				str = "d.txt";
				break;
			case 4:
				str = "e.txt";
				break;
			case 5:
				str = "f.txt";
				break;
		}
		String msg = "";
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(filesFolder+str));
			String line = br.readLine();
		
			while(line != null)
			{
				msg +=line+"\n";
				line = br.readLine();
			}
			br.close();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	public static double getHighestScore(User user)
	{
		ArrayList<User> al = readScoreFile();
		
		String name = user.getName();
		int size=0;
		if(al != null)
		{ size = al.size();}
		int i=0;
		for(i=0;i<size;i++)
		{
			User tmp = al.get(i);
			if(tmp.getName().equals(name))
				break;
		}
		return al.get(i).getTopSpeed();
	}
	public static Object[][] getModelData()
	{
		ArrayList<User> al = readScoreFile();
		int size = al.size();
		int n=4;
		Object [][]arr = new Object[size][n];
		for(int i=0;i<size;i++)
		{
			arr[i][0] = i+1;
			arr[i][1] = al.get(i).getName();
			arr[i][2] = (int)(al.get(i).getAvgSpeed());
			arr[i][3] = (int)(al.get(i).getTopSpeed());
		}
		return arr;
	}
}
