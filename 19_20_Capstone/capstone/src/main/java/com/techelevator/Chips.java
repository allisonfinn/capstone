package com.techelevator;

import java.math.BigDecimal;

public abstract class Chips extends Inventory {

    private String sound;

    public Chips (String button, String itemName, BigDecimal itemPrice, String itemType) {
        super (button,itemName, itemPrice, itemType);
    }

    public String getSound(String sound) {
        sound = "Crunch Crunch, Yum!";
        return sound;
    }
}