package com.techelevator;

import java.math.BigDecimal;

public abstract class Candy extends Inventory {

    private String sound;

    public Candy (String button, String itemName, BigDecimal itemPrice, String itemType) {
        super (button,itemName, itemPrice, itemType);
    }

    public String getSound(String sound) {
        sound = "Munch Munch, Yum!";
        return sound;
    }
}
