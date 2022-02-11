package com.techelevator;

import java.math.BigDecimal;

public class Drinks extends Inventory {

    private String sound;

    public Drinks (String button, String itemName, BigDecimal itemPrice, String itemType, int itemQuantity) {
        super (button,itemName, itemPrice, itemType, itemQuantity);
    }

    public String getSound() {
        sound = "Glug Glug, Yum!";
        return sound;
    }
}
