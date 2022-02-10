package com.techelevator;

import java.math.BigDecimal;

public abstract class Drinks extends Inventory {

    private String sound;

    public Drinks (String button, String itemName, BigDecimal itemPrice, String itemType) {
        super (button,itemName, itemPrice, itemType);
    }

    public String getSound(String sound) {
        sound = "Glug Glug, Yum!";
        return sound;
    }
}
