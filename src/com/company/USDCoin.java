package com.company;

import java.util.Random;
//an example of inheritance
public class USDCoin extends CryptoStocks{

    private float price = 0f;
    private float changeFactor = 0f;
    private String Cryptoname = "USDCoin";
    private int amountOwned;

    public USDCoin(){
        generatePrice();
        generateChangeFactor();
        changePrice();
    }

    @Override
    //Has price change like bitcoin but on a smaller scale
    public void generatePrice(){
        Random rand = new Random();
        price = (rand.nextInt(41)+80)/100f; // £0.8 -- £1.20
    }

    @Override
    public void generateChangeFactor(){
        Random rand = new Random();
        changeFactor = (rand.nextInt(11)+95)/100f; // 95% - 105%
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
