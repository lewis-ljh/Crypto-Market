package com.company;

import java.util.Random;

public class Bitcoin extends CryptoStocks{

    private float price;
    private float changeFactor;
    private String Cryptoname = "Bitcoin";
    private int amountOwned;

    public Bitcoin(){
        generatePrice();
        generateChangeFactor();
        changePrice();
    }

    @Override
    //Generates a random price between a certain value
    public void generatePrice(){
        Random rand = new Random();
        price = rand.nextInt(10_001)+25_000; // £25,000 - £35,000
    }

    @Override
    //Generates a price change everytime the system is loaded up
    public void generateChangeFactor(){
        Random rand = new Random();
        changeFactor = (rand.nextInt(31)+85)/100f; // 85% - 115%

    }

//Actually changes the price
    public void changePrice(){
        generateChangeFactor();
        price = price*changeFactor;
    }
    //getter methods
    public String getCryptoname(){
        return this.Cryptoname;
    }

    public float getPrice(){
        return this.price;
    }

}
