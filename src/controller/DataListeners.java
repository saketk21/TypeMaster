package controller;
import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Timing;
import model.User;
import view.AccuracyErrorDialog;
import view.ErrorDialog;
import view.Leaderboard;
import view.Login;
import view.ScoreDialog;
import view.TypingWindow;

public class DataListeners implements ActionListener,ChangeListener
{
	User user;
    static Timing tm;
    static {
    	tm = new Timing();
    }
    public DataListeners(User user) {
    	this.user=user;
	}
    public DataListeners(){}
	public void actionPerformed(ActionEvent e)
    {
        JButton b =(JButton)e.getSource();
		String str = b.getLabel();
        if(str.equals("Cancel") || str.equals("Close")) {
            System.exit(0);
        }
        if(str.equals("OK")){
        	ErrorDialog frm = (ErrorDialog)b.getParent().getParent().getParent().getParent();
        	frm.setVisible(false);
        	new Login().setVisible(true);
        }
        if(str.equals("Login/Signup"))
        {
            Login frm = (Login)((b.getParent()).getParent().getParent().getParent().getParent());
            frm.setVisible(false);
            User user1 = null;
            if(frm.getCheckBoxValue()==false)
            {
            	user1 = FileHandling.authenticate(frm.getUsername(),frm.getPassword());
            	if(user1==null)
            	{
            		ErrorDialog frm1 = new ErrorDialog("The username you entered does not exist.");
            		frm1.setVisible(true);
            		return;
            	}
            	else if(user1.getName().equals("")) {
            		ErrorDialog frm1 = new ErrorDialog("Incorrect Password.");
            		frm1.setVisible(true);
            		return;
            	}
            	user = user1;
            }
            else
            {
            	String username = frm.getUsername();
            	String password  =frm.getPassword();
            	if(username.equals("")||password.equals(""))
            	{
            		ErrorDialog frm1 = new ErrorDialog("Username/Password cannot be empty.");
            		frm1.setVisible(true);
            		return;
            	}
            	user1 = FileHandling.addUser(username,password);
            	if(user1==null)
            	{
            		ErrorDialog frm1 = new ErrorDialog("That username is taken.");
            		frm1.setVisible(true);
            		return;
            	}
            	user = user1;
            }
            System.out.println(user.getName());
            TypingWindow obj = new TypingWindow(user);
            obj.setVisible(true);
            tm.setStartTime(System.currentTimeMillis());
        }
        if(str.equals("Submit"))
        {
            tm.setEndTime(System.currentTimeMillis());
            double time = tm.returnTime()/60000;
            System.out.println("Start Time"+tm.getStartTime()+"\nEnd time"+tm.getEndTime());
            TypingWindow frm = (TypingWindow)((b.getParent()).getParent().getParent().getParent().getParent());
            frm.setVisible(false);           
            String result = Backend.update(user, frm.gettype(), frm.getRef(),time);
            System.out.println(result);
            if(result.equals(""))
            {
            	AccuracyErrorDialog frm1 = new AccuracyErrorDialog("Too many errors.");
        		frm1.setVisible(true);
        		return;
            }
            String []arg  = Backend.split(result);
            ScoreDialog obj = new ScoreDialog(arg[0],arg[1],arg[2]);
            obj.setVisible(true);
        }
        if(str.equals("Leaderboard"))
        {
            ScoreDialog frm = (ScoreDialog)((b.getParent()).getParent().getParent().getParent().getParent());
            frm.setVisible(false);
            Leaderboard obj = new Leaderboard();
            obj.setVisible(true);
        }
    }
	public void stateChanged(ChangeEvent e) {
		
	}
}
