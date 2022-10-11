package com.company;

import java.util.Random;

public class CryptoStocks {

    private String Cryptoname;
    private float price;
    private float changeFactor;
    private int amountOwned;
    public CryptoStocks(){
        generatePrice();
        generateChangeFactor();
        changePrice();
    }
    //Polymorphism with the classes bitcoin and USDCoin
    protected void generatePrice(){
        Random rand = new Random();
        price = rand.nextInt(201)+100; // £100 - £300
    }

    public void generateChangeFactor(){
        Random rand = new Random();
        changeFactor = (rand.nextInt(21)+90)/100f; // 90% - 110%
    }

    public void changePrice(){
        generateChangeFactor();
        price = price*changeFactor;
    }

    public String getCryptoname(){
        return this.Cryptoname;
    }

    public float getPrice(){
        return this.price;
    }

}
