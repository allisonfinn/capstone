package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Inventory {

    private String sound;

    public Gum (String button, String itemName, BigDecimal itemPrice, String itemType, int itemQuantity) {
        super (button,itemName, itemPrice, itemType, itemQuantity);
    }

    public String getSound() {
        sound = "Chew Chew, Yum!";
        return sound;
    }
}
