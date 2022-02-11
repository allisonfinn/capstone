package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Inventory {

    private String sound;

    public Candy (String button, String itemName, BigDecimal itemPrice, String itemType, int itemQuantity) {
        super (button,itemName, itemPrice, itemType, itemQuantity);
    }

    public String getSound() {
        sound = "Munch Munch, Yum!";
        return sound;
    }
}
