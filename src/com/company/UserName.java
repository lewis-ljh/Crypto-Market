package com.company;

public class UserName extends CryptoStocks{

    private String name;
    
    public UserName(String name){
	this.name = name;
    }

    public String getName(){
	return this.name;
    }

    public void setName(String name){
	this.name=name;
    }
    
}
