package com.techelevator;

import java.math.BigDecimal;

public class Chips extends Inventory {

    private String sound;


    public Chips (String button, String itemName, BigDecimal itemPrice, String itemType, int itemQuantity) {
        super (button,itemName, itemPrice, itemType, itemQuantity);
    }

    public String getSound() {
        sound = "Crunch Crunch, Yum!";
        return sound;
    }
}
