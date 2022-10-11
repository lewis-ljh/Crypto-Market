package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
public class UserInfo {
//Array list to store the different users
	//One example of the user of an array list
    private ArrayList<UsersBalance> User;

    public UserInfo(){
	User = new ArrayList<>();
    }

    public String getUserInfo(int UserNumber){
    	UsersBalance UsersInfo = User.get(UserNumber);
	String Output = "Name: "+ UsersInfo.getName()+ "\n"+"Balance: £"+UsersInfo.getFunds();
	return Output;
    }

    public void addUser(UsersBalance UsersInfo){
	User.add(UsersInfo);
    }

    public boolean deposit(String UserName, int amount){
	Iterator<UsersBalance> it = User.iterator();
	boolean found = false;	
	while (it.hasNext()){
		//Uses class UsersBalance to actually deposit the amount
	    UsersBalance User = it.next();
	    if (User.getName().equals(UserName)){
		found = true;
		User.deposit(amount);
	    }
	}
	//if not found returns false and doesnt deposit anything
	return found;
    }
	public boolean withdraw(String UserName, int amount){
		Iterator<UsersBalance> it = User.iterator();
		boolean found = false;
		while (it.hasNext()){
			UsersBalance User = it.next();
			if (User.getName().equals(UserName)){
				found = true;
				User.withdraw(amount);
			}
		}
		return found;
	}
}
