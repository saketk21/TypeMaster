package controller;

import model.User;

public class Backend 
{
	
	static int calcAccuracy(String strType, String strRef)
	{
		int len1 = strType.length(),len2 = strRef.length();
		int [][]arr = new int[len1+1][len2+1];
		for(int i=0;i<=len1;i++)
		{
			for(int j=0;j<=len2;j++)
			{
				if(i==0||j==0)
					arr[i][j] = 0;
				else if(strType.charAt(i-1)==strRef.charAt(j-1))
					arr[i][j] = arr[i-1][j-1]+1;
				else
					arr[i][j] = Math.max(arr[i-1][j],arr[i][j-1]);
					
			}
		}
		return arr[len1][len2];
	}
	public static String update(User user , String type, String ref,double time)
	{
		int len1 = type.length();
		int len2 = ref.length();
		
		int acc = calcAccuracy(type,ref);
		double score = (double)acc/(6.0*time);
		double accuracy = 0d;
		try {
			accuracy = (double)acc/len2*100;
		}catch(Exception e) {
			accuracy = 0;
		}
		user = addUser(user , score);
		String str = String.format("%d %.2f %d",(int)score,accuracy,(int)user.getTopSpeed());
		//String str = ""+(int)score+" "+accuracy+" "+(int)user.getTopSpeed();
		FileHandling.writeScoreFile(user);
		if(accuracy<80d)
			str = "";
		return  str;
		
	}
	static User addUser(User user, double score)
	{
		user.setAvgSpeed((user.getNoVisits()*user.getAvgSpeed()+score)/(user.getNoVisits()+1));
		user.setNoVisits(user.getNoVisits()+1);
		if(user.getTopSpeed()<score)
			user.setTopSpeed(score);
		return user;
	}
	public static String increment(String time)
    {
        String newTime = "";
        int a = Integer.parseInt(time.substring(3,5));
        if(a==59)
            a=0;
        else
            a++;
        int b = Integer.parseInt(time.substring(0,2));
        if(a==0)
            b++;
        if(b/10==0)
            newTime += "0";
        newTime += b+":";
        if(a/10==0)
            newTime += "0";
        newTime += a;
        return newTime;
    }
	public static String[] split(String str)
	{
		return str.split(" ");
	}
}