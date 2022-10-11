package com.company;

public class UsersBalance extends UserName {
     
    private Balance account;
    
    public UsersBalance(String name){
	super(name);
	this.account = new Balance();
    }

    public void deposit(float amount){
	this.account.deposit(amount);
    }

    public void withdraw(float amount){
	this.account.withdraw(amount);
    }

    public float getFunds(){
	return this.account.getBalance();
    }
    
}
