package com.company;

public class Balance {
    //Creates the users balance variable
    private float balance;

    //Constructor to set the initial value of balance
    public Balance(){
        this.balance=0;
    }    

    public Balance(float balance){
        this.balance = balance;
    }

    public float getBalance(){
        return this.balance;
    }    

    public void deposit(float amount){
        this.balance += amount;
    }    

    public void withdraw(float amount){
        this.balance -= amount;
    }

    public String toString(){
	return "Balance: " + this.balance;
    }
    
}
